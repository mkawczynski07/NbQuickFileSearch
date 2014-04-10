package pl.nazaweb.netbeans.quickfilesearch.files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author naza
 */
public class FileCollector {

    private Map<String, FileObject> files;

    public Map<String, FileObject> findFilesInOpenProjects() throws IOException {
        files = new HashMap();

        for (Project project : getOpenProjects()) {
            addProjectFiles(project);
        }

        return files;
    }

    private Project[] getOpenProjects() {
        return OpenProjects.getDefault().getOpenProjects();
    }

    private void addProjectFiles(Project project) throws IOException {
        for (FileObject file : project.getProjectDirectory().getChildren()) {
            if (file.isFolder() == false) {
                putFile(file);
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
            putFile(fileObject);
        }
    }

    private void addFolderChildrens(File file) throws IOException {
        for (File child : file.listFiles(new IgnoreFileFilter())) {
            addFolderFiles(child);
        }
    }

    private void putFile(FileObject file) {
        files.put(file.getPath(), file);
    }

    //TODO: create configuration for ingoring directories
    private boolean shouldIgnoreFolder(File folder) {
        String folderName = folder.getName().toLowerCase();
        return folderName.equals("classes")
                || folderName.equals("build")
                || folderName.equals("target")
                || folderName.equals("nbproject");
    }

}
