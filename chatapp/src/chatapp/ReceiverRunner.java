/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hesac
 */
public class ReceiverRunner extends Thread {

    byte[] BUFFER = new byte[4096];
    public final String GROUP_ADDRESS;//= "224.0.0.1";
    public final int PORT;//= 8888;
    MulticastSocket socket = null;
    DatagramPacket inPacket = null;
    InetAddress address;
    NetworkInterface nif;
    SocketAddress skad;
    private String msg;
    private boolean hasNewMessage = false;
    private boolean isConnected;

    public ReceiverRunner(String GROUP_ADDRESS, int PORT,String ni) {
        this.GROUP_ADDRESS = GROUP_ADDRESS;
        this.PORT = PORT;
        init(ni);
    }

    public ReceiverRunner() {
        this("224.0.0.1", 8888,"wlan2");
    }

    private void init(String ni) {
        try {
            address = InetAddress.getByName(GROUP_ADDRESS);
            socket = new MulticastSocket(PORT);
            nif = NetworkInterface.getByName(ni);
            
            skad = new InetSocketAddress(address, PORT);
            socket.joinGroup(skad, nif);
            isConnected = true;
        } catch (IOException ex) {
            Logger.getLogger(ReceiverRunner.class.getName()).log(Level.SEVERE, null, ex);
            isConnected = false;
        }
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        while (isRunning) {
            try {
                inPacket = new DatagramPacket(BUFFER, BUFFER.length);
                socket.receive(inPacket);
                System.out.println(inPacket.getAddress().getHostAddress());
                System.out.println(inPacket.getSocketAddress().toString());
                System.out.println(inPacket.getOffset());
                System.out.println(inPacket.getPort());
                System.out.println(inPacket.getLength());
                msg = new String(BUFFER, 0, inPacket.getLength());
                hasNewMessage = true;
            } catch (IOException ex) {
                Logger.getLogger(ReceiverRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            socket.close();
        } catch (Exception e) {
        }
    }

    @Override
    public synchronized void start() {
        isRunning = true;
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean isRunning = false;

    public void stopServer() {
        isRunning = false;
    }

    public synchronized boolean hasMessage() {
        return hasNewMessage;
    }

    public String getMessage() {
        hasNewMessage = false;
        return msg;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
