package pl.nazaweb.netbeans.quickfilesearch.search.strategy;

import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

/**
 *
 * @author naza
 */
public interface SearchStrategy {

    boolean test(String text, FileItem file);
}
