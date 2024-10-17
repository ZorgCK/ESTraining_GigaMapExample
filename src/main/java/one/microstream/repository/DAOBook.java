package one.microstream.repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.store.storage.types.StorageManager;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.eclipsestore.RootProvider;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import one.microstream.concurrent.ReadWriteLocked;
import one.microstream.domain.Book;
import one.microstream.domain.indices.BookIndices;
import one.microstream.storage.Root;


@Singleton
public class DAOBook extends ReadWriteLocked
{
	public final RootProvider<Root>	rootProvider;
	private final StorageManager	manager;
	private int						storeCount	= 0;
	
	DAOBook(final RootProvider<Root> rootProvider, final StorageManager manager)
	{
		this.rootProvider = rootProvider;
		this.manager = manager;
	}
	
	public Book getBookISBN(String isbn)
	{
		return rootProvider.root().gigaBooks.query(BookIndices.ISBNIndex.is(isbn)).findFirst().get();
	}
	
	public List<Book> searchBooksTitle(String title)
	{
		return this.read(() -> 
		{
			return rootProvider.root().gigaBooks
				.query(BookIndices.titleIndex.contains(title))
				.toList(1000);			
		});
	}
	
	public synchronized void insert(Book book)
	{
		Root root = rootProvider.root();
		
		root.gigaBooks.add(book);
		
		this.write(() ->
		{
			if(storeCount > 1000)
			{
				root.gigaBooks.store();
				storeCount = 0;
			}
			else
			{
				storeCount++;
			}
		});
	}
	
	public List<Book> pageBooks(@NonNull @NotBlank int limit)
	{
		Root root = rootProvider.root();
		
		try(Stream<Book> stream = root.gigaBooks.query().stream())
		{
			return stream.limit(limit).collect(Collectors.toList());
		}
	}
	
	public Long countBooks()
	{
		AtomicLong count = new AtomicLong();
		
		this.read(() -> 
		{
			count.addAndGet(rootProvider.root().gigaBooks.query().count());
		});
		
		return count.get();
	}
}
