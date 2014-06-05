package pl.nazaweb.netbeans.quickfilesearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import pl.nazaweb.netbeans.quickfilesearch.files.FileCache;
import pl.nazaweb.netbeans.quickfilesearch.files.FileItem;

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
        for (Project project : OpenProjects.getDefault().getOpenProjects()) {
            System.out.println(">>>>>>>" + project.getProjectDirectory().getPath());
            for (Entry<String, FileItem> entry : FileCache.getIntance()
                    .getProjectFiles(project.getProjectDirectory().getPath())
                    .entrySet()) {

                System.out.println(">> " + entry.getKey());
            }

        }
    }
}
