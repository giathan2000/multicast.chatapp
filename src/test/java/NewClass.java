
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hesac
 */
public class NewClass {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> a = NetworkInterface.getNetworkInterfaces();
            while (true) {
                try {
                    NetworkInterface n = a.nextElement();
                    System.out.println(n.getName()+" | " + n.getDisplayName()+" | "+n.getIndex()+" | "+n.getHardwareAddress()+" | "+n.isVirtual());
                } catch (Exception e) {
                    break;
                }
                
            }
    }
}
