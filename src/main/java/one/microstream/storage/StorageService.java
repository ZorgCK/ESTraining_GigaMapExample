package one.microstream.storage;

import jakarta.inject.Singleton;
import org.eclipse.store.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;

@Singleton
public class StorageService
{
    private EmbeddedStorageManager storageManager;
    private Root root;

    public EmbeddedStorageManager provideStorageManager() {

        if (storageManager == null)
        {
            initDatabase();
        }

        return storageManager;
    }

    public Root provideRoot()
    {
        if (storageManager == null)
        {
            initDatabase();
        }
        return root;
    }

    private void initDatabase()
    {
        this.root = new Root();
        this.storageManager = EmbeddedStorageConfiguration.Builder()
                .setChannelCount(2)
                .setStorageDirectory("storage")
                .createEmbeddedStorageFoundation().createEmbeddedStorageManager(root)
                .start();
    }
}
