package gameserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class testServer {
    private ServerSocket servSocket ;
    private Socket clSocket ;

    public int clCount = 0;

    List<threadServer> clList;
    //Constructor
    public testServer(ServerSocket servSocket) {
        this.servSocket = servSocket;
        clList = new ArrayList<>();


    }
    public void startServer() {
        try{

            System.out.println("서버가 켜졌습니다");

            while(true){
                clSocket = servSocket.accept();

                //Get client Ip address
                String clName = clSocket.getInetAddress().getHostAddress();
                System.out.println("클라이언트가 서버에 접속했습니다" + "\n" + "접속 주소:" + clName);

                threadServer thread = new threadServer(this,clSocket);
                thread.start();
                // clients thread add and delete in list when they connect and disconnect
                clList.add(thread);
                clCount++;

                    // [ADD] Need clCount reset condition o
                    // [ADD] When clients disconnect, the Clcount -=
//                    clSocket.close();

//                    System.out.println("클라이언트 접속 제한량 도달됨!");

                }

            }

         catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(servSocket != null) {
                try{
                    servSocket.close();
                    System.out.println("서버 종료");


                } catch(IOException e) {
                    e.printStackTrace();
                    System.out.println("서버 종료에 오류가 발생했습니다.");

                }

            }

        }

    }
    public int getClCount() {
        return clCount;
    }
    public synchronized void broadcast(String msg) {
        for(threadServer thread : clList) {
            thread.SendMsgCl1(msg);
        }

    }

    public synchronized void storeMsg() {
        if(clCount == 2) {
            for(threadServer thread : clList) {
                String toto = thread.getClMsg();
                if(toto.equalsIgnoreCase("가위")) {
                    System.out.println("가위");
                } else if(toto.equalsIgnoreCase("바위")) {
                    System.out.println("바위");
                } else if(toto.equalsIgnoreCase("보")) {
                    System.out.println("보");
                } else {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }

    }



}
