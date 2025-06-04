
package one.microstream.storage;

import io.micronaut.serde.annotation.Serdeable;
import one.microstream.domain.Book;
import one.microstream.domain.indices.BookIndices;
import one.microstream.gigamap.BitmapIndices;
import one.microstream.gigamap.GigaMap;


/**
 * MicroStream data root. Create your data model from here.
 *
 * @see <a href="https://manual.docs.microstream.one/">Reference Manual</a>
 */
@Serdeable
public class Root
{
	public GigaMap<Book>	gigaBooks	= GigaMap.New();
	
	public Root()
	{
		super();
		
		final BitmapIndices<Book> indices = gigaBooks.index().bitmap();
		indices.add(BookIndices.titleIndex);
		indices.add(BookIndices.ISBNIndex);
		indices.add(BookIndices.pubDateIndex);
		indices.setIdentityIndices(BookIndices.ISBNIndex);
	}
		
	public GigaMap<Book> getGigaBooks()
	{
		return gigaBooks;
	}
	
	public void setGigaBooks(GigaMap<Book> gigaBooks)
	{
		this.gigaBooks = gigaBooks;
	}	
}
