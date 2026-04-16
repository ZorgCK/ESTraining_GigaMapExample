package one.microstream.repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.inject.Inject;
import one.microstream.storage.StorageService;
import org.eclipse.serializer.concurrency.LockScope;
import org.eclipse.store.gigamap.lucene.LuceneIndex;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import one.microstream.domain.Book;
import one.microstream.domain.indices.BookIndices;
import one.microstream.dto.DTOBook;


@Singleton
public class DAOBook extends LockScope
{
    @Inject
    StorageService storageService;

	public Book getBookISBN(String isbn)
	{
//		return storageService.provideRoot().gigaBooks.query(BookIndices.ISBNIndex.is(isbn)).findFirst().get();
	}
	
	public List<Book> searchBooks(String searchTerm)
	{		
//		LuceneIndex<Book> luceneIndex = storageService.provideRoot().gigaBooks.index().get(LuceneIndex.class);
//
//		List<Book> result = luceneIndex.query("title:" + searchTerm + " OR authorLastname:" + searchTerm);
//
//		return result;
	}

	public List<Book> pageBooks(@NonNull @NotBlank int limit)
	{
//		try(Stream<Book> stream = storageService.provideRoot().gigaBooks.query().stream())
//		{
//			return stream.limit(limit).collect(Collectors.toList());
//		}
	}
	
	public Long countBooks()
	{
//		AtomicLong count = new AtomicLong();
//
//		this.read(() ->
//		{
//			count.addAndGet(storageService.provideRoot().gigaBooks.query().count());
//		});
//
//		return count.get();
	}

	public void insert(Book book)
	{
//        storageService.provideRoot().gigaBooks.add(book);
//        storageService.provideRoot().gigaBooks.store();
	}
	
	public void insert(Collection<Book> books)
	{
//        storageService.provideRoot().gigaBooks.addAll(books);
//        storageService.provideRoot().gigaBooks.store();
	}

	public void update(DTOBook dto)
	{
//		Book book = getBookISBN(dto.isbn());
//
//      storageService.provideRoot().gigaBooks.update(book, b -> {
//		    b.setPrice(dto.price());
//		});
//
//        storageService.provideRoot().gigaBooks.store();
	}
}
