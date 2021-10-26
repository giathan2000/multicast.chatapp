package chatapp;

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
public class GetInterfaceNetwork {

    public static String getInterfaceNetwork() throws SocketException {
        String rs = "Name   |     DisplayName       |    Index   |      HardwareAddress        |         isVirtual"
                + "\n"
                + "--------------------------------------------------------------------------------------------------------------------------------"
                + "\n";
        Enumeration<NetworkInterface> a = NetworkInterface.getNetworkInterfaces();
        while (true) {
            try {
                NetworkInterface n = a.nextElement();
                System.out.println(n.getName() + "       : " + n.getDisplayName() + " | " + n.getIndex() + " | " + n.getHardwareAddress() + " | " + n.isVirtual());
                rs += n.getName() + "       : " + n.getDisplayName() + " | " + n.getIndex() + " | " + n.getHardwareAddress() + " | " + n.isVirtual() + "\n"
                        //+ "\n"
                        + "--------------------------------------------------------------------------------------------------------------------------------"
                        + "\n";

            } catch (Exception e) {
                break;
            }

        }

        rs += "Begin : \n"
                + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
                + ""
                + "\n"
                + "\n";
        return rs;
    }
}
