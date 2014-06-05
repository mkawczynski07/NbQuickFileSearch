package pl.nazaweb.netbeans.quickfilesearch.files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.openide.filesystems.FileObject;

/**
 *
 * @author naza
 */
public class FileCache {

    private static FileCache INSTANCE;
    private final IgnoreFileFilter fileFilter = new IgnoreFileFilter();
    private final Map<String, Map<String, FileItem>> projectsFiles = new HashMap();
    private final Map<String, Entry<String, Map<String, FileItem>>> filesToProject = new HashMap();

    private FileCache() {
    }

    public static FileCache getIntance() {
        if (INSTANCE == null) {
            INSTANCE = new FileCache();
        }
        return INSTANCE;
    }

    public void addFile(FileObject object) throws Exception {
        File file = new File(object.getPath());
        if (shouldAddFile(file)) {
            Entry<String, Map<String, FileItem>> project = getProjectFilesByFile(object.getPath());
            FileItem item = new FileItem(file, new File(project.getKey()).getName());
            project.getValue().put(file.getPath(), item);

        }
    }

    public void removeFile(String path) throws Exception {
        getProjectFilesByFile(path).getValue().remove(path);
    }

    public Map<String, FileItem> getProjectFiles(String projectPath) {
        return projectsFiles.get(projectPath.toLowerCase());
    }

    public void addProject(String projectPaths) {
        projectsFiles.put(projectPaths.toLowerCase(), new HashMap());
    }

    private Entry<String, Map<String, FileItem>> getProjectFilesByFile(String filePath) throws Exception {
        if (filesToProject.containsKey(filePath)) {
            return filesToProject.get(filePath);
        }
        for (Entry<String, Map<String, FileItem>> project : projectsFiles.entrySet()) {
            if (filePath.toLowerCase().contains(project.getKey().toLowerCase())) {
                filesToProject.put(filePath, project);
                return project;
            }
        }
        throw new Exception("no project found");
    }

    private boolean shouldAddFile(File file) {
        return fileFilter.accept(file);
    }

    public Map<String, Map<String, FileItem>> getProjectsFiles() {
        return projectsFiles;
    }

}
