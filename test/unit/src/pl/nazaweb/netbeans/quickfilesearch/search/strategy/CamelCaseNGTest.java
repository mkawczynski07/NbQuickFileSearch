package pl.nazaweb.netbeans.quickfilesearch.search.strategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

/**
 *
 * @author naza
 */
public class CamelCaseNGTest {

    private final static List<String> TEST_DATA = new ArrayList();

    static {
        TEST_DATA.add("NewClass.java");
        TEST_DATA.add("NowaKlasa.java");
        TEST_DATA.add("Order.js");
        TEST_DATA.add("new_File.conf");
        TEST_DATA.add("Order.java");
        TEST_DATA.add("Newworld.java");
        TEST_DATA.add("NoClassKl.java");
        TEST_DATA.add("NyxKlo.java");
    }
    private CamelCase instance;

    @BeforeMethod
    public void init() {
        instance = new CamelCase();
    }

    @Test
    public void shouldReturnOnlyFilesMatchTo_Ne() {
        assertEquals(testData("Ne"), 2);
    }

    @Test
    public void shouldReturnOnlyFilesMatchTo_NC() {
        assertEquals(testData("NC"), 2);
    }

    @Test
    public void shouldReturnOnlyFilesMatchTo_NK() {
        assertEquals(testData("NK"), 3);
    }

    public void shouldReturnOnlyFilesMatchTo_NstarK() {
        assertEquals(testData("N*K"), 3);
    }

    @Test
    public void shouldReturnOnlyFilesMatchTo_F() {
        assertEquals(testData("F"), 1);
    }

    @Test
    public void shouldReturnOnlyFilesMatchTo_NeC() {
        assertEquals(testData("NeC"), 1);
    }

    @Test
    public void shouldReturnOnlyJavaFiles() {
        assertEquals(testData("*.java"), 6);
    }

    private int testData(String pattern) {
        int count = 0;
        count = TEST_DATA.stream()
                .filter((file)
                        -> (instance.test(pattern, new FileItem(new File(file), "project"))))
                .map((_item) -> 1)
                .reduce(count, Integer::sum);
        return count;
    }

}
