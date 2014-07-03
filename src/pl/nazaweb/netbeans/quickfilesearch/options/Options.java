package pl.nazaweb.netbeans.quickfilesearch.options;

import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import org.openide.util.NbPreferences;

/**
 *
 * @author jonasz
 */
public class Options {

    public static List<String> getIgnoredFiles() {
        return Arrays.asList(NbPreferences.forModule(Options.class).get("ignoredDirectories", "").split(";"));
    }

    public static boolean isIgnoredDirectoriesEmpty() {
        return NbPreferences.forModule(Options.class).get("ignoredDirectories", "empty").equals("empty");
    }

    public static void storeIgnoredDirectories(DefaultListModel<String> model) {
        String ignoredDirectories = "";
        for (int i = 0; i < model.getSize(); i++) {
            ignoredDirectories += ";" + model.getElementAt(i);
        }
        NbPreferences.forModule(Options.class).put("ignoredDirectories", ignoredDirectories);
    }

}
