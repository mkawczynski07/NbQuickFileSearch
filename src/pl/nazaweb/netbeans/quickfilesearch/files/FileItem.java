package pl.nazaweb.netbeans.quickfilesearch.files;

import java.io.File;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/**
 *
 * @author naza
 */
public class FileItem implements Runnable {

    private final File file;
    private final String name;
    private final String path;
    private final String projectName;

    public FileItem(File file, String projectName) {
        this.file = file;
        this.name = file.getName();
        this.path = file.getPath();
        this.projectName = projectName;
    }

    @Override
    public void run() {
        try {
            openFileInEditor(file);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void openFileInEditor(File file) throws DataObjectNotFoundException {
        DataObject.find(FileUtil.toFileObject(file)).
                getLookup().lookup(OpenCookie.class).open();
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getProjectName() {
        return projectName;
    }

    public String display() {
        return String.format("%s (%s)",
                name,
                path.substring(path.indexOf(projectName)));
    }

}
