package pl.nazaweb.netbeans.quickfilesearch.startup;

import java.io.IOException;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.windows.WindowManager;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;

/**
 *
 * @author naza
 */
public class Startup extends ModuleInstall {

    @Override
    public void restored() {
        try {
            FileCache.getIntance().init();
            WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
                @Override
                public void run() {
                    //TODO: create file listener
                }
            });
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
