package pl.nazaweb.netbeans.quickfilesearch.search.strategy;

import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

/**
 *
 * @author naza
 */
public class Default extends AbstractStrategy implements SearchStrategy {

    @Override
    public boolean test(String text, FileItem file) {
        init(text, file);
        return file.getName().toLowerCase().contains(text.toLowerCase());
    }

}
