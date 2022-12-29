package Client;

import Common.Config;
import Common.PrintMsg;
import Common.User;

import java.io.*;
import java.net.Socket;
import java.util.Map;

class Client {
    protected static String configFileName;
    protected static boolean flagRun;
    protected static PrintMsg printMsg;

    public Client(String configFileName) {
        this.configFileName = configFileName;
/*
        long pid = ProcessHandle.current().pid();
        String logFileName = "client"+pid+".log.txt";
        printMsg = new PrintMsg(logFileName);
*/
        flagRun = false;
    }


    static class Receiver implements Runnable {
        DataInputStream inStream;
        String serverMessage = "";

        public Receiver(DataInputStream inStream) {
            this.inStream = inStream;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    serverMessage = inStream.readUTF();
                    printMsg.print(serverMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                    //System.out.println(e);
                    //e.printStackTrace();

                }

            }
        }
    }

    public static void run() throws Exception {
        try {
            Config config = new Config(configFileName);
            printMsg = new PrintMsg(config.getLogFilename());
            Socket socket = new Socket(config.getIPAddr(), config.getLocalPort());
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage="";
            flagRun = true;


            Thread serverThread = new Thread(new Receiver(inStream));
            serverThread.start();

            while(!clientMessage.equalsIgnoreCase("exit")) {
                clientMessage = br.readLine();
                printMsg.print(clientMessage);
                outStream.writeUTF(clientMessage);
                outStream.flush();
            }

            flagRun = false;
            serverThread.interrupt();

            outStream.close();
            inStream.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
