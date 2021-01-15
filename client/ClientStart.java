import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientStart {

    Socket socket;
    BufferedReader input;
    PrintWriter output;
    String userInput = "";
    String clientName = "empty";
    Scanner scanner = new Scanner(System.in);

    public ClientStart(Socket socket, BufferedReader input, PrintWriter output) {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    public void runClient() throws IOException {

        ClientRunnable clientRun = new ClientRunnable(socket);
        new Thread(clientRun).start(); //watek obsługujacy powiadomienia od serwera

        while (true){
            if (clientName.equals("empty")) {  setClientName(); }
            else if (userInput.equals("0")) { closeConnection(); break;  }
            else { modifyVisit(); }
        }
        closeAll();
    }

    public void closeAll() throws IOException {
        input.close();
        output.close();
        scanner.close();
    }

    public void setClientName(){
        System.out.println("Podaj imie");
        userInput = scanner.nextLine();
        clientName = userInput;
        output.println(userInput); //wysyła imie na serwer
    }

    public void closeConnection() throws IOException {
        System.out.println("Closing this connection : " + socket);
        socket.close();
        System.out.println("Connection closed");
    }

    public void modifyVisit() throws IOException {
        System.out.println("Wybierz usluge: \n" + "1 - Umow \n" + "2 - Odwolaj \n" + "0 - Wyjdz \n");
        userInput = scanner.nextLine();
        output.println(userInput);
        if(userInput.equals("1") || userInput.equals("2")){
            System.out.println("Podaj godzine: ");
            output.println(scanner.nextLine());
            String uinput = input.readLine();
            System.out.println(uinput);
        }
    }

}
