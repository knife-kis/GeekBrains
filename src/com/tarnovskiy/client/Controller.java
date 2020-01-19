package com.tarnovskiy.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller {
    @FXML
    TextArea textArea;
    @FXML
    TextField textField;
    @FXML
    HBox upperPanel;
    @FXML
    HBox bottomPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;

    private boolean isAuthorized;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADPRESS = "localhost";
    final int PORT = 8189;


    public void connect() {
        try {
            socket = new Socket(IP_ADPRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                authorizationClient();
                readText();
                showPanelWhenLoggedIn(false);
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void authorizationClient() {
        try{
            while (true) {
                String str = in.readUTF();
                if (str.startsWith("/authok")){
                    showPanelWhenLoggedIn(true);
                    break;
                } else {
                    textArea.appendText(str + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readText() {
        try {
            while (true) {
                String str = in.readUTF();
                if (str.equals("/serverClosed")) {
                    break;
                }
                textArea.appendText(str + "\n");
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
            showPanelWhenLoggedIn(false);
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed())
            connect();
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPanelWhenLoggedIn(boolean isAuthorized){
        this.isAuthorized = isAuthorized;
        if(!isAuthorized){
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }
}

