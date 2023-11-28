package gameclient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;


public class inputThClient extends Thread {

    private Socket socket;
    private BufferedReader dInpt;


    public inputThClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try  {
            if(socket.isConnected() && !socket.isClosed()) {

                InputStream inputStream = socket.getInputStream();
                dInpt = new BufferedReader(new InputStreamReader(inputStream));
                //debug code 0
//                System.out.println("successfully recevd!");

            } else{
                //debug code 1
//                System.out.println("fail to get Data!");
            }

            while (dInpt != null) {

                String recvdMsg = dInpt.readLine();
                System.out.println("서버에서 온 메세지 : " + recvdMsg);

            }

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
