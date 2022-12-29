package Server;

import Common.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;


import static Server.Server.clientHandlers;

public class ClientHandler implements Runnable {

    final protected Socket clientSock;
    final protected DataInputStream inStream;
    final protected DataOutputStream outStream;
    int threadId;

    List<Message> messages;
    Users users;
    PrintMsg printMsg;

    ClientHandler(Socket clientSock, DataInputStream inStream, DataOutputStream outStream, int threadId, List<Message> messages, Users users, PrintMsg printMsg) {
        this.clientSock = clientSock;
        this.inStream = inStream;
        this.outStream = outStream;
        this.threadId = threadId;
        this.messages = messages;
        this.users = users;
        this.printMsg = printMsg;
    }
    int getThreadId() {
        return threadId;
    }
    void sendMsgAll(String msg, int threadId) throws IOException {
        for(ClientHandler ch : clientHandlers) {
            if(ch.threadId != threadId ) {
                ch.outStream.writeUTF(msg);
            }
        }
    }
@Override
    public void run() {
        try {
            String clientMessage = "";
            String serverMessage = "";
            String activeUserName = "";
            while(!clientMessage.equalsIgnoreCase("exit")) {

                if(activeUserName != "" ) {
                    clientMessage = inStream.readUTF();

                    if(!clientMessage.equalsIgnoreCase("exit")) {

                        messages.add(new Message(threadId, clientMessage));
                        printMsg.print("Пользователь : " + activeUserName + " Сообщение : " + clientMessage);

                        sendMsgAll(clientMessage, threadId);

                        outStream.flush();
                    }
                } else {

                    outStream.writeUTF("Введите имя пользователя для подключения :");
                    outStream.flush();

                    activeUserName = inStream.readUTF();

                    printMsg.print("Пользователь : " +activeUserName + " подключен к чату");
                    users.addUser(activeUserName, threadId);

                    outStream.writeUTF("Добро пожаловать в чат "+activeUserName+" !!!" );
                    sendMsgAll("Пользователь "+activeUserName+" подключился к чату !!!", threadId);

                    for(Message m : messages) {
                        outStream.writeUTF(m.getMessage());
                    }

                    outStream.flush();
                }

            }
//            inStream.close();
//            outStream.close();
//            clientSock.close();
        } catch (Exception ex) {
            //System.out.println(ex);
            throw new RuntimeException(ex);
        } finally {
            try {
                inStream.close();
                outStream.close();
                clientSock.close();

                clientHandlers.remove(this);
                printMsg.print("Завершение работы клиента id:" + threadId);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
}

