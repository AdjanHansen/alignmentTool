/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;


import java.io.*;
import java.util.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;
import java.awt.Color;
import org.jdom2.Attribute;

/**
 *
 * @author Adjan
 */
public class XmlReadWrite {
    
//supposed to read and write xml docs; 
//
    ArrayList <alignment.Aligned> listToSave = new ArrayList <alignment.Aligned>();
    
    List<Element>alignedInXML=null;
    List<Element>alignedInXMLLoad=null;
    
    
    String xml;
    boolean hasAligned;
    
    Document doc; 
    public void getXML() {
        try {
            SAXBuilder XMLBuilder = new SAXBuilder();
            doc = XMLBuilder.build(DialogOpen.fileName);
            Element rootElement = doc.getRootElement();
            
            List<Element> listElement = rootElement.getChildren();
            
            
            System.out.println(rootElement.getName());
            
            for (int i=0; i<listElement.size(); i++){
             Element elementSearch = listElement.get(i);
             String name = elementSearch.getName();
             System.out.println(name);
             if (name == "Aligned"){
                List <SelectedWord>listSource = new ArrayList<SelectedWord>();
                List <SelectedWord>listTarget = new ArrayList<SelectedWord>();
                int intIDAligned = elementSearch.getAttribute("id").getIntValue();
                System.out.println(intIDAligned);
                Element sourceWord = elementSearch.getChild("SourceText");
                System.out.println(sourceWord.getName());
                Element targetWord = elementSearch.getChild("TargetText");
                System.out.println(targetWord.getName());
                List<Element> listSourceWords = sourceWord.getChildren();
                List<Element> listTargetWords = targetWord.getChildren();
                
                
                for(int j=0; j<listSourceWords.size(); j++){
                    Element sourceWordElement = listSourceWords.get(j);
                    Attribute nameAtt = sourceWordElement.getAttribute("word");
                    String sourceWordWord = nameAtt.getValue();
                   
                    int lineID = sourceWordElement.getAttribute("lineID").getIntValue();
                    int sourceWordNumber = sourceWordElement.getAttribute("wordNumber").getIntValue();
                  
                    
                    SelectedWord sourceWordFromXML = new SelectedWord(sourceWordWord, Color.YELLOW, lineID, sourceWordNumber);
                 
                    listSource.add(sourceWordFromXML);
                    
                }
                for(int j=0; j<listTargetWords.size(); j++){
                    Element targetWordElement = listTargetWords.get(j);
                    Attribute nameAtt = targetWordElement.getAttribute("word");
                    String targetWordWord = nameAtt.getValue();
                    
                    System.out.println(targetWordWord);
                    int lineID = targetWordElement.getAttribute("lineID").getIntValue();
                    int targetWordNumber = targetWordElement.getAttribute("wordNumber").getIntValue();
                    System.out.println(lineID);
                    SelectedWord targetWordFromXML = new SelectedWord(targetWordWord, Color.YELLOW, lineID, targetWordNumber);
                    listTarget.add(targetWordFromXML);
                    System.out.println(listTarget.size());
                }
                Aligned alignedFromXML = new Aligned (listSource, listTarget, Color.RED, intIDAligned);
                Alignments.alignedToLoad.add(alignedFromXML);
                AlignmentTool.hasAligned=true; 
             }
               
            }
         
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    public void writeXML()throws IOException{
        
        
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(doc, new FileOutputStream(DialogSave.fileName));
    }
    
    public void toXML(){
        try{
            SAXBuilder XMLBuilder = new SAXBuilder();
            doc = XMLBuilder.build(DialogOpen.fileName);
            Element element = doc.getRootElement();
            
                
                 for(int i=0; i<Alignments.alignedToStore.size(); i++){
                Aligned alignedToXML = (Aligned)Alignments.alignedToStore.get(i);
                alignedToXML.setTextColor(Color.red);
                Element newAlignedElement = new Element("Aligned"); 
                newAlignedElement.setAttribute("id", alignedToXML.idString);
                element.addContent(newAlignedElement);
                Element sourceElement = new Element ("SourceText");
                Element targetElement = new Element ("TargetText");
                newAlignedElement.addContent(sourceElement);
                newAlignedElement.addContent(targetElement);
                
                for(int j=0; j<alignedToXML.listSource.size();j++){
                    SelectedWord sourceWordToXML = (SelectedWord) alignedToXML.listSource.get(j);
                    sourceWordToXML.setTextColor(Color.red);
                    //Element sourceWordElement = new Element(sourceWordToXML.getWord());
                    Element sourceWordElement = new Element("l"+sourceWordToXML.getLineIDString()+"w"+sourceWordToXML.getWordNumberString());
                    sourceWordElement.setAttribute("word", sourceWordToXML.getWord());
                    sourceWordElement.setAttribute("lineID",sourceWordToXML.getLineIDString());
                    sourceWordElement.setAttribute("wordNumber",sourceWordToXML.getWordNumberString());
                    sourceWordElement.setAttribute("isAligned", "true");
                    sourceElement.addContent(sourceWordElement);
                }
                for(int k=0;k<alignedToXML.listTarget.size();k++){
                    SelectedWord targetWordToXML = (SelectedWord) alignedToXML.listTarget.get(k);
                    targetWordToXML.setTextColor(Color.red);
                    //Element targetWordElement = new Element(targetWordToXML.getWord());
                    Element targetWordElement = new Element("l"+targetWordToXML.getLineIDString()+"w"+targetWordToXML.getWordNumberString());
                    targetWordElement.setAttribute("word", targetWordToXML.getWord());
                    targetWordElement.setAttribute("lineID",targetWordToXML.getLineIDString());
                    targetWordElement.setAttribute("wordNumber",targetWordToXML.getWordNumberString());
                    targetWordElement.setAttribute("isAligend", "true");
                    targetElement.addContent(targetWordElement);
                }
            }
           
            
            
                
            
             }
         catch(Exception e){    
             e.printStackTrace();
         } 
            
             
            
        }
  
    public String getSourceText(){
        String sourceTextString=null;
        try{
            SAXBuilder XMLBuilder = new SAXBuilder();
             doc = XMLBuilder.build(DialogOpen.fileName);
             Element rootElement = doc.getRootElement();
            List<Element> listElement = rootElement.getChildren();
            
            for (int i=0; i<listElement.size(); i++){
                Element elementSearch = listElement.get(i);
                String name = elementSearch.getName();
                System.out.println(name);
                if(name=="Project"){
                    System.out.println("found Project");
                    List<Element> listElementLvl2 = elementSearch.getChildren();
                    for (int j=0; j<listElementLvl2.size(); j++){
                        Element elementSearchLvl2 = listElementLvl2.get(j);
                        String nameLvl2 = elementSearchLvl2.getName();
                        System.out.println("children lvl2: " + nameLvl2);
                        
                        if (nameLvl2 == "sourceText"){
                            System.out.println("found sourceText");
                            List<Element> listElementLvl3 = elementSearchLvl2.getChildren();
                            for (int k=0; k<listElementLvl3.size(); k++){
                                Element elementSearchLvl3 = listElementLvl3.get(k);
                                String nameLvl3 = elementSearchLvl3.getName();
                                System.out.println("children lvl3: " + nameLvl3);
                                if (nameLvl3 == "Text"){
                                    
                                    sourceTextString = elementSearchLvl3.getValue();
                                    System.out.println("found tag text");
                                    
                                }
                            }
                        }
                    }
                    sourceTextString = elementSearch.getText();
                    System.out.println(sourceTextString);
                }
            }
        
        
        
    }
         catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return sourceTextString;
    }
    
    public String getTargetText(){
        String targetTextString=null;
        try{
            SAXBuilder XMLBuilder = new SAXBuilder();
            doc = XMLBuilder.build(DialogOpen.fileName);
            Element rootElement = doc.getRootElement();
            List<Element> listElement = rootElement.getChildren();
            
            for (int i=0; i<listElement.size(); i++){
                Element elementSearch = listElement.get(i);
                String name = elementSearch.getName();
                System.out.println(name);
                if(name=="FinalText"){
                    System.out.println("found FinalText");
                    targetTextString = elementSearch.getText();
                }
             }
        }
        catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return targetTextString;
  }
}

