/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shool.htpt.multicast.chatapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hesac
 */
public class SenderRunner {

    byte[] BUFFER = new byte[4096];
    public final String GROUP_ADDRESS;//= "224.0.0.1";
    public final int PORT;//= 8888;
    MulticastSocket socket = null;
    DatagramPacket outPacket = null;
    InetAddress address;
    NetworkInterface nif;
    SocketAddress skad;
    private String msg;
    private boolean hasNewMessage = false;
    private boolean isConnected;

    public SenderRunner(String GROUP_ADDRESS, int PORT) {
        this.GROUP_ADDRESS = GROUP_ADDRESS;
        this.PORT = PORT;
        init();
    }

    public SenderRunner() {
        this("224.0.0.1", 8888);
    }

    private void init() {
        try {
            address = InetAddress.getByName(GROUP_ADDRESS);
            
            socket = new MulticastSocket(PORT);
            nif = NetworkInterface.getByName("wlan2");
            
            Enumeration<NetworkInterface> a = NetworkInterface.getNetworkInterfaces();
            skad = new InetSocketAddress(address, PORT);
            socket = new MulticastSocket();
            socket.setNetworkInterface(nif);
            isConnected = true;
        } catch (Exception ex) {
            Logger.getLogger(ReceiverRunner.class.getName()).log(Level.SEVERE, null, ex);
            isConnected = false;
        }
    }

    public boolean send(String msg) {
        boolean rs = true;
        try {
            
            outPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address,PORT);
            //System.out.println(socket.getLocalAddress().getHostAddress());
            socket.send(outPacket);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        return rs;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
