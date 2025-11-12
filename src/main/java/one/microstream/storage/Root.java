
package one.microstream.storage;

import org.eclipse.store.gigamap.types.BitmapIndices;
import org.eclipse.store.gigamap.types.GigaMap;

import io.micronaut.serde.annotation.Serdeable;
import one.microstream.domain.Book;
import one.microstream.domain.indices.BookIndices;


/**
 * MicroStream data root. Create your data model from here.
 *
 * @see <a href="https://manual.docs.microstream.one/">Reference Manual</a>
 */
@Serdeable
public class Root
{	
	//Step 1
	//private List<Book> books1 = new ArrayList<Book>();
	//Step 2
	//private Lazy<List<Book>> books2 = Lazy.Reference(new ArrayList<Book>());
	//Step 3
	//private Map<String, Lazy<List<Book>>> books3 = new HashMap<String, Lazy<List<Book>>>();
	//Step 4
	
	public GigaMap<Book>	gigaBooks	= GigaMap.New();
	
	public Root()
	{
		super();
		
		final BitmapIndices<Book> indices = gigaBooks.index().bitmap();
		indices.add(BookIndices.ISBNIndex);
		indices.add(BookIndices.pubDateIndex);
		BookIndices.registerLuceneIndex(gigaBooks);
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
