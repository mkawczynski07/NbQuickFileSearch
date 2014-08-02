package pl.nazaweb.netbeans.quickfilesearch.files;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.util.Utilities;
import pl.nazaweb.netbeans.quickfilesearch.options.Options;

/**
 *
 * @author naza
 */
public class IgnoreDirectoryFilter implements FileFilter {

    @Override
    public boolean accept(File directory) {
        String directoryPath = extractDirectoryPath(directory);
        List<String> storedIgnoredDirectories = Options.getIgnoredFiles();
        return storedIgnoredDirectories.stream().noneMatch((ignoredDir)
                -> (ignoredDir.isEmpty() == false && directoryPath.contains(ignoredDir)));
    }

    private String extractDirectoryPath(File directory) {
        String directoryPath = directory.getAbsolutePath();
        String projectPath = getDirectoryProjectOwnerPath(directory);
        directoryPath = directoryPath.replace(projectPath, "");
        directoryPath = removeFileSeparatorIfExistsOnFirstPosition(directoryPath);
        return directoryPath;
    }

    private String getDirectoryProjectOwnerPath(File directory) {
        Project project = FileOwnerQuery.getOwner(Utilities.toURI(directory));
        return project != null ? project.getProjectDirectory().getPath() : new File("").getAbsolutePath();
    }

    private String removeFileSeparatorIfExistsOnFirstPosition(String directoryPath) {
        if (directoryPath.isEmpty() == false && directoryPath.toCharArray()[0] == File.separatorChar) {
            return directoryPath.substring(1);
        }
        return directoryPath;
    }

}
