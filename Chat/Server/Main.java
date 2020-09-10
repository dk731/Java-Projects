package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int maxHistorySize = 100;
    public static List<String> chatHistory = new ArrayList<String>();
    public static List<ListenToClient> threadList = new ArrayList<ListenToClient>();
    public static List<ListenToClient> clientList = new ArrayList<ListenToClient>();

    public static void main(String args[]) {
        ListenToClient tmpThread;
        System.out.println("Server has been started");
        AdminThread adminThread = new AdminThread();
        adminThread.run();

        ServerSocket server;


        try {
            server = new ServerSocket(7771);

            while (true) {
                tmpThread = new ListenToClient(server.accept());
                System.out.println("We just got a new user!");
                tmpThread.run();
                threadList.add(tmpThread);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    synchronized static List<String> getChatHistory() {
        return chatHistory;
    }

    synchronized static void addString(String string) {
        chatHistory.add(string);
        if (chatHistory.size() > maxHistorySize) {
            chatHistory.remove(0);
        }
    }

    synchronized static boolean checkName (ListenToClient client){
        for (ListenToClient a : clientList) {
            if (a.myName.equals(client.myName)) {
                return false;
            }
        }
        clientList.add(client);
        return true;
    }

    synchronized static void removeThread(ListenToClient client) {
        if (clientList.contains(client)) {
            clientList.remove(client);
        }
        threadList.remove(client);
    }

    synchronized static void notifyAllWriters() {
        for (ListenToClient a : clientList) {
            a.writer.send = true;
        }
    }


}
