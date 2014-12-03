/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Adjan
 */
public class DialogSave {
//This is the "save" dialog window
   static String fileName;
    void save(){
        this.fileName = null;
        final JFileChooser chooser = new JFileChooser("Choose directory");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
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
        chooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml" ));
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
   
}
