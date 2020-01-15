package com.tarnovskiy.clientServer;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Tarnovskiy Maksim
 */
public class Client {
    public static void main(String[] args) {
        final String IP_ADPRESS = "localhost";
        final int PORT = 8189;
        DataInputStream in;
        DataOutputStream out;
        Socket socket = null;

        try {
            socket = new Socket(IP_ADPRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            new Thread(() -> {
                while (true) {
                    getMsgServer(in);
                }
            }).start();
            while (true) {
                String strClient = scanner.nextLine();
                out.writeUTF(strClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getMsgServer(DataInputStream in) {
        try {
            String msgServer = in.readUTF();
            System.out.println(msgServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


