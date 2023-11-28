package gameserver;
import java.io.IOException;
import java.net.ServerSocket;

public class testDriveServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1111);
        testServer ts = new testServer(ss);
        ts.startServer();


    }
}
