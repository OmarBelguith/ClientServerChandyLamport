package socket.test.com;
import java.io.*;
import java.net.*;

public class Server extends ChandyLamport {
    
    public static void main(String[] args) throws Exception  {
        ServerSocket serverSocket= new ServerSocket(port);
        socket = serverSocket.accept();
        System.out.println("SOCKET = " + socket);
        ChandyLamport myServer = new ChandyLamport();
        myServer.setup();
        String str = "100";
        myServer.sendMessage(str);
        myServer.chandyLamport(myServer.receiveMessage());
        myServer.initiateChandy();
        myServer.chandyLamport(myServer.receiveMessage());
        myServer.chandyLamport(myServer.receiveMessage());
        myServer.closeFiles();
        socket.close();
    }
}
