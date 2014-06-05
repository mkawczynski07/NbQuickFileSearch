package pl.nazaweb.netbeans.quickfilesearch;

import java.util.Map;
import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

public class QuickFileSearchProvider implements SearchProvider {

    @Override
    public void evaluate(SearchRequest request, SearchResponse response) {

        for (Map.Entry<String, Map<String, FileItem>> project : FileCache.getIntance().getProjectsFiles().entrySet()) {
            for (Map.Entry<String, FileItem> file : project.getValue().entrySet()) {
                FileItem item = file.getValue();
                if (item.getName().toLowerCase().contains(request.getText().toLowerCase())) {
                    if (!response.addResult(item, item.getName(), item.getPath(), null)) {
                        return;
                    }
                }
            }
        }

    }

}