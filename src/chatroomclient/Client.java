/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author AhmedWard
 */
public class Client {
    
    Socket socket;
    DataInputStream dis;
    PrintStream ps;
    
    public Client() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void readMessage(TextArea textArea) {
        
        new Thread() {
            @Override
            public void run() {
                try {
                    while (socket.isConnected()) {
                        Thread.sleep(100);
                        textArea.appendText(dis.readLine() + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }
    
    public void sendMessage(String message) {
        
        new Thread() {
            @Override
            public void run() {
                ps.println(message);
            }
        }.start();
    }
    
}
