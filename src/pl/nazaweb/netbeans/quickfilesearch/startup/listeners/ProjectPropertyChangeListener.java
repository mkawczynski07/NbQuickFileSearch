package pl.nazaweb.netbeans.quickfilesearch.startup.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.netbeans.api.project.ui.OpenProjects;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.startup.ProjectUtils;

/**
 *
 * @author naza
 */
public class ProjectPropertyChangeListener implements PropertyChangeListener {

    private static ProjectPropertyChangeListener INSTANCE;
    private int projectsCount = 0;

    private ProjectPropertyChangeListener() {
    }

    public static ProjectPropertyChangeListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProjectPropertyChangeListener();
        }
        return INSTANCE;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (projectsCount != getCurrentProjectCounts()) {
            System.out.println(">>>>>>>> refresh catch <<<<<<<<<<");
            ProjectUtils.addFilesFromAllOpenProjectToCache();
            refreshProjectsCount();
        }
    }

    public void refreshProjectsCount() {
        projectsCount = getCurrentProjectCounts();
    }

    private int getCurrentProjectCounts() {
        return OpenProjects.getDefault().getOpenProjects().length;
    }

}
