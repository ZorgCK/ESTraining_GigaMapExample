package one.microstream.domain.indices;

import java.nio.file.Paths;
import java.time.LocalDate;

import org.eclipse.store.gigamap.lucene.LuceneContext;
import org.eclipse.store.gigamap.lucene.LuceneIndex;
import org.eclipse.store.gigamap.lucene.LuceneIndex.Category;
import org.eclipse.store.gigamap.types.GigaMap;
import org.eclipse.store.gigamap.types.IndexerLocalDate;
import org.eclipse.store.gigamap.types.IndexerString;

import io.micronaut.eclipsestore.RootProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import one.microstream.domain.Book;
import one.microstream.storage.Root;

@Singleton
public class BookIndices
{
	@Inject
	RootProvider<Root>	rootProvider;
	
	public final static IndexerString<Book> ISBNIndex = new IndexerString.Abstract<Book>()
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
	
	public final static IndexerString<Book> titleIndex = new IndexerString.Abstract<Book>()
	{
		public String name()
		{
			return "title";
		}
		
		@Override
		public String getString(final Book entity)
		{
			return entity.getTitle();
		}
	};

	public static void registerLuceneIndex(GigaMap<Book> map)
	{
		LuceneContext<Book> luceneContext = LuceneContext.New(
		    Paths.get("lucene-store"),    // path to directory
		    new BookTitlePopulator() // our document populator
		);
		
		Category<Book> category = LuceneIndex.Category(luceneContext);
		
		map.index().register(category);
	}
	
	public final static IndexerLocalDate<Book> pubDateIndex = new IndexerLocalDate.Abstract<Book>()
	{
		public String name()
		{
			return "releaseDate";
		}
		
		@Override
		protected LocalDate getLocalDate(final Book entity)
		{
			return entity.getRelease();
		}
	};
}
