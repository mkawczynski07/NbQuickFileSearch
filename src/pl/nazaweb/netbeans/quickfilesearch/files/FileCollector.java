package pl.nazaweb.netbeans.quickfilesearch.files;

import java.io.File;
import java.io.IOException;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author naza
 */
public class FileCollector {

    public void addProjectFiles(Project project) throws IOException {
        for (FileObject file : project.getProjectDirectory().getChildren()) {
            if (file.isFolder() == false) {
                addFile(file);
            } else {
                addDirectoryFiles(new File(file.getPath()));
            }
        }
    }

    private void addDirectoryFiles(File file) throws IOException {
        if (file.isDirectory()) {
            if (shouldIgnoreDirectory(file) == false) {
                addDirectoryChildrens(file);
            }
        } else {
            FileObject fileObject = FileUtil.createData(file);
            addFile(fileObject);
        }
    }

    private void addDirectoryChildrens(File file) throws IOException {
        for (File child : file.listFiles(new IgnoreFileFilter())) {
            addDirectoryFiles(child);
        }
    }

    private void addFile(FileObject file) {
        try {
            FileCache.getInstance().addFile(file);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private boolean shouldIgnoreDirectory(File directory) {
        return new IgnoreDirectoryFilter().accept(directory) == false;
    }

}
