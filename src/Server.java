import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    Server () throws IOException {
        serverSocket = new ServerSocket(1234);
        //создание сервера, который начинеает слушать порт 1234
        //Когда мы запускаем NetClient он присоединяется к серверу
    }

    public void sendAll(String message){
        for (Client client:clients
        ) {
            client.receive(message);
        }
    }

    public void run (){
        while(true){
            System.out.println("Waiting");
            try {
                Socket socket = serverSocket.accept();
                //когда кто-то подсоеднияется к порту метод accept
                //воссоздает клиентский сокет (то есть объект socket)
                //это наше соединение. С его помощью мы создает на своей
                //стороне объект класса клиент
                System.out.println("Client connected");
                clients.add(new Client(socket,this));


                //создаем новое соединение и стартуем его в новом потоке
            }catch (IOException e){
                e.getStackTrace();

            }

        }
    }

    public static void main(String[] args) throws IOException {
        new Server().run();

    }

}
