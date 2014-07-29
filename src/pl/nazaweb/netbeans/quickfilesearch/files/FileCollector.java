package pl.nazaweb.netbeans.quickfilesearch.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author naza
 */
public class FileCollector {

    public void addProjectFiles(Project project) throws IOException {
        Files.walk(Paths.get(project.getProjectDirectory().toURI())).filter(filePath
                -> !shouldIgnoreFileInDirectory(filePath)
        ).forEach(filePath -> {
            try {
                if (Files.isRegularFile(filePath)) {
                    addFile(FileUtil.createData(filePath.toFile()));
                }
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        });
    }

    private void addFile(FileObject file) throws Exception {
        FileCache.getInstance().addFile(file);
    }

    private boolean shouldIgnoreDirectory(File directory) {
        return new IgnoreDirectoryFilter().accept(directory) == false;
    }

    private boolean shouldIgnoreFileInDirectory(Path path) {
        return shouldIgnoreDirectory(path.toFile().getParentFile());
    }

}
