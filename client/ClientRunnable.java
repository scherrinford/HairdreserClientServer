import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunnable implements Runnable {

    private Socket socket;
    private BufferedReader input;

    public ClientRunnable(Socket s) throws IOException {
        this.socket = s;
        this.input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {
        
            try {
                while(true) {
                    String response = input.readLine();
                    if(response==null)break;
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    
}
