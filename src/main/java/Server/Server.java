package Server;

import Common.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class Server {
    protected static String configFileName;
    protected static List<Message> messages = new ArrayList<>();
    protected static Users users = new Users();
    protected static PrintMsg printMsg;

    static Vector<ClientHandler> clientHandlers = new Vector<>();
    public Server(String configFileName) {
        this.configFileName = configFileName;
    }

    public static void run() throws Exception {
        try {
            Config config = new Config(configFileName);
            printMsg = new PrintMsg(config.getLogFilename());
            ServerSocket serverSock = new ServerSocket(config.getLocalPort());
            int userId = 0;
            printMsg.print("Сервер запущен ....");
            while(true) {
                Socket clientSock = serverSock.accept();
                printMsg.print("Запрос на подключение клиента : " + clientSock);

                DataInputStream inStream = new DataInputStream(clientSock.getInputStream());
                DataOutputStream outStream = new DataOutputStream(clientSock.getOutputStream());

                ClientHandler clientHandler = new ClientHandler(clientSock, inStream, outStream, userId, messages, users, printMsg);
                Thread clientThread = new Thread(clientHandler);
                clientHandlers.add(clientHandler);
                printMsg.print("Клиент : "+userId+" запущен поток обработки");
                clientThread.start();

                userId++;
            }
        } catch (Exception e) {
            //System.out.println(e);
            e.printStackTrace();
        }
    }
}
