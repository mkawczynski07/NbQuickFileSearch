package pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.states;

import java.io.File;
import org.openide.util.Exceptions;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.EventHandler;

/**
 *
 * @author naza
 */
public class DeleteEventState implements EventHandler {

    @Override
    public void handle(File file) {
        try {
            FileCache.getInstance().removeFile(file.getAbsolutePath());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
