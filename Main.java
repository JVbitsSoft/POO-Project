import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String port = scanner.nextLine();
        if (isNumeric(port)) {
            Server server = new Server(Integer.parseInt(port));
            server.openConnection();
        } else {
            System.out.println("Invalid value");
        }
        scanner.close();
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }
}
