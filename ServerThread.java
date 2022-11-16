import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.io.IOException;

public class ServerThread extends Thread {
    private static Map<String, ServerThread> mapClient = new ConcurrentHashMap<>(); // <username, serverThread>
    private Socket socket;
    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;
    private String username;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            String username = (String) objectInputStream.readObject();
            if (mapClient.containsKey(username)) {
                this.objectOutputStream.writeObject(false);
                
                this.objectInputStream.close();
                this.objectOutputStream.close();
                this.socket.close();
            } else {
                this.username = username;
                this.objectOutputStream.writeObject(true);
                mapClient.put(username, this);
                sendToAll(new Message("", this.username + " entrou na conversa"));
                start();
            }
        } catch (IOException e) {
            // e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message msg = (Message) this.objectInputStream.readObject();
                if (msg.username.equals(null)) {
                    this.objectInputStream.close();
                    this.objectOutputStream.close();
                    this.socket.close();
                    break;
                }
                sendToAll(msg);
            }
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        mapClient.remove(this.username);
        sendToAll(new Message("", this.username + " saio da conversa"));
    }

    private synchronized void sendToAll(Message msg) {
        for (String key : mapClient.keySet()) {
            try {
                mapClient.get(key).objectOutputStream.writeObject(msg);
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
    }
}
