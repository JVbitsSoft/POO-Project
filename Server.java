import java.net.ServerSocket;

import java.io.IOException;

public class Server {
    private ServerSocket serverSocket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void openConnection() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Esperando conexão na porta " + String.valueOf(port) + " (Ctrl+C para fechar o Servidor).");
            while (true) {
                new ServerThread(this.serverSocket.accept());
            }
        } catch (IOException e) {
            System.out.println("Não foi possivel iniciar o servidor. Outro serviço pode estar fazendo uso dessa porta.");
            //e.printStackTrace();
        }
    }
}
