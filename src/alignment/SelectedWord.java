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
public class SelectedWord {
    
    String selectedWord;
    Color textColor;
    int lineID;
    int wordNumber;
    int sentenceNumber;
    String newID;
    String idString;
    String wordNumberString;
    String sentenceNumberString;
    boolean isAligned;
    
    public SelectedWord(String wordS, Color textColor){
        this.textColor = textColor;
        this.selectedWord = wordS;
    }
    public SelectedWord(String wordS, int id){
        this.selectedWord = wordS;
        this.lineID = id;
    }
    
    public SelectedWord(String wordS, Color textColor, int id){
        this.textColor = textColor;
        this.selectedWord = wordS;
        this.lineID = id;
    }
    
    public SelectedWord(String wordS, Color textColor, int line, int wordNumber){
        this.textColor = textColor;
        this.selectedWord = wordS;
        this.lineID = line;
        this.wordNumber = wordNumber;
    }
    
        public SelectedWord(String wordS, Color textColor, int line, int wordNumber, int sentenceNumber){
        this.textColor = textColor;
        this.selectedWord = wordS;
        this.lineID = line;
        this.wordNumber = wordNumber;
        this.sentenceNumber = sentenceNumber;
    }
    
    
    public SelectedWord(String wordS, Color textColor, int id, boolean isAligned){
        this.textColor = textColor;
        this.selectedWord = wordS;
        this.lineID = id;
        this.isAligned = isAligned;
    }
    public SelectedWord(String wordS, Color textColor, int line, int wordNumber, boolean isAligned){
        this.textColor = textColor;
        this.selectedWord = wordS;
        this.lineID = line;
        this.wordNumber = wordNumber;
        this.isAligned = isAligned;
    }
    
       public SelectedWord(String wordS, Color textColor, int line, int wordNumber, int sentenceNumber, boolean isAligned){
        this.textColor = textColor;
        this.selectedWord = wordS;
        this.lineID = line;
        this.wordNumber = wordNumber;
        this.sentenceNumber = sentenceNumber;
        this.isAligned = isAligned;
    }
    
    
    
    public String getWord(){
        return selectedWord;
    }
    public Color getTextColor(){
        return textColor;
    }
    public void setTextColor (Color textColor) {
        this.textColor = textColor;
    }
    public void setWord(String wordS){
        this.selectedWord = wordS;
    }
    public int getlineID(){
        return lineID;
    }
    public String getLineIDString(){
        idString = String.valueOf(lineID);
        return idString;
    }
    public String getWordNumberString(){
         wordNumberString = String.valueOf(wordNumber);
         return wordNumberString;
    }
    public String getNewID(){
        newID=String.valueOf(lineID)+selectedWord+String.valueOf(wordNumber);
        return newID;
    }
    public String getSentenceNumberString(){
        sentenceNumberString = String.valueOf(sentenceNumber);
        return sentenceNumberString;
    }
}

