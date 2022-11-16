import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Defina uma porta para o Servidor: ");
        String port = scanner.nextLine();
        if (isNumeric(port)) {
            Server server = new Server(Integer.parseInt(port));
            server.openConnection();
        } else {
            System.out.println("O valor da porta está invalido");
        }

        System.out.print("Pressione a tecla Enter para fechar...");
        try {
            System.in.read();
            scanner.nextLine();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        scanner.close();
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }
}
