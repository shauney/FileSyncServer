package GUI;

import filesyncserver.Client;
import filesyncserver.ClientList;
import filesyncserver.ClientSerializer;
import filesyncserver.SettingsManager;
import filesyncserver.SharedDirectory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Shaun
 */
public class ClientManager extends javax.swing.JFrame {

    private javax.swing.JButton addClientButton;
    private javax.swing.JCheckBox autoSyncCheckbox;
    private javax.swing.JButton browseButton;
    private javax.swing.JList clientDirectoryList;
    private javax.swing.JList clientList;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField mappedLocation;
    private javax.swing.JButton removeClientButton;
    private javax.swing.JButton syncButton;
    private javax.swing.JProgressBar syncProgress;

    private SharedDirectory currentSharedDirectory;

    public ClientManager() {
        initComponents();
        setupActionListeners(this);
        loadClients();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientList = new javax.swing.JList();
        addClientButton = new javax.swing.JButton();
        removeClientButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        clientDirectoryList = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        mappedLocation = new javax.swing.JTextField();
        syncButton = new javax.swing.JButton();
        syncProgress = new javax.swing.JProgressBar();
        autoSyncCheckbox = new javax.swing.JCheckBox();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Client List");

        clientList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(clientList);

        jScrollPane2.setViewportView(jScrollPane1);

        addClientButton.setText("Add");

        removeClientButton.setText("Remove");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addClientButton)
                    .addComponent(removeClientButton))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Shared Directories");

        clientDirectoryList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "No Directories" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(clientDirectoryList);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mapped Location");

        browseButton.setText("Browse");

        syncButton.setText("Sync Now");

        autoSyncCheckbox.setText("Auto Sync Enabled");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel2)
                        .addGap(0, 197, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(165, 165, 165))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(autoSyncCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(syncButton))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(browseButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mappedLocation))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(syncProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseButton)
                    .addComponent(mappedLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(syncButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(autoSyncCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(syncProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }

    private void setMappedLocation(SharedDirectory directory, String location) {
        directory.setLocalDirectory(new File(location));
    }

    private void setMappedLocationText(SharedDirectory directory) {
        if (directory != null) {
            currentSharedDirectory = directory;
            File mappedDir = directory.getLocalDirectory();
            if (mappedDir != null) {
                mappedLocation.setText(mappedDir.getAbsolutePath());
            }
            else {
                String defaultLocation = (String)SettingsManager.getInstance().getSetting("default");
                mappedLocation.setText(defaultLocation);
                setMappedLocation(directory, defaultLocation);
            }
        }
        else {
            mappedLocation.setText("");
        }
    }

    private void setClientDirectories(Client client) {
        ArrayList<SharedDirectory> directoryList = client.getSharedDirectorys();

        if (directoryList != null) {
            clientDirectoryList.setListData(directoryList.toArray());
        }
    }

    private void loadClients() {
        ArrayList<Client> clients = ClientList.getInstance().getClientList();
        clientList.setListData(clients.toArray());

        // Prevent multiple selections
        clientList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        clientDirectoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    public void addClientToList(Client client) {
        ClientList.getInstance().addClient(client);
        loadClients();
    }

    public void removeClientFromList(Client client) {
        ClientList.getInstance().removeClient(client);
        loadClients();
    }

    private void setupActionListeners(final ClientManager mainPanel) {

        // BROWSE BUTTON
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentLocation = mappedLocation.getText();

                if (currentLocation != null || !currentLocation.equals("")) {
                    jFileChooser1.setSelectedFile(new File(currentLocation));
                }

                jFileChooser1.setVisible(true);
            }
        });

        // ADD BUTTON
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClientGUI addClientWindow = new AddClientGUI(mainPanel);
            }
        });

        // REMOVE BUTTON
        removeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selection = clientList.getSelectedValue();
                if (selection instanceof Client) {
                    removeClientFromList((Client) selection);
                }
            }
        });

        // SYNC BUTTON
        syncButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientSerializer hello = new ClientSerializer();
                hello.saveClientList();
            }
        });

        // CLIENT LIST
        clientList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Object selection = clientList.getSelectedValue();
                    if (selection instanceof Client) {
                        setClientDirectories((Client)selection);
                    }
                }
            }
        });

        // SHARED DIRECTORY LIST
        clientDirectoryList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Object selection = clientDirectoryList.getSelectedValue();
                    if (selection instanceof SharedDirectory) {
                        setMappedLocationText((SharedDirectory)selection);
                    }
                }
            }
        });

        // MAPPED LOCATION TEXT
        mappedLocation.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (currentSharedDirectory != null) {
                    currentSharedDirectory.setLocalDirectory(new File(mappedLocation.getText()));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (currentSharedDirectory != null) {
                    currentSharedDirectory.setLocalDirectory(new File(mappedLocation.getText()));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (currentSharedDirectory != null) {
                    currentSharedDirectory.setLocalDirectory(new File(mappedLocation.getText()));
                }
            }
        });
    }
}
