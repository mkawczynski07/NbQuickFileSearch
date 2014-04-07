package pl.nazaweb.netbeans.quickfilesearch;

import java.io.IOException;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import pl.nazaweb.netbeans.quickfilesearch.startup.FileCache;

/**
 *
 * @author naza
 */
public class Startup extends ModuleInstall {

    @Override
    public void restored() {
        try {
            FileCache.getIntance().init();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
