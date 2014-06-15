/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nazaweb.netbeans.quickfilesearch.options;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.NbPreferences;

/**
 *
 * @author jonasz
 */
public class DefaultIgnoredDirectoriesLoader {

    private static DefaultIgnoredDirectoriesLoader INSTANCE;
    public List<String> defaultIgnoredDirectoriesList = new ArrayList<String>();

    public DefaultIgnoredDirectoriesLoader() {
        defaultIgnoredDirectoriesList.add("classes");
        defaultIgnoredDirectoriesList.add("build");
        defaultIgnoredDirectoriesList.add("target");
        defaultIgnoredDirectoriesList.add("nbproject");
        defaultIgnoredDirectoriesList.add(".git");
        defaultIgnoredDirectoriesList.add(".svn");
    }

    public static DefaultIgnoredDirectoriesLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultIgnoredDirectoriesLoader();
        }
        return INSTANCE;
    }

    public void initDefaultListToPrefs() {
        String ignoredDirectories = "";
        DefaultIgnoredDirectoriesLoader defaultContainer = new DefaultIgnoredDirectoriesLoader();
        for (String defaultIgnored : defaultContainer.defaultIgnoredDirectoriesList) {
            ignoredDirectories += ";" + defaultIgnored;
        }
        NbPreferences.forModule(Options.class).put("ignoredDirectories", ignoredDirectories);

    }

}
