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
        LOCAL_PORT = Integer.valueOf(props.getProperty("LOCAL_PORT", "1"));
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

/*
* public class Example1
{
	private static int SOME_INT_VALUE = 1;
	private static String SOME_STRING_VALUE;
	private static int[] SOME_INT_ARRAY;
	private static double SOME_DOUBLE_VALUE;

	public Example1() throws IOException
	{
		Properties props = new Properties();
		props.load(new FileInputStream(new File("config/example.ini")));

		SOME_INT_VALUE = Integer.valueOf(props.getProperty("SOME_INT_VALUE", "1"));
		SOME_STRING_VALUE = props.getProperty("SOME_STRING_VALUE");
		SOME_DOUBLE_VALUE = Double.valueOf(props.getProperty("SOME_DOUBLE_VALUE", "1.0"));

		// Предположим, что в настройках находится список целых через точку с запятой
		String[] parts = props.getProperty("SOME_INT_ARRAY").split(";");
		SOME_INT_ARRAY = new int[parts.length];
		for (int i = 0; i < parts.length; ++i)
		{
			SOME_INT_ARRAY[i] = Integer.valueOf(parts[i]);
		}
	}
* */