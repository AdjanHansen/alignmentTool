/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import java.awt.Color;

/**
 *
 * @author Adjan
 */
public class Placeholder {
    static int placeHolderCounter=0;
    
 void insertSourcePlaceHolder(int index){
        
        SelectedWord sourcePlaceHolder = new SelectedWord("undefined", Color.blue, index);
        
            
        
        AlignmentTool.listModel.add(index, sourcePlaceHolder);
        
        placeHolderCounter++;
        
        
    }
    void insertTargetPlaceHolder(int index){
        SelectedWord targetPlaceHolder = new SelectedWord("undefined", Color.blue, index);
        AlignmentTool.listModel2.add(index, targetPlaceHolder);
        placeHolderCounter++;
        
    }
}
