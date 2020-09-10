package Client;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class ListenToServer extends Thread {
    private Socket server;

    @Override
    public void run () {
        try {
            ObjectInputStream objectInput = new ObjectInputStream(server.getInputStream());

            while (true) {

                Main.chatHistory = (List<String>) objectInput.readObject();
                Main.updateScreen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListenToServer (Socket server) {
        this.server = server;
    }
}
