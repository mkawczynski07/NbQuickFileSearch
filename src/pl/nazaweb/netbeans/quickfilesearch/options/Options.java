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

    private final static String IGNORED_DIRECTORY_OPTIONS_NAME = "ignoredDirectories";

    public static List<String> getIgnoredFiles() {
        return Arrays.asList(NbPreferences.forModule(Options.class).get(IGNORED_DIRECTORY_OPTIONS_NAME, "").split(";"));
    }

    public static boolean isIgnoredDirectoriesEmpty() {
        return NbPreferences.forModule(Options.class).get(IGNORED_DIRECTORY_OPTIONS_NAME, "empty").equals("empty");
    }

    public static void storeIgnoredDirectories(DefaultListModel<String> model) {
        String ignoredDirectories = "";
        for (int i = 0; i < model.getSize(); i++) {
            ignoredDirectories += model.getElementAt(i);
            if (isLastElement(i, model)) {
                ignoredDirectories += ";";
            }
        }
        NbPreferences.forModule(Options.class).put(IGNORED_DIRECTORY_OPTIONS_NAME, ignoredDirectories);
    }

    private static boolean isLastElement(int i, DefaultListModel<String> model) {
        return i != model.getSize() - 1;
    }

}
