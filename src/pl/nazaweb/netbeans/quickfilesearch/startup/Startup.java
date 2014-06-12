package pl.nazaweb.netbeans.quickfilesearch.startup;

import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.api.project.ui.ProjectGroupChangeEvent;
import org.netbeans.api.project.ui.ProjectGroupChangeListener;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;
import pl.nazaweb.netbeans.quickfilesearch.startup.listeners.ProjectPropertyChangeListener;

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
                ProjectUtils.addFilesFromAllOpenProjectToCache();
                ProjectPropertyChangeListener.getInstance().refreshProjectsCount();
                OpenProjects.getDefault().addPropertyChangeListener(ProjectPropertyChangeListener.getInstance());
            }

        });
    }

    private void addProjectGroupChangeListener() {
        OpenProjects.getDefault().addProjectGroupChangeListener(new ProjectGroupChangeListener() {

            @Override
            public void projectGroupChanging(ProjectGroupChangeEvent pgce) {
                clearSharedData();
            }

            @Override
            public void projectGroupChanged(ProjectGroupChangeEvent pgce) {
                ProjectUtils.addFilesFromAllOpenProjectToCache();
            }
        });
    }

}
