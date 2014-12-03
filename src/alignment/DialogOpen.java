/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Adjan
 */
public class DialogOpen {
//This is the "open" dialog window
   static String fileName;
    void open() {
        final JFileChooser chooser = new JFileChooser("Choose directory");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        final File file = new File("/home");
        chooser.setCurrentDirectory(file);

        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals(JFileChooser.SELECTED_FILES_CHANGED_PROPERTY)
                        || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                    final File f = (File) e.getNewValue();
                }
            }
        });
        chooser.setVisible(true);
        final int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File inputVerzFile = chooser.getSelectedFile();
            String inputVerzStr = inputVerzFile.getPath();
            this.fileName = inputVerzFile.getPath();

            System.out.println("Eingabepfad:" + inputVerzStr);

        }
        System.out.println("Abbruch");
        chooser.setVisible(false);
    }
    static void reset(){
        AlignmentTool.listModel.clear();
        AlignmentTool.listModel2.clear();
        AlignmentTool.listModel3.clear();
        AlignmentTool.listModel4.clear();
        Alignments.alignedToStore.clear();
        Alignments.alignedToLoad.clear();
        AlignmentTool.hasAligned=false;
        Alignments.alignedId=0;
        
        
    }
}
