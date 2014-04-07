package pl.nazaweb.netbeans.quickfilesearch.startup;

import java.io.File;
import java.io.FileFilter;
import pl.nazaweb.netbeans.quickfilesearch.utils.FileUtils;

/**
 *
 * @author naza
 */
//TODO: create configuration for ingoring files
public class IgnoreFileFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        String extension = FileUtils.getFileExtension(file.getAbsolutePath());
        if (extension.equalsIgnoreCase("class")) {
            return false;
        }
        return !extension.equalsIgnoreCase("jar");
    }

}
