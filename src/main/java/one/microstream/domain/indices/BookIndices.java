package one.microstream.domain.indices;

import org.eclipse.store.gigamap.lucene.LuceneContext;
import org.eclipse.store.gigamap.lucene.LuceneIndex;
import org.eclipse.store.gigamap.lucene.LuceneIndex.Category;
import org.eclipse.store.gigamap.types.BinaryIndexerString;
import org.eclipse.store.gigamap.types.GigaMap;

import jakarta.inject.Singleton;
import one.microstream.domain.Book;

@Singleton
public class BookIndices
{
	public final static BinaryIndexerString<Book> ISBNIndex = new BinaryIndexerString.Abstract<Book>()
	{
		public String name()
		{
			return "isbn";
		}
		
		@Override
		public String getString(final Book entity)
		{
			return entity.getIsbn();
		}
	};

	public static void registerLuceneIndex(GigaMap<Book> map)
	{
		LuceneContext<Book> luceneContext = LuceneContext.New(new BookDocPopulator());
		Category<Book> category = LuceneIndex.Category(luceneContext);
		map.index().register(category);
	}
	

}
