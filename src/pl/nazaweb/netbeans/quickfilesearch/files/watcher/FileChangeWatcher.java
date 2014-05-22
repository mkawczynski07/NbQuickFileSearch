package pl.nazaweb.netbeans.quickfilesearch.files.watcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author naza
 */
public class FileChangeWatcher implements Runnable {

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final FileChangeEventDispatcher dispatcher;

    public FileChangeWatcher(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
        dispatcher = new FileChangeEventDispatcher();
        registerWatcherRecursively(dir);
    }

    public static <T> WatchEvent<T> castToWatchEvent(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    public void watch() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        startWatching();
    }

    private void startWatching() {
        for (;;) {
            WatchKey key = tryTaketKeyFromWatcher();
            if (isExists(key)) {
                return;
            }

            Path dir = getDirectoryFromKey(key);
            if (isExists(dir)) {
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> pathEvent = castToWatchEvent(event);
                Path name = pathEvent.context();
                Path eventFile = dir.resolve(name);

                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(eventFile, NOFOLLOW_LINKS)) {
                            registerWatcherRecursively(eventFile);
                        }
                    } catch (IOException x) {
                    }
                }

                dispatcher.handleEvent(eventFile.toFile(), event.kind());

            }

            if (isKeyInaccessible(key)) {
                keys.remove(key);
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    private void registerWatcherRecursively(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerWatcherOnDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void registerWatcherOnDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }

    private WatchKey tryTaketKeyFromWatcher() {
        try {
            return watcher.take();
        } catch (InterruptedException x) {
            return null;
        }
    }

    private boolean isExists(Object object) {
        return object == null;
    }

    private Path getDirectoryFromKey(WatchKey key) {
        return keys.get(key);
    }

    private boolean isKeyInaccessible(WatchKey key) {
        return key.reset();
    }

}
