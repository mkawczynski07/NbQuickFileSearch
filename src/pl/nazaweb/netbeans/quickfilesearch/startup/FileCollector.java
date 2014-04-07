package pl.nazaweb.netbeans.quickfilesearch.startup;

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
                System.out.println(">>> file "+file.getPath());
                files.put(file.getPath(), file);
            } else {
                addFolderFiles(new File(file.getPath()));
            }
        }
    }

    private void addFolderFiles(File file) throws IOException {
        if (file.isDirectory()) {
            for (File child : file.listFiles(new IgnoreFileFilter())) {
                addFolderFiles(child);
            }
        } else {
            FileObject fileObject = FileUtil.createData(file);
            System.out.println(">>> file "+fileObject.getPath());
            files.put(fileObject.getPath(), fileObject);
        }
    }

}
