package Common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    protected String filePath;

    public Logger(String filePath) {
        this.filePath = filePath;
    }

    public void cleanLog() {
        try {
            new FileWriter(filePath, false).close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void putText(String text) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(text); bw.newLine();
            bw.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<String> getFromLog() {
        List<String> result = new ArrayList<>();

        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
                result.add(line);
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return result;
    }

}
