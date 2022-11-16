import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.net.ServerSocket;

import java.io.IOException;

public class Server {
    private ServerSocket serverSocket;
    final private int port = 9876;

    public void openConnection() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Open Connection (Ctrl+C to close Server).");
            while (true) {
                new ServerThread(this.serverSocket.accept());
            }
        } catch (IOException e) {
            System.out.println("Could not open connection");
            //e.printStackTrace();
        }
    }
}
