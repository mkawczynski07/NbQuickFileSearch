package pl.nazaweb.netbeans.quickfilesearch.files.watcher;

import java.io.File;
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
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    @Override
    public void run() {
        startWatching();
    }

    private void startWatching() {
        for (;;) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = castToWatchEvent(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerWatcherRecursively(child);
                        }
                    } catch (IOException x) {
                    }
                }

                dispatcher.handleEvent(child.toFile(), event.kind());

            }

            boolean valid = key.reset();
            if (!valid) {
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
        File file = dir.toFile();
        if (shouldIgnoreFile(file)) {
            return;
        }
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }

    private boolean shouldIgnoreFile(File file) {
        String filePath = file.getPath().toLowerCase();
        return filePath.contains("classes")
                || filePath.contains("build")
                || filePath.contains("target")
                || filePath.contains("nbproject")
                || filePath.contains(".git")
                || filePath.contains(".svn");
    }
}
