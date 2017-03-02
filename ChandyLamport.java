package socket.test.com;

import java.io.*;
import java.net.Socket;

/**
 * Created by admin on 3/2/2017.
 */
public class ChandyLamport {
    static final int port = 8080;
    static Socket socket;
    BufferedReader plec;
    PrintWriter pred;

    public int functionalState = 500;
    //Non Functional state
    enum NodeType {INITIATOR, NODE};
    NodeType ntype;
    int localState;
    boolean storedState;
    int [] channelState;


    public void chandyLamport(String str) throws IOException {
        if (str.equalsIgnoreCase("MARKER")){
            System.out.println("Hello Marker");
            if (!this.storedState){
                this.localState = this.functionalState;
                this.channelState[0] = -1;
                System.out.println("Local State Stored: "+ this.localState);
                System.out.println("Channel State Stored: "+ this.channelState[0]);
                this.storedState = true;
                this.sendMessage("MARKER");
            }else{
                System.out.println("My Local State is : " + this.localState);
                System.out.println("Channel State is : " + channelState[0]);
            }
        }
        else{
            System.out.println("It's not a Marker, the value is: " + str);
            if (this.storedState){
                this.channelState[0]+=Integer.parseInt(str);
                System.out.println("Modify the channel : " + this.channelState[0]);
            }
            else{
                functionalState += Integer.parseInt(str);
                System.out.println("Modify the state : " + this.functionalState);
            }
        }
    }
    public void sendMessage (String str)throws IOException{
         pred.println(str);
        if (!str.equalsIgnoreCase("MARKER")){
            int m=Integer.parseInt(str);
            functionalState=functionalState-m;
        }
    }

    public String receiveMessage ( ) throws IOException{
        return (plec.readLine());
    }
    public void closeFiles() throws IOException{
        plec.close();
        pred.close();
    }
    public void setup(){
        ntype = NodeType.NODE;
        try {
            plec = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            pred = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Non Functional initialization
        channelState = new int [] {0,-1};
        storedState = false;
    }
    public void initiateChandy() throws IOException{
        this.sendMessage("MARKER");
        this.localState = this.functionalState;
        this.storedState = true;
    }

}
