package pl.nazaweb.netbeans.quickfilesearch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author naza
 */
public class SearchNGTest {

    @Test
    public void testSomeMethod() {
        mainTest();
    }

    @Test
    public void benchmark() {
        long sum = 0l;
        for (int x = 0; x < 100; x++) {
            sum += mainTest();
        }
        System.out.println("result: " + sum / 100);
    }

    private long mainTest() {
        Map<String, String> testMap = new HashMap();
        for (int x = 0; x < 30000; x++) {
            testMap.put("testString" + x, "" + x);
        }
        long startTime = System.currentTimeMillis();

        Map<String, String> result = new HashMap();
        for (Entry<String, String> entry : testMap.entrySet()) {
            if (entry.getKey().matches(".*[a-zA-Z]+.*")) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        assertEquals(result.size(), 30000);
        return elapsedTime;
    }

}
