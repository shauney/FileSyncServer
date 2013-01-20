package filesyncserver;

import GUI.SystemTrayGUI;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.SwingUtilities;

/**
 *
 * @author Shaun
 */
public class FileSyncServer {

    private static SystemTrayGUI systemTray;
    private final static String CONFIG_LOCATION_ENV_VARIABLE = "SYNC_SERVER_CONFIG";
    private SettingsManager settingsManager;
    private ClientSerializer clientSerializer;

    public static void main(String[] args) {
        // parse arguments

        FileSyncServer instance = new FileSyncServer();
        instance.startFileSyncServer();
    }

    public void startFileSyncServer() {

        // load GUI
        if (!SystemTrayGUI.checkTraySupported()) {
            System.out.println("System Tray Not Supported!");
            System.exit(1);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                systemTray = new SystemTrayGUI();
                systemTray.init();
                }
            });

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    SettingsManager.getInstance().saveSettingsFile();
                    ClientSerializer.getInstance().saveClientList();
                }
            });
        }

//        System.getenv(CONFIG_LOCATION_ENV_VARIABLE);
        settingsManager = new SettingsManager("C:\\FileSyncServer\\");
        clientSerializer = ClientSerializer.getInstance();
        ClientList.getInstance().setClientList(clientSerializer.loadClientList());

//        try {
//            loadTest();
//        }
//        catch (Exception e) {
//            System.out.println("OH UH!");
//        }

        // Main loop
        // Sync with client list
    }

    public static void close() {
        SettingsManager.getInstance().saveSettingsFile();
        // interrupt main loop
        systemTray = null;
        System.exit(0);
    }

    public void loadTest() throws URISyntaxException {
        Client testClient1 = new Client(new URI("192.168.0.1"), "client1");
        ArrayList<SharedDirectory> testClient1Dirs = new ArrayList<>();
        testClient1Dirs.add(new SharedDirectory(new File("C:\\client1\\dir1"), new File("C:\\clientdir1")));
        testClient1Dirs.add(new SharedDirectory(new File("C:\\client1\\dir2"), new File("C:\\clientdir2")));
        testClient1Dirs.add(new SharedDirectory(new File("C:\\client1\\dir3"), new File("C:\\clientdir3")));
        testClient1.setSharedDirectories(testClient1Dirs);

        Client testClient2 = new Client(new URI("192.168.0.2"), "client2");
        ArrayList<SharedDirectory> testClient2Dirs = new ArrayList<>();
        testClient2Dirs.add(new SharedDirectory(new File("C:\\client2\\dir1"), new File("C:\\clientdir1")));
        testClient2Dirs.add(new SharedDirectory(new File("C:\\client2\\dir2"), new File("C:\\clientdir2")));
        testClient2Dirs.add(new SharedDirectory(new File("C:\\client2\\dir3"), new File("C:\\clientdir3")));
        testClient2.setSharedDirectories(testClient2Dirs);

        Client testClient3 = new Client(new URI("192.168.0.3"), "client3");
        ArrayList<SharedDirectory> testClient3Dirs = new ArrayList<>();
        testClient3Dirs.add(new SharedDirectory(new File("C:\\client3\\dir1"), new File("C:\\clientdir1")));
        testClient3Dirs.add(new SharedDirectory(new File("C:\\client3\\dir2"), new File("C:\\clientdir2")));
        testClient3Dirs.add(new SharedDirectory(new File("C:\\client3\\dir3"), new File("C:\\clientdir3")));
        testClient3.setSharedDirectories(testClient3Dirs);

        ClientList clientList = ClientList.getInstance();
        clientList.addClient(testClient1);
        clientList.addClient(testClient2);
        clientList.addClient(testClient3);
    }
}
