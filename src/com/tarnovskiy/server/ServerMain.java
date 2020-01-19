package com.tarnovskiy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerMain {
    private Vector<ClientHandler> clients;

    public ServerMain() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");


            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился ");
                new ClientHandler(this, socket);
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
            AuthService.disconnect();
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    public void privatebroadcastMsg(String msg, String nickName){
        for (ClientHandler t: clients){
            if (t.getNick().equals(nickName))
                t.sendMsg("ПC от " + msg);
        }
    }

    public void errorMsg(ClientHandler clientHandler){
        clientHandler.sendMsg("такого пользователя не существует" + clientHandler.getNick());
    }

    public void deleteClient(ClientHandler i) {
        System.out.println("Клиент отключился ");
        clients.remove(i);
    }

    public boolean searchName(String nick) {
        boolean check = false;
        for (ClientHandler o: clients) {
            if (o.getNick().equals(nick)){
                check = true;
            }
        }
        return check;
    }
}
