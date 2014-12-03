/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 *
 * @author Adjan
 */
public class TextFinder{
    //This class gets the string array that is created by StrArray and searches the array for a
    //specific string (in this case the xml tags "<text>" and "</text>" and "<FinalText>
    //and </FinalText> ). 
    //The position of the array is stored in an int. Then the method findSourceText goes
    //through the array again using the indexes determined in the previous step
    //and stores the lines between the two tags in another array. Then another method goes
    //through that array and splits the lines into individual words which then are displayed
    //in the jLists jList1 and jList2. 

    
    
    
    //To do: fully implement the sentence counter
    
    
    
    
    
    StrArray sArray = new StrArray();
    String line;
    String lineFilt;
    String lineFilt2;
    LineNumberReader lnr = null;
    int indexTextSourceBeg;
    int indexTextSourceEnd;
    int indexTextTargetBeg;
    int indexTextTargetEnd;
    
    
    String[] tagText;
    String[] splitText;
    String lineSplit;

    SelectedWord selectedWordTemp;
    SelectedWord selectedWordLoadTemp;
    ArrayList <String> textFilt = new ArrayList<String>();
    int sourceWordCounter=0; //counts the source words
    int targetWordCounter=0; //counts the target words
    int headlineFinder = 0; //headlineFinder 1 and 2 are used to find the headline so that it will be counted as a sentence of its own
    //int headlineFinder2 = 0; //rather than going on until the first period/exclamation mark/question mark
    int sentenceCounterSource=1; //counts the source sentences 
    int sentenceCounterTarget=1;
    
    public boolean hasQuoteMark(String s){
        
        return s.contains("\\rquote");
   }
    public boolean wordIsS(String s){
        return s.matches("s");
    }
    public boolean isAbr(String s){
        return s.contains("n't");
    }
    public boolean hasPunctuation(String s){
        boolean hasPunct = false;
        if(s.contains(",")||s.contains(":")||s.contains(";")){
        hasPunct=true;
        }
        return hasPunct;
    }
    public boolean isEndOfSentence(String s){
        boolean isEndOfSentence = false;
        if(s.length()>0){
             String last = s.substring(s.length()-1);
        if(last.equals(".")|| last.equals("!")|| last.equals("?")){
            isEndOfSentence = true;
        }
        
        }
       return isEndOfSentence;
    }
    
    public void findText(){    
        try{
          
            String[] lines = sArray.readLines(DialogOpen.fileName);
            //String[] linesFilt = sArray.readLines(DialogOpen.fileName);
            
            
            for (int i=0; i<lines.length; i++){
                String line = lines[i];
                
                
//                if (line.indexOf("<Text>")!=-1){
//                    indexTextBeg = i;
//                    System.out.println("Anfangstag: " + i);
//                    }
//                    
//                else if (line.indexOf("</Text>")!=-1){
//                    indexTextEnd = i;
//                    System.out.println("Endtag: " + i);
//                  }        
                //The following two statements are for the xml files of newer versions of translog
                if (line.indexOf("<SourceTextUTF8>") !=-1){
                    indexTextSourceBeg = i;
                    System.out.println ("Anfangstag Source neu: " + i);
                }
                
                else if (line.indexOf("</SourceTextUTF8>") !=-1){
                    indexTextSourceEnd = i;
                    System.out.println("Endtag Source neu: " + i);
                }
                
                
                if (line.indexOf("<FinalTextUTF8>")!=-1){
                    indexTextTargetBeg = i;
                    System.out.println("Anfangstag Target neu: " + i);
                    }
                    
                else if (line.indexOf("</FinalTextUTF8>")!=-1){
                    indexTextTargetEnd = i;
                    System.out.println("Endtag Target neu: " + i);
                  }       
              
             }
            //here we're looking for the source text
            for (int j = indexTextSourceBeg; j<indexTextSourceEnd+1; j++){
                 //lineFilt = linesFilt[j]; (old)
                 lineFilt=lines[j];
                 
                 splitText = lineFilt.split("\\s|<SourceTextUTF8>|</SourceTextUTF8>");
                 System.out.println("Anzahl von SourceTextEinheiten: " + splitText.length);
                 for (int k=0; k<splitText.length; k++)
                 {
                     
                     if(isAbr(splitText[k])==false){
   
                        if(wordIsS(splitText[k])==false && hasQuoteMark(splitText[k])==true){
                     
                            if(hasPunctuation(splitText[k])==false && isEndOfSentence(splitText[k])==false){
                                System.out.println("Found quotation mark");
                                String tempReplace = splitText[k].replace("\\rquote", "'s");
                                System.out.println(tempReplace);
                                lineSplit = tempReplace;
                                }
                            if(hasPunctuation(splitText[k])==true){
                                System.out.println("Found punctuation");
                                String [] newPunctArray = seperatePunct(splitText[k]);
                         
                                for(int i =0; i<newPunctArray.length; i++){
                                    String punctWord = newPunctArray[i];
                                    SelectedWord newWordSource = new SelectedWord(punctWord, Color.BLUE, j,sourceWordCounter);
                                    targetWordCounter++;
                                    AlignmentTool.listModel.addElement(newWordSource);
                                }
                            }
                            if(isEndOfSentence(splitText[k])==true){
                                System.out.println("Found end of sentence");
                                String[] newEndSentenceArray = seperatePunct(splitText[k]);
                                for(int i=0; i<newEndSentenceArray.length; i++){
                                    String punctWord = newEndSentenceArray[i];
                                    SelectedWord newWordSource = new SelectedWord(punctWord, Color.BLUE, j, sourceWordCounter);
                                    targetWordCounter++;
                                    AlignmentTool.listModel.addElement(newWordSource);
                                }
                                sentenceCounterSource++;
                            }
                        }
                       
                        else{
                            if(isEndOfSentence(splitText[k])==true){
                                System.out.println("Found end of sentence; No abbreviation; no quotation mark");
                                String[] newEndSentenceArray = seperatePunct(splitText[k]);
                                for(int i=0; i<newEndSentenceArray.length; i++){
                                    String punctWord = newEndSentenceArray[i];
                                    SelectedWord newWordSource = new SelectedWord(punctWord, Color.BLUE, j, sourceWordCounter);
                                    sourceWordCounter++;
                                    AlignmentTool.listModel.addElement(newWordSource);
                                }
                                sentenceCounterSource++;
                            }

                            else if(hasPunctuation(splitText[k])==false){
                            lineSplit = splitText[k];
                            System.out.println(lineSplit + "\n");
                    
                            SelectedWord newWordTarget = new SelectedWord(lineSplit, Color.BLUE,j,sourceWordCounter);
                            sourceWordCounter++;
                            AlignmentTool.listModel.addElement(newWordTarget);
                    
                            System.out.println(newWordTarget.getNewID());   
                            
                            }
                            else{
                                    System.out.println("Found punctuation");
                                    String [] newPunctArray = seperatePunct(splitText[k]);
                         
                                    for(int l=0; l<newPunctArray.length; l++){
                                        String punctWord = newPunctArray[l];
                                        SelectedWord newWordTarget = new SelectedWord(punctWord, Color.BLUE, j,sourceWordCounter);
                                        sourceWordCounter++;
                                        AlignmentTool.listModel.addElement(newWordTarget);
                                    }                       
                            }
                        }            
               }
                   
                     else{
                         System.out.println("Found abreviation");
                         String [] newAbrArray = seperateAbrev(splitText[k]);
                         
                         for(int i =0; i<newAbrArray.length; i++){
                             
                             String abrWord = newAbrArray[i];
                             
                             if(hasPunctuation(newAbrArray[i])==false){
                             SelectedWord newWordSource = new SelectedWord(abrWord, Color.BLUE, j,sourceWordCounter);
                             sourceWordCounter++;
                             AlignmentTool.listModel.addElement(newWordSource);
                             }
                                else{
                                    System.out.println("Found punctuation");
                                    String [] newPunctArray = seperatePunct(splitText[k]);
                         
                                    for(int l=0; l<newPunctArray.length; l++){
                                        String punctWord = newPunctArray[l];
                                        SelectedWord newWordSource = new SelectedWord(punctWord, Color.BLUE, j,sourceWordCounter);
                                        sourceWordCounter++;
                                        AlignmentTool.listModel.addElement(newWordSource);
                                    }
                            
                                }
                         }
                     }
            }
                 
       }
            //here we're looking for the target text
             for (int j = indexTextTargetBeg; j<indexTextTargetEnd+1; j++){
                
                lineFilt = lines[j];
                 
                 System.out.println(lineFilt);
                 splitText = lineFilt.split("\\s|<FinalTextUTF8>|</FinalTextUTF8>");
                 
                
                    for (int k=0; k<splitText.length; k++){
                    lineSplit = splitText[k];
                    System.out.println("Anzahl von TargetTextEinheiten: " + splitText.length);
                    
                    if(isAbr(splitText[k])==false){
   
                        if(wordIsS(splitText[k])==false && hasQuoteMark(splitText[k])==true){
                     
                            if(hasPunctuation(splitText[k])==false && isEndOfSentence(splitText[k])==false){
                                System.out.println("Found quotation mark");
                                String tempReplace = splitText[k].replace("\\rquote", "'s");
                                System.out.println(tempReplace);
                                lineSplit = tempReplace;
                                }
                            if(hasPunctuation(splitText[k])==true){
                                System.out.println("Found punctuation");
                                String [] newPunctArray = seperatePunct(splitText[k]);
                         
                                for(int i =0; i<newPunctArray.length; i++){
                                    String punctWord = newPunctArray[i];
                                   
                                    SelectedWord newWordTarget = new SelectedWord(punctWord, Color.BLUE, j,targetWordCounter);
                                    targetWordCounter++;
                                    AlignmentTool.listModel2.addElement(newWordTarget);
                                }
                            }
                            if(isEndOfSentence(splitText[k])==true){
                                System.out.println("Found end of sentence");
                                String[] newEndSentenceArray = seperatePunct(splitText[k]);
                                for(int i=0; i<newEndSentenceArray.length; i++){
                                    String punctWord = newEndSentenceArray[i];
                                    SelectedWord newWordTarget = new SelectedWord(punctWord, Color.BLUE, j, targetWordCounter);
                                    targetWordCounter++;
                                    AlignmentTool.listModel2.addElement(newWordTarget);
                                }
                                sentenceCounterTarget++;
                            }
                        }
                       
                        else{
                            if(isEndOfSentence(splitText[k])==true){
                                System.out.println("Found end of sentence; No abbreviation; no quotation mark");
                                String[] newEndSentenceArray = seperatePunct(splitText[k]);
                                for(int i=0; i<newEndSentenceArray.length; i++){
                                    String punctWord = newEndSentenceArray[i];
                                    SelectedWord newWordTarget = new SelectedWord(punctWord, Color.BLUE, j, targetWordCounter);
                                    targetWordCounter++;
                                    AlignmentTool.listModel2.addElement(newWordTarget);
                                }
                                sentenceCounterTarget++;
                            }

                            else if(hasPunctuation(splitText[k])==false){
                            lineSplit = splitText[k];
                            System.out.println(lineSplit + "\n");
                    
                            SelectedWord newWordTarget = new SelectedWord(lineSplit, Color.BLUE,j,targetWordCounter);
                            targetWordCounter++;
                            AlignmentTool.listModel2.addElement(newWordTarget);
                    
                            System.out.println(newWordTarget.getNewID());   
                            
                            }
                            else{
                                    System.out.println("Found punctuation");
                                    String [] newPunctArray = seperatePunct(splitText[k]);
                         
                                    for(int l=0; l<newPunctArray.length; l++){
                                        String punctWord = newPunctArray[l];
                                        SelectedWord newWordTarget = new SelectedWord(punctWord, Color.BLUE, j,targetWordCounter);
                                        targetWordCounter++;
                                        AlignmentTool.listModel2.addElement(newWordTarget);
                                    }                       
                            }
                        }            
               }
                     else{
                         System.out.println("Found abreviation");
                         String [] newAbrArray = seperateAbrev(splitText[k]);
                         
                         for(int i =0; i<newAbrArray.length; i++){
                             
                             String abrWord = newAbrArray[i];
                             
                             if(hasPunctuation(newAbrArray[i])==false){
                             SelectedWord newWordTarget = new SelectedWord(abrWord, Color.BLUE, j, targetWordCounter);
                             targetWordCounter++;
                             AlignmentTool.listModel2.addElement(newWordTarget);
                             }
                                else{
                                    System.out.println("Found punctuation");
                                    String [] newPunctArray = seperatePunct(splitText[k]);
                         
                                    for(int l=0; l<newPunctArray.length; l++){
                                        String punctWord = newPunctArray[l];
                                        SelectedWord newWordTarget = new SelectedWord(punctWord, Color.BLUE, j,targetWordCounter);
                                        targetWordCounter++;
                                        AlignmentTool.listModel2.addElement(newWordTarget);
                                    }
                            
                                }
                         }
                     }
                    
                   
                    }
            }
            if(AlignmentTool.hasAligned=true){
                for(int i =0; i<Alignments.alignedToLoad.size(); i++){
                    Aligned tempAligned = (Aligned)Alignments.alignedToLoad.get(i);
                    for(int j=0; j<tempAligned.listSource.size(); j++){
                        SelectedWord tempSelectedWord = (SelectedWord)tempAligned.listSource.get(j);
                        int tempID = tempSelectedWord.wordNumber;
                        SelectedWord tempWordInJList = (SelectedWord)AlignmentTool.listModel.get(tempID);
                        tempWordInJList.setTextColor(Color.red);
                    }
                     for(int j=0; j<tempAligned.listTarget.size(); j++){
                        SelectedWord tempSelectedWord = (SelectedWord)tempAligned.listTarget.get(j);
                        int tempID = tempSelectedWord.wordNumber;
                        SelectedWord tempWordInJList = (SelectedWord)AlignmentTool.listModel2.get(tempID);
                        tempWordInJList.setTextColor(Color.red);
                    }
                }
            }
            
            
              if(AlignmentTool.hasAligned=true){
                for(int i =0; i<Alignments.alignedToLoad.size(); i++){
                    Aligned tempAligned = (Aligned)Alignments.alignedToLoad.get(i);
                   
                }
            }
//            for (int i=0; i<NewJFrame.listModel.size();i++){
//                 SelectedWord selectedWordSourceTemp = (SelectedWord)NewJFrame.listModel.get(i);
//                 for(int j=0; j<Alignments.alignedToLoad.size(); j++){
//                     Aligned tempAligned = (Aligned)Alignments.alignedToLoad.get(j);
//                     for (int k=0; k<tempAligned.listSource.size(); k++){
//                         SelectedWord selectedWordComp = (SelectedWord)tempAligned.listSource.get(k);
//                         if(selectedWordSourceTemp.lineID==selectedWordComp.lineID&&selectedWordSourceTemp.wordNumber==selectedWordComp.wordNumber){
//                            selectedWordSourceTemp.setTextColor(Color.red);
//                         }
//                     }
//                 }     
//            }
       
             
            }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
        public String[] seperateAbrev(String abreviatedWord){
            String[] abrArr = new String[2];
            int abrevIndex=abreviatedWord.indexOf("'");
            String stringBefAbr = abreviatedWord.substring(0, abrevIndex-1);
            String stringAftAbr = abreviatedWord.substring(abrevIndex-1);
            abrArr[0]= stringBefAbr;
            abrArr[1]= stringAftAbr;
            
            return abrArr;
        }
        public String[] seperatePunct(String punctuation){
            String[] punctuationArray = new String[2];
            int periodIndex = punctuation.indexOf(".");
            int commaIndex = punctuation.indexOf(",");
            int colonIndex = punctuation.indexOf(":");
            int semicolonIndex = punctuation.indexOf(";");
            int questionMarkIndex = punctuation.indexOf("?");
            int exclamationMarkIndex = punctuation.indexOf("!");
            
            
            if (periodIndex !=-1){
                String word = punctuation.substring(0, periodIndex);
                String punctuationMark = punctuation.substring(periodIndex);
                punctuationArray[0]= word;
                punctuationArray[1]= punctuationMark;
            }
            if (commaIndex !=-1){
                String word = punctuation.substring(0,commaIndex);
                String punctuationMark = punctuation.substring(commaIndex);
                punctuationArray[0]= word;
                punctuationArray[1]= punctuationMark;
            }
            if(colonIndex !=-1){
                String word = punctuation.substring(0, colonIndex);
                String punctuationMark = punctuation.substring(colonIndex);
                punctuationArray[0]=word;
                punctuationArray[1]=punctuationMark;
            }
            if(semicolonIndex !=-1){
                String word = punctuation.substring(0, semicolonIndex);
                String punctuationMark = punctuation.substring(semicolonIndex);
                punctuationArray[0]=word;
                punctuationArray[1]=punctuationMark;
            }
            if(questionMarkIndex !=-1){
                String word = punctuation.substring(0, questionMarkIndex);
                String punctuationMark = punctuation.substring(questionMarkIndex);
                punctuationArray[0]=word;
                punctuationArray[1]=punctuationMark;
            }
             if(exclamationMarkIndex !=-1){
                String word = punctuation.substring(0, exclamationMarkIndex);
                String punctuationMark = punctuation.substring(exclamationMarkIndex);
                punctuationArray[0]=word;
                punctuationArray[1]=punctuationMark;
            }
            
            return punctuationArray;
        }
        

  class StrArray{ 
//reads a textfile and stores the lines in an arraylist. returnes a stringarray
        public String[] readLines(String filename) throws IOException {
        
            FileReader fileReader = new FileReader(filename);
            //BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-16")); -> translog 2006
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            List<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
            in.close();
            return lines.toArray(new String[lines.size()]);
        }
    }     
}