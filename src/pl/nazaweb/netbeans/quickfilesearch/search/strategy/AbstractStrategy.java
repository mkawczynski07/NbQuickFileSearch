package pl.nazaweb.netbeans.quickfilesearch.search.strategy;

import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

/**
 *
 * @author naza
 */
public abstract class AbstractStrategy implements SearchStrategy {

    protected String requestText;
    protected FileItem file;

    protected void init(String text, FileItem file) {
        this.requestText = text;
        this.file = file;
    }
}
