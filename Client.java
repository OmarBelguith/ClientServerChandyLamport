package socket.test.com;

import java.io.*;
import java.net.*;

/** The client process initializes a connection with the host introduced 
 *   by the command line as argument. It uses the port 8080.
 */
public class Client extends ChandyLamport {

    public static void main(String[] args) throws Exception  {
        socket = new Socket(InetAddress.getLocalHost(), port);
        System.out.println("SOCKET = " + socket);
        ChandyLamport myClient = new ChandyLamport();
        myClient.setup();
        String str = "50";
        myClient.sendMessage(str);
        myClient.chandyLamport(myClient.receiveMessage());
        str="30";
        myClient.sendMessage(str);
        myClient.chandyLamport(myClient.receiveMessage());
        myClient.closeFiles();
        socket.close();
    }
}