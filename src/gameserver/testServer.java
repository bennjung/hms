package gameserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class testServer {
    ServerSocket servSocket = null;
    Socket clSocket = null;

    int clCount = 0;

    public void startServer() {
        try{
            servSocket = new ServerSocket(1111);

            while(true){
                clSocket = servSocket.accept();
                if (clCount <2) {

                    String clName = clSocket.getInetAddress().getHostAddress();
                    System.out.println("클라이언트가 서버에 접속했습니다" + "\n" + "접속 주소:" + clName);

                    threadServer thread = new threadServer(clSocket,clCount);
                    thread.start();
                    clCount++;
                } else {
                    clSocket.close();
                    System.out.println("접속 제한량 도달됨!");

                }

            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
