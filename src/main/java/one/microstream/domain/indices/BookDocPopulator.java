package one.microstream.domain.indices;

import org.apache.lucene.document.Document;
import org.eclipse.store.gigamap.lucene.DocumentPopulator;

import one.microstream.domain.Book;

public class BookDocPopulator extends DocumentPopulator<Book>
{
	@Override
    public void populate(Document document, Book book)
    {
        document.add(createTextField("title", book.getTitle()));
        document.add(createTextField("authorLastname", book.getAuthor().getLastname()));
        document.add(createTextField("releaseDate", book.getRelease().toString()));
    }
}
