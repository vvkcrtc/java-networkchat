package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static int LOCAL_PORT = 1;
    private static String IP_ADDRESS;
    private static String LOG_FILENAME;

    protected String configFile;
    public Config(String configFile) throws IOException {

        this.configFile = configFile;
        readConfig();
    }
    void readConfig() throws IOException {
        Properties props = new Properties();
        File conf = new File(configFile);
//        props.load(new FileInputStream(new File(configFile)));
        props.load(new FileInputStream(conf));
        LOCAL_PORT = Integer.parseInt(props.getProperty("LOCAL_PORT", "1"));
        IP_ADDRESS = props.getProperty("IP_ADDRESS");
        LOG_FILENAME = props.getProperty("LOG_FILENAME");
    }
    public String getIPAddr() {
        return IP_ADDRESS;
    }
    public int getLocalPort() {
        return LOCAL_PORT;
    }
    public String getLogFilename() { return LOG_FILENAME; }

}

