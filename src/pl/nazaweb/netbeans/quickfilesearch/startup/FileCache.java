package pl.nazaweb.netbeans.quickfilesearch.startup;

import java.io.IOException;
import java.util.Map;
import org.openide.filesystems.FileObject;

/**
 *
 * @author naza
 */
public class FileCache {

    private static FileCache INSTANCE;
    private Map<String, FileObject> files;

    private FileCache() {
    }

    public static FileCache getIntance() {
        if (INSTANCE == null) {
            INSTANCE = new FileCache();
        }
        return INSTANCE;
    }

    public void init() throws IOException {
        files = new FileCollector().findFilesInOpenProjects();
    }

}
