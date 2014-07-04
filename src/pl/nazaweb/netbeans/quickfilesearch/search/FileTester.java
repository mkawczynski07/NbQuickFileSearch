package pl.nazaweb.netbeans.quickfilesearch.search;

import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

/**
 *
 * @author Marek Kawczyński (marekka@mikronika.com.pl)
 */
public class FileTester {

    private String requestText;
    private FileItem file;

    public boolean test(String text, FileItem file) {
        init(text, file);
        return file.getName().toLowerCase().contains(text.toLowerCase());
    }

    private void init(String text, FileItem file) {
        this.requestText = text;
        this.file = file;
    }

}
