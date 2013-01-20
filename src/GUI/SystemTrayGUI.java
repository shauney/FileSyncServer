package GUI;

import filesyncserver.FileSyncServer;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Shaun
 *
 * This class creates the main GUI interface on the system tray.
 */
public class SystemTrayGUI {

    private PopupMenu menu;
    private TrayIcon trayIcon;
    private SystemTray tray;
    private ClientManager clientManagerGUI;

    public static boolean checkTraySupported() {
        boolean traySupported = true;
        if (!SystemTray.isSupported()) {
            // TODO log system tray not supported
            traySupported = false;
        }
        return traySupported;
    }

    public void init() {
        menu = new PopupMenu();
        tray = SystemTray.getSystemTray();
        ImageIcon image = new ImageIcon(SystemTrayGUI.class.getResource("trayIcon.gif"));
        trayIcon = new TrayIcon(image.getImage());
        trayIcon.setImageAutoSize(true);

        // Create popup menu
        MenuItem clientManager = new MenuItem("Client Manager");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");

        menu.add(clientManager);
        menu.add(aboutItem);
        menu.add(exitItem);

        trayIcon.setPopupMenu(menu);

        // Add Action Listeners
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileSyncServer.close();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        clientManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        clientManagerGUI = new ClientManager();
                    }
                });
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        clientManagerGUI = null;
    }

    // Directory "mapper"

}
