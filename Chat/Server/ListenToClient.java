package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ListenToClient extends Thread{
    private Socket client;
    public String myName = "";
    public WriteToClient writer;


    @Override
    public void run() {
        String tmpString = "";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            /*System.out.println("Hello111");
            out.write("You successfully connected to the server!");
            System.out.println("Hello222");
            out.write("REQUEST#NAME");
            out.flush();
            System.out.println("Hello333");*/
            tmpString = in.readLine();

            System.out.println(tmpString);
            if ( tmpString.charAt(0) == '*' ) {
                myName = tmpString.substring(1);
                if ( ! Main.checkName(this) ) {
                    out.write("ERROR. NAME ALLREADY EXISTS");
                    Main.removeThread(this);
                }
            }

            writer = new WriteToClient(client);
            writer.run();

            while(true) {

                tmpString = in.readLine();
                if (false) {
                    Main.removeThread(this);
                }

                Main.chatHistory.add(myName+":"+tmpString);
                Main.notifyAllWriters();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListenToClient(Socket client) {
        this.client = client;

    }
}
