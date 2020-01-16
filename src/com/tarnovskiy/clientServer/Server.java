package com.tarnovskiy.clientServer;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Tarnovskiy Maksim
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");
            socket = server.accept();
            System.out.println("Клиент подключился ");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                while (true){
                    String msgServer = scanner.nextLine();
                    writeMsgServer(out, msgServer);
                }
            }).start();
            while (true){
                String msgClient = in.readUTF();
                System.out.println(msgClient);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Сокет закрылся ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
                System.out.println("Сервер закрылся ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeMsgServer(DataOutputStream out, String msgServer) {
        try {
            out.writeUTF(msgServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

