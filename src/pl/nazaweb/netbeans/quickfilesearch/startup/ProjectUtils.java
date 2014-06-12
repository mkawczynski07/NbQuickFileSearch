package pl.nazaweb.netbeans.quickfilesearch.startup;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.util.Exceptions;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCollector;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.FileChangeWatcher;

/**
 *
 * @author naza
 */
public class ProjectUtils {

    public static void addFilesFromAllOpenProjectToCache() {
        FileCache.getInstance().clear();

        for (Project project : getOpenProjects()) {

            Path path = Paths.get(project.getProjectDirectory().getPath());
            addProjectToFileCache(project);

            try {
                addProjectFilesToFileCache(project);
                runFileWatcher(path);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        }
    }

    public static Project[] getOpenProjects() {
        return OpenProjects.getDefault().getOpenProjects();
    }

    public static void runFileWatcher(Path path) throws IOException {
        new FileChangeWatcher(path).watch();
    }

    public static void addProjectFilesToFileCache(Project project) throws IOException {
        new FileCollector().addProjectFiles(project);
    }

    public static void addProjectToFileCache(Project project) {
        FileCache.getInstance().addProject(project.getProjectDirectory().getPath());
    }

}
