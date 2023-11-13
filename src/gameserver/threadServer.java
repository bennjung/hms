package gameserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.IOException;

public class threadServer extends Thread {
    //connected client socket
    Socket socket = null;

    // received data from client
    BufferedReader in = null;
    // return data to client
    BufferedWriter out = null;
    int clSize = 0;
    // constructor
    public threadServer(Socket socket, int clSize) {
        this.socket = socket;
        this.clSize = clSize;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if(clSize == 1) {


        }



    }
}
