package filesyncserver;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

/**
 *
 * @author Shaun
 *
 * A representation of the Client, holds information needed to identify a
 * particular client.
 */
public class Client implements Serializable {

    private URI ipAddress;
    public ArrayList<SharedDirectory> sharedDirectories;
    private SyncManager syncManager;
    private String name;

    public Client(URI ipAddress, String name) {
        this.name = name;
        this.ipAddress = ipAddress;
        syncManager = new SyncManager(ipAddress);
    }

    public void updateMetaData() {
        sharedDirectories = syncManager.getDirectoryList();
    }

    public void syncDirectories() {
        syncManager.syncDirectories();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SharedDirectory> getSharedDirectorys() {
        return sharedDirectories;
    }

    public void setSharedDirectories(ArrayList<SharedDirectory> sharedDirectories) {
        this.sharedDirectories = sharedDirectories;
    }

    @Override
    public String toString() {
        return name;
    }
}
