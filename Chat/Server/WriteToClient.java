package Server;

import java.io.BufferedWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class WriteToClient extends Thread {

    public Socket client;
    public boolean send = false;
    @Override
    public void run () {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(client.getOutputStream());

            while (true) {
                if (send) {
                    objectOutput.writeObject(Main.getChatHistory());
                    objectOutput.flush();
                    send = false;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public WriteToClient (Socket client) {
        this.client = client;
    }
}
