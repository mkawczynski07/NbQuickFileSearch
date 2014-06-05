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
                addFolderFiles(new File(file.getPath()));
            }
        }
    }

    private void addFolderFiles(File file) throws IOException {
        if (file.isDirectory()) {
            if (shouldIgnoreFolder(file) == false) {
                addFolderChildrens(file);
            }
        } else {
            FileObject fileObject = FileUtil.createData(file);
            addFile(fileObject);
        }
    }

    private void addFolderChildrens(File file) throws IOException {
        for (File child : file.listFiles(new IgnoreFileFilter())) {
            addFolderFiles(child);
        }
    }

    private void addFile(FileObject file) {
        try {
            FileCache.getIntance().addFile(file);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    //TODO: create configuration for ignoring directories
    private boolean shouldIgnoreFolder(File folder) {
        String folderName = folder.getName().toLowerCase();
        return folderName.equals("classes")
                || folderName.equals("build")
                || folderName.equals("target")
                || folderName.equals("nbproject")
                || folderName.equals(".git")
                || folderName.equals(".svn");
    }

}
