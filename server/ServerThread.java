
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {

    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;
    BufferedReader input;
    Hairdresser timetable;
    HairdreserClient client = new HairdreserClient();

    public ServerThread(Socket socket, ArrayList<ServerThread> threads, Hairdresser timetable) {
        this.socket = socket;
        this.threadList = threads;
        this.timetable = timetable;
    }

    @Override
    public void run() {
        try {

            input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),true);

            while(true) {
                String outputString = input.readLine();
                if(outputString.equals("0")) { closeSocket();  break; }
                if(client.getName()==null){
                    client.setName(outputString);
                    printToALlClients("Zajete terminy: " + timetable.getReservedDate().toString() + "\n"); //wysyla informacje do wszystkich klientów
                }
                else {
                    if(outputString.equals("1")) bookVisit();
                    else if(outputString.equals("2")) cancelVisit();
                }
                System.out.println("Server received " + outputString);
            }
            input.close();
            output.close();

        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void printToALlClients(String outputString) throws IOException {
        for( ServerThread sT: threadList) {
            sT.output.println(outputString);
        }
    }

    public void bookVisit() throws IOException, InterruptedException {
        if(client.setHour(Integer.parseInt(input.readLine()))){
            if(timetable.isFree(client.getHour())){
                timetable.addElement(client.getHour(),client.getName());
                output.println("Termin zarezerwowany" + "\n");
                printToALlClients("Zajete terminy: " + timetable.getReservedDate().toString() + "\n"); //wysyla informacje do wszystkich klientów
            }else output.println("Termin zajety");
        }else output.println("Nieprawidlowa godzina");
    }

    public void cancelVisit() throws IOException {
        int hour = Integer.parseInt(input.readLine());
        if(timetable.isBookedByName(hour,client.getName())==0){
            if(!timetable.isFree(hour)){
                timetable.cancelVisit(hour);
                output.println("Wizyta na godzine" + " " + hour + " " + " anulowana" + "\n");
                printToALlClients("Zajete terminy: " + timetable.getReservedDate().toString() + "\n"); //wysyla informacje do wszystkich klientów
            } output.println("Ta godzina nie jaet zarezerwowana");
        }else output.println("Nie masz zarezerwowanej wizyty na ta godzine");
    }

    public void closeSocket(){
        try
        {
            System.out.println("Client " + this.socket + " sends exit...");
            System.out.println("Closing this connection.");
            this.socket.close();
            System.out.println("Connection closed");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
