package pl.nazaweb.netbeans.quickfilesearch;

import java.util.Map;
import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;
import pl.nazaweb.netbeans.quickfilesearch.search.FileTester;

public class QuickFileSearchProvider implements SearchProvider {
    
    private final FileTester fileTester = new FileTester();
    
    @Override
    public void evaluate(SearchRequest request, SearchResponse response) {
        
        for (Map.Entry<String, Map<String, FileItem>> project : FileCache.getInstance().getProjectsFiles().entrySet()) {
            for (Map.Entry<String, FileItem> file : project.getValue().entrySet()) {
                FileItem item = file.getValue();
                if (fileTester.test(request.getText(), item)) {
                    if (!response.addResult(item, item.display(), item.getPath(), null)) {
                        return;
                    }
                }
            }
        }
        
    }
    
}
