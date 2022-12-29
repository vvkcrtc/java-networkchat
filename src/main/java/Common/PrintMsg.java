package Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintMsg {
    private Logger logger;// = new Logger()
    public PrintMsg(String logFileName) {
        logger = new Logger(logFileName);
    }
    public synchronized void print(String text) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dt = new Date();
        String msg = dateFormat.format(dt) + " " +text;
        System.out.println(msg);
        logger.putText(msg);
    }
}
