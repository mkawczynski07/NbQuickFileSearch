package pl.nazaweb.netbeans.quickfilesearch.search.strategy;

import java.util.HashMap;
import java.util.Map;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

/**
 *
 * @author naza
 */
public class CamelCaseNGTest {

    private final static Map<String, String> TEST_DATA = new HashMap();

    static {
//        TEST_DATA.put("", null)
    }

    @Test
    public void testTest() {
        System.out.println("test");
        String text = "";
        FileItem file = null;
        CamelCase instance = new CamelCase();
        boolean expResult = false;
        boolean result = instance.test(text, file);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
