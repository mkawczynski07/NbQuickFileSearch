package pl.nazaweb.netbeans.quickfilesearch.files;

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

    public void addFile(FileObject object) {
        System.out.println("addding file : " + object.getPath());
        files.put(object.getPath(), object);
    }

    public void removeFile(String path) {
        System.out.println("remove : " + path);
        files.remove(path);
    }

    public Map<String, FileObject> getFiles() {
        return files;
    }

    public void init() throws IOException {
        files = new FileCollector().findFilesInOpenProjects();
    }

}
