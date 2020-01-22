package com.tarnovskiy.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerMain server;
    private String nick;


    public ClientHandler(ServerMain server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                authorization();
                readClient();
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void authorization() {
        while (true) {
            try {
                String str = in.readUTF();
                String[] tokes = str.split(" ");
                String nickName = AuthService.getnickByLoginAndPass(tokes[1], tokes[2]);
                if (server.searchName(nickName)){
                    sendMsg("Пользователь авторизован");
                } else
                if (str.startsWith("/auth")){
                    if (nickName != null){
                        sendMsg("/authok");
                        nick = nickName;
                        server.subscribe(this);
                        break;
                    } else {
                        sendMsg("Неверный логин/пароль");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readClient() {
        try {
            while (true) {

                String str = in.readUTF();
                if (str.equals("/end")) {
                    out.writeUTF("/serverClosed");
                    break;
                }
                sendPrivateMsg(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server.deleteClient(this);
        }
    }

    private void sendPrivateMsg(String str) {
        if(str.startsWith("/w") || str.startsWith("/W")){
            String[] tokes = str.split(" ");
            if(tokes.length > 1){
                String nickname = tokes[1];
                if(AuthService.searchClient(nickname)){
                    String deleteLine = str.substring(0, (nickname.length() + 3));
                    String privetMsg = str.replace(deleteLine, "");
                    server.privatebroadcastMsg(nick + ": " + privetMsg, nickname);
                }
            }
        } else {
            server.broadcastMsg(nick + ": " + str);
            System.out.println("Client: " + str);
        }
    }

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
