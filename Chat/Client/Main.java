package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String ip = "localhost";
    public static int port = 7771;
    public static ListenToServer listenToServer;
    public static WriteToServer writeToServer;
    public static String myLastMSG;
    public static List<String> chatHistory = new ArrayList<String>();
    public static String myName;

    public static void main (String args[]) {
        try {
            Scanner scan = new Scanner(System.in);

            /*System.out.print("enter ip : ");
            ip = scan.nextLine();
            System.out.print("enter port : ");
            port = scan.nextInt();*/


            Socket server = new Socket(ip,port);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));

            System.out.print("Enter your name : ");
            myName = scan.nextLine();
            out.write("*"+myName);
            out.flush();

            listenToServer = new ListenToServer(server);
            writeToServer = new WriteToServer(server);

            String tmpString;
            while (true) {
                tmpString = scan.nextLine();
                myLastMSG = tmpString;
                updateScreen();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateScreen () {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        for (String a : chatHistory) {
            System.out.println(a);
        }
    }
}
