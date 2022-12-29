package Server;

public class Main {
    public static void main(String[] args) throws Exception {
        String configFileName = "config.txt";
        if(args.length != 0) {
            configFileName = args[0];
        }
        Server server = new Server(configFileName);
        server.run();
    }
}
