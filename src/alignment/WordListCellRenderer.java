/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 *
 * @author Adjan
 */
class WordListCellRenderer extends JLabel implements ListCellRenderer {

  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {

    // Wert aus der Liste wird als SelectedWord interpretiert (gecastet)
    SelectedWord selectedWords = (SelectedWord) value;

    // Name der Person wird als Text gesetzt
    this.setText(selectedWords.getWord());
    
    // Muss aufgerufen werden ansonsten hat this.setBackground keine Wirkung
    this.setOpaque(true);
     // Element aus der Liste ist markiert
    if(isSelected){
      
      // Schriftfarbe
      // UIManager.getColor("List.selectionForeground") gibt die 
      // Standard Schriftfarbe für ein markiertes Listen Element zurück
      this.setForeground(UIManager.getColor("List.selectionForeground"));
      // Hintergrund
      // UIManager.getColor("List.selectionBackground") gibt die 
      // Standard Hintergrundfarbe für ein markiertes Listen Element zurück      
      this.setBackground(UIManager.getColor("List.selectionBackground"));
    }
    // Element aus der Liste ist nicht markiert
    else{
      // Schriftfarbe
      this.setForeground(selectedWords.getTextColor());
      // Hintergrund
      this.setBackground(UIManager.getColor("List.background"));
    }
    
    // Das Label wird zurückgegeben und nun angezeigt
    return this;
  }
}

class AlignedWordListCellRendererID extends JLabel implements ListCellRenderer {

  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {

    // Wert aus der Liste wird als SelectedWord interpretiert (gecastet)
    Aligned alignedWords = (Aligned) value;

    // Name der Person wird als Text gesetzt
    this.setText(alignedWords.getIdString());
    
    // Muss aufgerufen werden ansonsten hat this.setBackground keine Wirkung
    this.setOpaque(true);
     // Element aus der Liste ist markiert
    if(isSelected){
      // Schriftfarbe
      // UIManager.getColor("List.selectionForeground") gibt die 
      // Standard Schriftfarbe für ein markiertes Listen Element zurück
      this.setForeground(UIManager.getColor("List.selectionForeground"));
      // Hintergrund
      // UIManager.getColor("List.selectionBackground") gibt die 
      // Standard Hintergrundfarbe für ein markiertes Listen Element zurück      
      this.setBackground(UIManager.getColor("List.selectionBackground"));
    }
    // Element aus der Liste ist nicht markiert
    else{
      // Schriftfarbe
      this.setForeground(alignedWords.getTextColor());
      // Hintergrund
      this.setBackground(UIManager.getColor("List.background"));
    }
    
    // Das Label wird zurückgegeben und nun angezeigt
    return this;
  }
}

class AlignedWordListCellRendererWord extends JLabel implements ListCellRenderer {

  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {

    // Wert aus der Liste wird als Aligned interpretiert (gecastet)
    Aligned alignedWords = (Aligned) value;
    
    // Name der Person wird als Text gesetzt
    this.setText(alignedWords.name);
    
    // Muss aufgerufen werden ansonsten hat this.setBackground keine Wirkung
    this.setOpaque(true);
     // Element aus der Liste ist markiert
    if(isSelected){
      
      // Schriftfarbe
      // UIManager.getColor("List.selectionForeground") gibt die 
      // Standard Schriftfarbe für ein markiertes Listen Element zurück
      this.setForeground(UIManager.getColor("List.selectionForeground"));
      // Hintergrund
      // UIManager.getColor("List.selectionBackground") gibt die 
      // Standard Hintergrundfarbe für ein markiertes Listen Element zurück      
      this.setBackground(UIManager.getColor("List.selectionBackground"));
    }
    // Element aus der Liste ist nicht markiert
    else{
      // Schriftfarbe
      this.setForeground(alignedWords.getTextColor());
      // Hintergrund
      this.setBackground(UIManager.getColor("List.background"));
    }
    
    // Das Label wird zurückgegeben und nun angezeigt
    return this;
  }
}