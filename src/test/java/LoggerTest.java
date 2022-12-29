import Common.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LoggerTest {
    @Test
    public void testWriteReadLog() {
        List<String> testList = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            testList.add("Text message : "+i);
        }
        Logger log = new Logger("TestLog.txt");
        log.cleanLog();

        for(String st : testList) {
            log.putText(st);
        }

        List<String> readList = log.getFromLog();

        Assertions.assertLinesMatch(testList,readList,"Error read write file operation");
    }
}
