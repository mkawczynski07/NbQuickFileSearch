package pl.nazaweb.netbeans.quickfilesearch.startup;

import org.netbeans.api.project.ui.OpenProjects;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;
import pl.nazaweb.netbeans.quickfilesearch.options.DefaultIgnoredDirectoriesLoader;
import pl.nazaweb.netbeans.quickfilesearch.options.Options;
import pl.nazaweb.netbeans.quickfilesearch.startup.listeners.ProjectPropertyChangeListener;
import pl.nazaweb.netbeans.quickfilesearch.startup.listeners.QuickSearchProjectGroupChangeListener;

/**
 *
 * @author naza
 */
public class Startup extends ModuleInstall {

    @Override
    public void restored() {
        OpenProjects.getDefault().addProjectGroupChangeListener(QuickSearchProjectGroupChangeListener.getInstance());
        initDefaultIgnoredDirectoriesList();
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                ProjectUtils.addFilesFromAllOpenProjectToCache();
                ProjectPropertyChangeListener.getInstance().refreshProjectsCount();
                OpenProjects.getDefault().addPropertyChangeListener(ProjectPropertyChangeListener.getInstance());
            }

        });
    }

    private void initDefaultIgnoredDirectoriesList() {
        if (Options.isIgnoredDirectoriesEmpty()) {
            DefaultIgnoredDirectoriesLoader.getInstance().initDefaultListToPrefs();
        }
    }

}
