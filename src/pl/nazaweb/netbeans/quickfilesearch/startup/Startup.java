package pl.nazaweb.netbeans.quickfilesearch.startup;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.api.project.ui.ProjectGroupChangeEvent;
import org.netbeans.api.project.ui.ProjectGroupChangeListener;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.windows.WindowManager;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCollector;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.FileChangeWatcher;

/**
 *
 * @author naza
 */
public class Startup extends ModuleInstall {

    @Override
    public void restored() {
        addProjectGroupChangeListener();
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                initOpenProjectsFiles();
            }

        });
    }

    private void initOpenProjectsFiles() {
        for (Project project : getOpenProjects()) {

            Path path = Paths.get(project.getProjectDirectory().getPath());
            addProjectToFileCache(project);

            try {
                addExistingFilesToFileCache(project);
                runFileWatcher(path);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        }
    }

    private void addProjectGroupChangeListener() {
        OpenProjects.getDefault().addProjectGroupChangeListener(new ProjectGroupChangeListener() {

            @Override
            public void projectGroupChanging(ProjectGroupChangeEvent pgce) {
                clearSharedData();
            }

            @Override
            public void projectGroupChanged(ProjectGroupChangeEvent pgce) {
                clearCache();
                initOpenProjectsFiles();

            }
        });
    }

    private Project[] getOpenProjects() {
        return OpenProjects.getDefault().getOpenProjects();
    }

    private void runFileWatcher(Path path) throws IOException {
        FileChangeWatcher watcher = new FileChangeWatcher(path);
        watcher.watch();
    }

    private void addExistingFilesToFileCache(Project project) throws IOException {
        new FileCollector().addProjectFiles(project);
    }

    private void addProjectToFileCache(Project project) {
        FileCache.getInstance().addProject(project.getProjectDirectory().getPath());
    }

    private void clearCache() {
        FileCache.getInstance().clear();
    }

}
