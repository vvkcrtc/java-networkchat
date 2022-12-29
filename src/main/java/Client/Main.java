package Client;

public class Main {
    public static void main(String[] args) throws Exception {
        String configFileName = "config.txt";
        if(args.length != 0) {
            configFileName = args[0];
        }
        Client client = new Client(configFileName);
        client.run();
    }
}
