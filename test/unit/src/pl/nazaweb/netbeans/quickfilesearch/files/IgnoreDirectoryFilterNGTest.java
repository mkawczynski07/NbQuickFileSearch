package pl.nazaweb.netbeans.quickfilesearch.files;

import java.io.File;
import javax.swing.DefaultListModel;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.nazaweb.netbeans.quickfilesearch.options.Options;

/**
 *
 * @author naza
 */
public class IgnoreDirectoryFilterNGTest {

    private IgnoreDirectoryFilter instance;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        instance = new IgnoreDirectoryFilter();
        mockDefualtOptions();
    }

    @Test
    public void shouldDontIgnore() {
        File directory = new File("src");
        assertTrue(instance.accept(directory));
    }

    @Test
    public void shouldIgnoreExtraDirectory() {
        DefaultListModel<String> options = new DefaultListModel<String>();
        String directoryPath = "temp";
        options.addElement(";" + directoryPath);
        Options.storeIgnoredDirectories(options);
        File directory = new File("temp");
        assertFalse(instance.accept(directory));
    }

    @Test
    public void shouldIgnoreDefaultDirectories() {
        File directory = new File("classes");
        assertFalse(instance.accept(directory));
        directory = new File("build");
        assertFalse(instance.accept(directory));
        directory = new File("target");
        assertFalse(instance.accept(directory));
        directory = new File(".git");
        assertFalse(instance.accept(directory));
        directory = new File(".svn");
        assertFalse(instance.accept(directory));
        directory = new File("nbproject");
        assertFalse(instance.accept(directory));
    }

    @Test
    public void shouldDontIgnoreNestedDirectoryNamedLikeIgnored() {
        File directory = new File("src" + File.separator + "pl" + File.separator + "target");
        assertTrue(instance.accept(directory));
    }

    @Test
    public void shouldIgnoreNestedDirectory() {
        DefaultListModel<String> options = new DefaultListModel<String>();
        String directoryName = "tests";
        String directoryPath = "src" + File.separator + "pl" + File.separator + directoryName;
        options.addElement(";" + directoryPath);
        Options.storeIgnoredDirectories(options);

        File directory = new File(directoryName);
        assertTrue(instance.accept(directory));
        directory = new File(directoryPath);
        assertFalse(instance.accept(directory));

    }

    private void mockDefualtOptions() {
        DefaultListModel<String> options = new DefaultListModel<String>();
        options.addElement("classes");
        options.addElement("build");
        options.addElement("target");
        options.addElement(".git");
        options.addElement(".svn");
        options.addElement("nbproject");
        Options.storeIgnoredDirectories(options);
    }

}
