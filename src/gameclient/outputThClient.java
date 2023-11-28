package gameclient;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class outputThClient extends  Thread{

    // return data to client
    private PrintWriter dOutpt ;
    private Socket socket;

    private Scanner sc = new Scanner(System.in);

    public outputThClient(Socket socket) {
        this.socket = socket;
//        try{
//            dOutpt = new PrintWriter(socket.getOutputStream());
//
//        } catch(IOException e){
//            e.printStackTrace();
//
//        }
    }
    @Override
    public void run() {
        try{
            dOutpt = new PrintWriter(socket.getOutputStream());
            System.out.print("닉네임을 입력하세요: ");
            sendMsg((sc.nextLine()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            String clMsg = sc.nextLine();
            sendMsg(clMsg);
        }
    }
    private void sendMsg(String msg){
//        dOutpt.write(msg + "\n");
        dOutpt.println(msg);
        dOutpt.flush();
    }
}
