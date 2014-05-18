package pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.states;

import java.io.File;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.EventHandler;

/**
 *
 * @author naza
 */
public class DeleteEventState implements EventHandler {

    @Override
    public void handle(File file) {
        System.out.println("deleted file : " + file.getAbsolutePath());
        FileCache.getIntance().removeFile(file.getAbsolutePath());
    }

}
