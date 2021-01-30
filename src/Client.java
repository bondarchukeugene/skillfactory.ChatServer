

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    Socket socket;
    Scanner in;
    PrintStream out;
    Server server;

    public Client(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
        new Thread(this).start();
    }

    public void receive (String message){
        out.println(message);
    }

    public void run(){
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            in = new Scanner(is);
            out = new PrintStream(os);


            out.println("Welcome to chat");
            String input = in.nextLine();

            while (!input.equals("buy")){
                server.sendAll(input);
                input = in.nextLine();
            }
            socket.close();

        }catch (IOException e){
            e.getStackTrace();

        }

    }
}

