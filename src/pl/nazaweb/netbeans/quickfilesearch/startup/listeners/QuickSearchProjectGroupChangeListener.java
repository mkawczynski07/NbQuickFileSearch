/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nazaweb.netbeans.quickfilesearch.startup.listeners;

import org.netbeans.api.project.ui.ProjectGroupChangeEvent;
import org.netbeans.api.project.ui.ProjectGroupChangeListener;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.startup.ProjectUtils;

/**
 *
 * @author jonasz
 */
public class QuickSearchProjectGroupChangeListener implements ProjectGroupChangeListener {

    private static QuickSearchProjectGroupChangeListener INSTANCE;

    public QuickSearchProjectGroupChangeListener() {
    }

    public static QuickSearchProjectGroupChangeListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QuickSearchProjectGroupChangeListener();
        }
        return INSTANCE;
    }

    @Override
    public void projectGroupChanging(ProjectGroupChangeEvent pgce) {
        FileCache.getInstance().clear();
    }

    @Override
    public void projectGroupChanged(ProjectGroupChangeEvent pgce) {
        ProjectUtils.addFilesFromAllOpenProjectToCache();
    }

}
