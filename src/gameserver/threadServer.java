package gameserver;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


public class threadServer extends Thread {
    //connected client socket
    private testServer ts;
    private Socket socket ;
    // received data from client
    private BufferedReader dInpt ;
    // return data to client
    private PrintWriter dOutpt ;
    private String clName ;

    private String clMsg = null;

    private Map<Socket, String> clMap = Collections.synchronizedMap(new HashMap<>());



    // constructor
    public threadServer(testServer ts, Socket socket) {
        this.ts = ts;
        this.socket = socket;
        try{
            dInpt = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dOutpt = new PrintWriter(socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    // server main method
    public void run() {


        try {
            clName = dInpt.readLine();
            ts.broadcast("["+clName+"]님이 입장하셨습니다.");
            if(ts.getClCount() == 1){
                SendMsgCl1("다른 플레이어를 기다리고 있습니다.");
            } else if(ts.getClCount() == 2) {
                ts.broadcast("게임을 시작합니다");
                ts.broadcast("가위 바위 보 중 한개만 선택해주세요");
                //game start
                while((clMsg = dInpt.readLine()) != null) {
                    System.out.println( clName+"로부터 받은 메세지: "+clMsg);
                }
            }


        } catch(IOException e3) {
            e3.printStackTrace();
        }

//                while(true) {
//
//                    String clData = dInpt.readLine();
//                    // Not used clSize for control
//                }
//
//            }
    }

    // send msg to all connected client's socket with outputstream
    public void SendMsgCl1(String msg) {
        dOutpt.println(msg);
        dOutpt.flush();
    }

    public String getClMsg() {
        return clMsg;
    }
}
