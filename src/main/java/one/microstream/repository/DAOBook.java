package one.microstream.repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.serializer.concurrency.LockScope;
import org.eclipse.store.gigamap.lucene.LuceneIndex;
import org.eclipse.store.storage.types.StorageManager;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.eclipsestore.RootProvider;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import one.microstream.domain.Book;
import one.microstream.domain.indices.BookIndices;
import one.microstream.dto.DTOBook;
import one.microstream.storage.Root;


@Singleton
public class DAOBook extends LockScope
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
		LuceneIndex<Book> luceneIndex = rootProvider.root().gigaBooks.index().get(LuceneIndex.class);
		
		List<Book> result = luceneIndex.query("title:" + title);
		
		return result;
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

	public void insert(Book book)
	{
		rootProvider.root().gigaBooks.add(book);
		
		//Option 1:
		manager.store(rootProvider.root().gigaBooks);
		
		//Option 2:
//		rootProvider.root().gigaBooks.store();
		
		rootProvider.root().gigaBooks.index().get(LuceneIndex.class).close();
	}
	
	public void insert(Collection<Book> books)
	{
		rootProvider.root().gigaBooks.addAll(books);
		
		//Option 1:
		manager.store(rootProvider.root().gigaBooks);
		
		rootProvider.root().gigaBooks.index().get(LuceneIndex.class).close();
	}

	public void update(DTOBook dto)
	{
		Book book = getBookISBN(dto.isbn());
		
		rootProvider.root().gigaBooks.update(book, b -> {
		    b.setPrice(dto.price());
		});
		
		manager.storeAll(rootProvider.root().gigaBooks, book);
	}
}
