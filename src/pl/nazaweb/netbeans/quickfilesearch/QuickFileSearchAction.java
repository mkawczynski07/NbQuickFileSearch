/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.nazaweb.netbeans.quickfilesearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle.Messages;

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
        for (FileObject file : OpenProjects.getDefault().getMainProject().getProjectDirectory().getChildren()) {
            System.out.println(file.getName() + ", path: " + file.getPath());
        }
//        final Lookup.Result<DataObject> dtoResult = Utilities.actionsGlobalContext().lookupResult(DataObject.class);
//        dtoResult.addLookupListener(new LookupListener() {
//
//            @Override
//            public void resultChanged(LookupEvent ev) {
//                for (DataObject dto : dtoResult.allInstances()) {
//                    Project project = FileOwnerQuery.getOwner(dto.getPrimaryFile());
//                    if (project != null) {
//                        System.out.println("File " + dto.getPrimaryFile() + " of Project " + project.getProjectDirectory());
//                    }
//                }
//            }
//        });
    }
}
