package pl.nazaweb.netbeans.quickfilesearch.search.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

/**
 *
 * @author naza
 */
public class CamelCase extends AbstractStrategy implements SearchStrategy {

    @Override
    public boolean test(String text, FileItem file) {
        init(text, file);
        return matchCamelCase(text, file.getName());
    }

    private boolean matchCamelCase(String query, String str) {
        StringBuilder queryBuilder = new StringBuilder();
        for (int i = 0; i < query.length(); i++) {
            if (Character.isUpperCase(query.charAt(i))) {
                queryBuilder.append(".*?").append(query.charAt(i));
            } else if (query.charAt(i) == 42) {
                queryBuilder.append(".*?");
            } else {
                queryBuilder.append(query.charAt(i));
            }
        }

        query = queryBuilder.toString();

        String re = "\\b(" + query.replaceAll("([A-Z][^A-Z]*)", "$1[^A-Z]*") + ".*?)\\b";

        Pattern regex = Pattern.compile(re);

        Matcher m = regex.matcher(str);
        boolean find = m.find();
        System.out.println(str + " - > " + re + " = " + find);
        return find;
    }

}
