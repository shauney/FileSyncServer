package filesyncserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Shaun
 *
 * The SettingsManager is responsible for saving and parsing the settings file.
 */
public class SettingsManager {

    private static SettingsManager instance;
    private HashMap settings;

    private final static String DEFAULT_PATH = "C:\\Program Files\\FileSyncServer\\";
    private final String SETTINGS_FILENAME = "settings";
    public final static String SETTINGS_PATH_KEY = "settings.path";
    private File settingsFile;

    public SettingsManager(String settingsPath) {
        settingsFile = new File(settingsPath + SETTINGS_FILENAME);
        loadSettingsFile();
        settings.put(SETTINGS_PATH_KEY, settingsPath);
    }

    public static SettingsManager getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new SettingsManager(DEFAULT_PATH);
            return instance;
        }
    }

    private void loadSettingsFile() {
        HashMap loadedSettings = null;

        if (settingsFile.exists()) {
            try {
                System.out.println("Loading Settings..........");
                FileInputStream fileIn = new FileInputStream(settingsFile);
                ObjectInputStream input = new ObjectInputStream(fileIn);
                loadedSettings = (HashMap) input.readObject();
                input.close();
                fileIn.close();
                this.settings = loadedSettings;
            }
            catch (IOException|ClassNotFoundException e) {
                System.out.println("Error occured while loading settings.");
                System.out.println(e.getMessage());
                this.settings = new HashMap();
            }
            System.out.println("Done!");
        }
        else {
            this.settings = new HashMap();
        }
    }

    public void saveSettingsFile() {
        try {
            System.out.println("Saving Settings.........");
            settingsFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(settingsFile);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(settings);
            output.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.out.println("Error occured while saving the current settings.");
            System.out.println(e.getMessage());
        }
        System.out.println("Saved!");
    }

    // Set/Get settings methods
    public Object getSetting(String key) {
        return null;
    }

    public void setSetting(String key, Object setting) {
        settings.put(key, setting);
    }

    // String
    public String getStringSetting(String key) {
        Object setting = settings.get(key);
        if (setting instanceof String) {
            return (String)setting;
        }
        else {
            return null;
        }
    }

    public void setSettingIfRequired(String key, Object setting) {
        if (!settings.containsKey(key)) {
            settings.put(key, setting);
        }
    }
}
