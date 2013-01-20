package filesyncserver;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

/**
 *
 * @author Shaun
 *
 * Each Client has a SyncManager that is responsible for communicating
 * with the actual client.
 */
public class SyncManager implements Serializable {

    private URI ipAddress;

    public SyncManager(URI ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ArrayList<SharedDirectory> getDirectoryList() {
        System.out.println("Requesting directory list from: " + ipAddress.toString());
        return new ArrayList<>();
    }

    public void syncDirectories() {
        System.out.println("Performing sync with: " + ipAddress.toString());
    }

    private void openSocket() {
    }
    // Socket manager
}
