package pl.nazaweb.netbeans.quickfilesearch.files.watcher;

import java.io.File;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.EventHandler;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.states.CreateEventState;
import pl.nazaweb.netbeans.quickfilesearch.files.watcher.events.states.DeleteEventState;

/**
 *
 * @author naza
 */
public class FileChangeEventDispatcher {

    private final static Map<WatchEvent.Kind, EventHandler> EVENT_STATES = new HashMap();

    static {
        EVENT_STATES.put(ENTRY_CREATE, new CreateEventState());
        EVENT_STATES.put(ENTRY_DELETE, new DeleteEventState());
    }

    public void handleEvent(File file, WatchEvent.Kind kind) {
        EventHandler handler = EVENT_STATES.get(kind);
        if (handler != null) {
            handler.handle(file);
        }
    }

}
