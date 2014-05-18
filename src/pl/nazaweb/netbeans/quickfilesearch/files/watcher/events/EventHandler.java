package pl.nazaweb.netbeans.quickfilesearch.files.watcher.events;

import java.io.File;

/**
 *
 * @author naza
 */
public interface EventHandler {

    void handle(File file);

}
