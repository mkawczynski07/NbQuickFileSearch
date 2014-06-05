package pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.states;

import java.io.File;
import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.IgnoreFileFilter;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.EventHandler;

/**
 *
 * @author naza
 */
public class CreateEventState implements EventHandler {

    private final IgnoreFileFilter ignoreFilter = new IgnoreFileFilter();

    @Override
    public void handle(File file) {
        if (shouldIgnoreFile(file)) {
            return;
        }
        FileObject fileObject;
        try {
            fileObject = FileUtil.createData(file);
            FileCache.getIntance().addFile(fileObject);

        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private boolean shouldIgnoreFile(File file) {
        return ignoreFilter.accept(file) == false;
    }

}
