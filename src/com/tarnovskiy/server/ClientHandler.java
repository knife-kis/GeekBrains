package com.tarnovskiy.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerMain server;

    public ClientHandler(ServerMain server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(this::readClient).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readClient() {
        try {
            while (true) {
                String str = in.readUTF();
                if (str.equals("/end")) {
                    server.deleteClient(this);
                    out.writeUTF("/serverClosed");
                    break;
                }
                System.out.println("Client: " + str);
                server.broadcastMsg(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
