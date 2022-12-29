import Common.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ConfigTest {
    @Test
    public void testReadConfig() throws IOException {
/*
        File folder = new File("src/t");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

*/

        Config conf = new Config("src/test/java/config.txt");
        String ipAddr = "127.0.0.1";
        int port = 8888;
        String tmp = conf.getIPAddr();
        int portRead = conf.getLocalPort();
        Assertions.assertEquals(port, portRead);
        Assertions.assertTrue(ipAddr.equals(tmp));


    }
}
