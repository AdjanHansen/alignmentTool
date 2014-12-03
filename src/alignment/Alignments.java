/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adjan Hansen-Ampah <adjan.hansen@rwth-aachen.de>
 */
public class Alignments{
    
    static ArrayList <alignment.Aligned> alignedList = new ArrayList <alignment.Aligned>();  
    static ArrayList <alignment.Aligned> alignedToStore = new ArrayList <alignment.Aligned>();
    static ArrayList <alignment.Aligned> alignedToLoad = new ArrayList <alignment.Aligned>(); 
    static List <Object> alignedListToErase;
    static int alignedId;  
    
    public static void increaseAlignedID(){
        alignedId++;
    }
    public static void deleteAligned(){
        
    }
}

 class Aligned{
    StringBuilder alignedWordsBuilder = new StringBuilder();
    String tempAlignedSourceWords;
    String tempAlignedTargetWords;
    List <Object>listSource;
    List <Object>listTarget;
    SelectedWord newWordSource;
    SelectedWord newWordTarget;
    int id;
    String idString;
    String sourceWord;
    String targetWord;
    String alignedWordsString;
    Color textColor;
    String name;
    
    public Aligned(){
        
    }
    
    public Aligned(List list){
        this.listSource = list;
    }
    public Aligned(List sourceList, List targetList, int alignedIndex){
        this.listSource = sourceList;
        this.listTarget = targetList;
        this.id = alignedIndex;
        
    }
    public Aligned(List sourceList, List targetList, Color color, int alignedIndex){
        this.listSource = sourceList;
        this.listTarget = targetList;
        this.textColor = color;
        this.id = alignedIndex;
        this.name = this.getAlignedWords();
        
        
    }
    public String getAlignedWords(){
        alignedWordsBuilder.append("(");
        for(int i=0; i<this.listSource.size(); i++){
                   
               tempAlignedSourceWords = this.getSourceWord(i).getWord();
               alignedWordsBuilder.append(" | ");
               alignedWordsBuilder.append(tempAlignedSourceWords);
               
               }
               alignedWordsBuilder.append(") - (");
               for(int j=0; j<this.listTarget.size(); j++){
                   tempAlignedTargetWords = this.getTargetWord(j).getWord();
                   alignedWordsBuilder.append(" | ");
                   alignedWordsBuilder.append(tempAlignedTargetWords);
                   
                   
               }
               alignedWordsBuilder.append(" | )");
               alignedWordsBuilder.append("\n");
               
        
        alignedWordsString = alignedWordsBuilder.toString();
       
        return alignedWordsString;
    }
    public SelectedWord getSourceWord(int i){
        
        this.newWordSource = (SelectedWord)listSource.get(i);
        this.sourceWord = newWordSource.getWord();
        
        return this.newWordSource;
    
    }

           
    public SelectedWord getTargetWord(int i){
        this.newWordTarget = (SelectedWord)listTarget.get(i);
        this.targetWord = newWordTarget.getWord();
        return this.newWordTarget;
        
    }
    public String getName(){
        return this.name;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public String getIdString(){
        idString = String.valueOf(id);
        return idString;
    }
    public Color getTextColor(){
        return textColor;
    }
    public void setTextColor(Color textColor){
        this.textColor = textColor;
    }
}

