package one.microstream.listener;

import org.eclipse.store.storage.types.StorageManager;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerShutdownEvent;
import jakarta.inject.Singleton;


@Singleton
public class MNShutDownEvent implements ApplicationEventListener<ServerShutdownEvent>
{
	private final StorageManager manager;
	
	MNShutDownEvent(final StorageManager manager)
	{
		this.manager = manager;
	}
	
	@Override
	public void onApplicationEvent(ServerShutdownEvent event)
	{
		manager.shutdown();
	}
}
