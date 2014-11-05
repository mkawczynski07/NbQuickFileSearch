package pl.nazaweb.netbeans.quickfilesearch.search;

import pl.nazaweb.netbeans.quickfilesearch.search.strategy.CamelCase;
import pl.nazaweb.netbeans.quickfilesearch.search.strategy.Default;
import pl.nazaweb.netbeans.quickfilesearch.search.strategy.SearchStrategy;

/**
 *
 * @author naza
 */
public class FileTester {

    private final Default defaultStrategy = new Default();
    private final CamelCase camelCase = new CamelCase();

    public SearchStrategy getStrategy(String text) {
        return camelCase;
    }

}
