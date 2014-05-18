package pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.states;

import java.io.File;
import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.EventHandler;

/**
 *
 * @author naza
 */
public class CreateEventState implements EventHandler {

    @Override
    public void handle(File file) {
        System.out.println("new file : " + file.getAbsolutePath());
        FileObject fileObject;
        try {
            fileObject = FileUtil.createData(file);
            FileCache.getIntance().addFile(fileObject);

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
