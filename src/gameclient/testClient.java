package gameclient;
import java.net.Socket;
import java.io.IOException;
public class testClient {
    public Socket socket;

    public void startClient() {
        try{
            socket = new Socket("localhost",1111);
            System.out.println("서버에 연결되었습니다!");
            outputThClient otc = new outputThClient(socket);
            otc.start();
            inputThClient itc = new inputThClient(socket);
            itc.start();

            


        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("서버 접속 실패!");

        }
//        finally {
//            try{
//                if(socket != null) {
//                    socket.close();
//                    System.out.println("Client socket is closeed!!!!!");
//
//                }
//
//            } catch(IOException e) {
//                e.printStackTrace();
//
//            }
//        }

    }


}
