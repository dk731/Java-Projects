package Client;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class WriteToServer extends Thread {
    private Socket server;

    @Override
    public void run () {
        try {

            while (true) {
                wait();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));

                out.write(Main.myLastMSG);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WriteToServer (Socket server) {
        this.server = server;
    }
}
