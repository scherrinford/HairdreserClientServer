import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) {

        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket("localhost", 5000);
            //reading the input from server
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            //returning the output to the server : true statement is to flush the buffer otherwise
            //we have to do it manuallyy
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            ClientStart clientStart = new ClientStart(socket, input, output);
            clientStart.runClient();

            //taking the user input
   /*         Scanner scanner = new Scanner(System.in);
            String userInput = "";
            String response;
            String clientName = "empty";

            ClientRunnable clientRun = new ClientRunnable(socket);

            Thread listeningMessage =  new Thread(clientRun);
            listeningMessage.start(); //watek obsługujacy powiadomienia od serwera
           //loop closes when user enters exit command

            while (true){

               if (clientName.equals("empty")) {
                    System.out.println("Enter your name ");
                    userInput = scanner.nextLine();
                    clientName = userInput;
                    output.println(userInput); //wysyła imie na serwer
                    if (userInput.equals("0")) {
                        break;
                    }
               }
               else {
                    //String message = ( "(" + clientName + ")" + " message : " );
                    System.out.println("Wybierz usluge: \n" + "1 - Umow \n" + "2 - Odwolaj \n" + "0 - Wyjdz \n");
                    userInput = scanner.nextLine();
                    output.println(userInput);
                    if(userInput.equals("1") || userInput.equals("2")){

                        System.out.println("Podaj godzine: ");
                        output.println(scanner.nextLine());
                        System.out.println(input.readLine());
                    }
                    else if (userInput.equals("0")) {
                        break;
                    }
                }

           }

            listeningMessage.interrupt();
            input.close();
            output.close();*/


        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
        }


    }
}