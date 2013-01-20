package filesyncserver;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Shaun
 *
 * Holds the current list of clients the server needs to sync with.
 */
public class ClientList {

    private static ClientList instance;
    private ArrayList<Client> clientList = new ArrayList<>();

    public static ClientList getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new ClientList();
            return instance;
        }
    }

    public void addClient(Client newClient) {
        clientList.add(newClient);
    }

    public void removeClient(Client removedClient) {
        clientList.remove(removedClient);
    }

    public void setClientList(ArrayList<Client> newClientList) {
        clientList = newClientList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public Client getClientByName(String name) {
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getName().equals(name)) {
                return clientList.get(i);
            }
        }
        return null;
    }

}
