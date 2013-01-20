package filesyncserver;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Shaun
 *
 * A class that represents a directory on the client.
 */
public class SharedDirectory implements Serializable {

    private File localDirectory;
    private File clientDirectory;

    public SharedDirectory(File localDirectory, File clientDirectoy) {
        this.localDirectory = localDirectory;
        this.clientDirectory = clientDirectoy;
    }

    public File getLocalDirectory() {
        return localDirectory;
    }

    public File getClientDirectory() {
        return clientDirectory;
    }

    public void setLocalDirectory(File localDirectory) {
        this.localDirectory = localDirectory;
    }

    @Override
    public String toString() {
        return clientDirectory.getAbsolutePath();
    }
}
