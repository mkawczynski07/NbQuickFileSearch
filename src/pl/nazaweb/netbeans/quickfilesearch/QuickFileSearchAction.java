package pl.nazaweb.netbeans.quickfilesearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileRenameEvent;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

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

        final Lookup.Result<DataObject> dtoResult = Utilities.actionsGlobalContext().lookupResult(DataObject.class);
        dtoResult.addLookupListener(new LookupListener() {

            @Override
            public void resultChanged(LookupEvent ev) {
                for (DataObject dto : dtoResult.allInstances()) {
                    Project project = FileOwnerQuery.getOwner(dto.getPrimaryFile());
                    if (project != null) {
                        dto.getPrimaryFile().addFileChangeListener(new MyFileChangeListener());
                        System.out.println("File " + dto.getPrimaryFile() + " of Project " + project.getProjectDirectory());
                    }
                }
            }
        });

    }

    public class MyFileChangeListener implements FileChangeListener {

        @Override
        public void fileFolderCreated(FileEvent fe) {
            System.out.println("folder create");
        }

        @Override
        public void fileDataCreated(FileEvent fe) {
            System.out.println("file create");
        }

        @Override
        public void fileChanged(FileEvent fe) {
            System.out.println("file change");
        }

        @Override
        public void fileDeleted(FileEvent fe) {
            System.out.println("file delete");
        }

        @Override
        public void fileRenamed(FileRenameEvent fre) {
            System.out.println("file rename");
        }

        @Override
        public void fileAttributeChanged(FileAttributeEvent fae) {
            System.out.println("file attr change");
        }
    }
}
