package one.microstream.listener;

import org.eclipse.store.storage.types.StorageManager;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.eclipsestore.RootProvider;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import one.microstream.storage.Root;


@Singleton
public class MNStartupEvent implements ApplicationEventListener<ServerStartupEvent>
{
	public final RootProvider<Root>	rootProvider;
	private final StorageManager		storageManager;
	
	MNStartupEvent(final RootProvider<Root> rootProvider, final StorageManager storageManager)
	{
		this.rootProvider = rootProvider;
		this.storageManager = storageManager;
	}
	
	@Override
	public void onApplicationEvent(ServerStartupEvent event)
	{
		// LazyReferenceManager.set(
		// LazyReferenceManager.New(
		// Lazy.Checker(
		// Duration.ofMinutes(15).toMillis(), // timeout of lazy access
		// 0.75 // memory quota
		// )));
		
//		System.out.println("Lazy: " + rootProvider.root().getLazyBooks().get().size());
//		System.out.println("Books: " + rootProvider.root().getBooks().size());
//		
//		if(rootProvider.root().getBooks().isEmpty())
//		{
//			rootProvider.root().getBooks().clear();
//			rootProvider.root().getLazyBooks().get().clear();
//			
//			rootProvider.root().setBooks(DB.data.books().all().stream().collect(Collectors.toList()));
//			rootProvider.root().getLazyBooks().get().addAll(DB.data.books().all().stream().collect(Collectors.toList()));
//			rootProvider.root().setShops(DB.data.shops().all().stream().collect(Collectors.toList()));
//			storageManager.store(rootProvider.root());
//			storageManager.store(rootProvider.root().getBooks());
//			storageManager.store(rootProvider.root().getLazyBooks().get());
//			storageManager.store(rootProvider.root().getShops());
//			System.out.println("[Database loaded]");
//			
//		}
//		System.out.println("Lazy: " + rootProvider.root().getLazyBooks().get().size());
//		System.out.println("Books: " + rootProvider.root().getBooks().size());
//		System.out.println("Shops: " + rootProvider.root().getShops().size());
	}
	
}
