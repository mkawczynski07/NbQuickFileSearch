package pl.nazaweb.netbeans.quickfilesearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle.Messages;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;

@ActionID(
        category = "File",
        id = "pl.nazaweb.netbeans.quickfilesearch.QuickFileSearchAction"
)
@ActionRegistration(
        displayName = "#CTL_QuickFileSearchAction"
)
@ActionReference(path = "Shortcuts", name = "DS-Q")
@Messages("CTL_QuickFileSearchAction=Search ...")
public final class QuickFileSearchAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Entry<String, FileObject> entry : FileCache.getIntance().getFiles().entrySet()) {
            System.out.println(">> " + entry.getKey());
        }
        System.out.println("summary: " + FileCache.getIntance().getFiles().size());
    }
}
