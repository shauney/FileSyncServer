package filesyncserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Shaun
 *
 * The ClientSerializer saves the client list to file and parses it
 * when needed.
 */
public class ClientSerializer {

    private static ClientSerializer instance;
    private final String CLIENT_LIST_FILENAME_KEY = "clientlist.filename";
    private final String CLIENT_LIST_FILENAME_DEFAULT = "ClientList";
    private String path;
    private File clientListFile;

    public ClientSerializer() {
        SettingsManager settings = SettingsManager.getInstance();
        path = settings.getStringSetting(SettingsManager.SETTINGS_PATH_KEY);
        settings.setSettingIfRequired(CLIENT_LIST_FILENAME_KEY, CLIENT_LIST_FILENAME_DEFAULT);
        String filename = path + settings.getStringSetting(CLIENT_LIST_FILENAME_KEY);
        clientListFile = new File(filename);
    }

    public static ClientSerializer getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new ClientSerializer();
            return instance;
        }
    }

    public void saveClientList() {
        ArrayList<Client> clientList = ClientList.getInstance().getClientList();

        try {
            System.out.println("Saving Client List.........");
            clientListFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(clientListFile);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(clientList);
            output.close();
            fileOut.close();
            System.out.println("Saved!");
        }
        catch (IOException e) {
            System.out.println("Error occured while saving the current ClientList");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Client> loadClientList() {
        ArrayList<Client> clientList = null;

        if (clientListFile.exists()) {
            try {
                System.out.println("Loading Client List.......");
                FileInputStream fileIn = new FileInputStream(clientListFile);
                ObjectInputStream input = new ObjectInputStream(fileIn);
                clientList = (ArrayList<Client>) input.readObject();
                input.close();
                fileIn.close();
                System.out.println("Done!");
                return clientList;
            }
            catch (IOException|ClassNotFoundException e) {
                System.out.println("Error occured while loading the ClientList");
                System.out.println(e.getMessage());
            }
        }

        // Return blank client list if none found or error
        clientList = new ArrayList<>();
        return clientList;
    }

}
