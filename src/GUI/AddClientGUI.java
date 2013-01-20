package GUI;

import filesyncserver.Client;
import filesyncserver.ClientList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

public class AddClientGUI extends JFrame {

    private ClientManager parentPanel;

    private JPanel namePanel = new JPanel();
    private JLabel nameLabel = new JLabel("Name");
    private JTextField nameField = new JTextField(20);

    private JPanel ipPanel = new JPanel();
    private JLabel ipLabel = new JLabel("IP Address");
    private JTextField ipField = new JTextField(20);

    private JPanel buttonPanel = new JPanel();
    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");

    public AddClientGUI(ClientManager parent) {
        parentPanel = parent;
        init();
    }

    private void init() {

        this.setLayout(new BorderLayout());

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        ipPanel.add(ipLabel);
        ipPanel.add(ipField);

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        this.getContentPane().add(namePanel, BorderLayout.NORTH);
        this.getContentPane().add(ipPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        addActionListeners(this);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addActionListeners(final JFrame mainPanel) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                URI ip = createIPAddress(ipField.getText());
                String name = nameField.getText();

                if (ip != null && !name.equals("")) {
                    Client client = new Client(ip, name);
                    parentPanel.addClientToList(client);
                    mainPanel.dispose();
                }
                else {
                    JDialog error = new JDialog(mainPanel, "Invalid Details", Dialog.ModalityType.APPLICATION_MODAL);
                    error.setVisible(true);
                }


            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.dispose();
            }
        });
    }

    private URI createIPAddress(String ip) {
        try {
            // TODO Create IP validator
            return new URI(ip);
        }
        catch (URISyntaxException ex) {
            return null;
        }
    }
}
