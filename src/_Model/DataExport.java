package _Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

// XML Writer DOM
public class DataExport {
	
	int antennaIndex = 0;
	

    private void writeXML(String fileName, Map<String, String> elementsMap) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        try{
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");
            //start writing xml file
            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeCharacters("\n");
            
            // Header
            xmlStreamWriter.writeStartElement("Simant_Settings");
            xmlStreamWriter.writeCharacters("\n\t");
            // Start element
            xmlStreamWriter.writeStartElement("Antenna");
            //write id as attribute
            xmlStreamWriter.writeAttribute("id", elementsMap.get("id"));
            //write other elements
            writeElement(xmlStreamWriter, "antenna", elementsMap);
            writeElement(xmlStreamWriter, "form", elementsMap);
            writeElement(xmlStreamWriter, "dLambda", elementsMap);
            writeElement(xmlStreamWriter, "direction", elementsMap);
            writeElement(xmlStreamWriter, "dirHauptKaeule", elementsMap);
            writeElement(xmlStreamWriter, "amplitude", elementsMap);
            writeElement(xmlStreamWriter, "sliderPercent", elementsMap);
            writeElement(xmlStreamWriter, "reflDist", elementsMap);
            writeElement(xmlStreamWriter, "refl", elementsMap);
            writeElement(xmlStreamWriter, "antVert", elementsMap);
            writeElement(xmlStreamWriter, "advanced", elementsMap);
            
            // ampArray
            for (int i = 0; i < elementsMap.size()-12; i++) {
            	writeElement(xmlStreamWriter, "ampArray"+i, elementsMap);
			}
            
            
            //write end tag of Employee element
            xmlStreamWriter.writeCharacters("\n\t");
            xmlStreamWriter.writeEndElement();
            
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndElement();
            
            //write end document
            xmlStreamWriter.writeEndDocument();
            
            //flush data to file and close writer
            xmlStreamWriter.flush();
            xmlStreamWriter.close();
            
        }catch(XMLStreamException | FileNotFoundException e){
            e.printStackTrace();
            Notification.error("Fehler beim Schreiben der Datei");
        }
    }
    
    private void writeElement(XMLStreamWriter smlSW, String element, Map<String, String> elementsMap) {
    	try {
			smlSW.writeCharacters("\n\t\t");
			smlSW.writeStartElement(element);
			smlSW.writeCharacters(elementsMap.get(element));
	    	smlSW.writeEndElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
    }
	
    private SimantInputData parseXML(String fileName) {
    	
    	XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    	SimantInputData data = new SimantInputData();
    	List<List<Double>> temp = new ArrayList<>();
    	
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            int event = xmlStreamReader.getEventType();
            while(true){
                switch(event) {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("Antenna")){
//                        emp.setId(Integer.parseInt(xmlStreamReader.getAttributeValue(0)));
                    }else if(xmlStreamReader.getLocalName().equals("antenna")){
                    	this.antennaIndex = 1;
                    }else if(xmlStreamReader.getLocalName().equals("form")){
                    	this.antennaIndex = 2;
                    }else if(xmlStreamReader.getLocalName().equals("dLambda")){
                    	this.antennaIndex = 3;
                    }else if(xmlStreamReader.getLocalName().equals("direction")){
                    	this.antennaIndex = 4;
                    }else if(xmlStreamReader.getLocalName().equals("dirHauptKaeule")){
                    	this.antennaIndex = 5;
                    }else if(xmlStreamReader.getLocalName().equals("amplitude")){
                    	this.antennaIndex = 6;
                    }else if(xmlStreamReader.getLocalName().equals("sliderPercent")){
                    	this.antennaIndex = 7;
                    }else if(xmlStreamReader.getLocalName().equals("reflDist")){
                    	this.antennaIndex = 8;
                    }else if(xmlStreamReader.getLocalName().equals("refl")){
                    	this.antennaIndex = 9;
                    }else if(xmlStreamReader.getLocalName().equals("antVert")){
                    	this.antennaIndex = 10;
                    }else if(xmlStreamReader.getLocalName().equals("advanced")){
                    	this.antennaIndex = 11;
                    }else {
                    	for (int i = 0; i < 9; i++) {
							if (xmlStreamReader.getLocalName().equals("ampArray"+i)) {
								this.antennaIndex = 12+i;
							}
						}
                    }
                    break;
                    
                case XMLStreamConstants.CHARACTERS:
                	
                	switch (antennaIndex) {
					case 1:		data.setAnt(Integer.parseInt(xmlStreamReader.getText())); break;
					case 2:		data.setForm(Integer.parseInt(xmlStreamReader.getText())); break;
					case 3:		data.setDLambda(Double.parseDouble(xmlStreamReader.getText())); break;
					case 4:		data.setDir(Integer.parseInt(xmlStreamReader.getText())); break;
					case 5:		data.setDirHauptk(Integer.parseInt(xmlStreamReader.getText())); break;
					case 6:		data.setAmp(Double.parseDouble(xmlStreamReader.getText())); break;
					case 7:		data.setAmpPercent(Double.parseDouble(xmlStreamReader.getText())); break;
					case 8:		data.setDist(Double.parseDouble(xmlStreamReader.getText())); break;
					case 9:		data.setReflektor(Boolean.parseBoolean(xmlStreamReader.getText())); break;
					case 10:	data.setAntVertikal(Boolean.parseBoolean(xmlStreamReader.getText())); break;
					case 11:	data.setAdvanced(Boolean.parseBoolean(xmlStreamReader.getText())); break;
					
					case 12:	List<Double> wordList1 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList1); break;
					case 13:	List<Double> wordList2 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList2); break;
					case 14:	List<Double> wordList3 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList3); break;
					case 15:	List<Double> wordList4 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList4); break;
					case 16:	List<Double> wordList5 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList5); break;
					case 17:	List<Double> wordList6 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList6); break;
					case 18:	List<Double> wordList7 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList7); break;
					case 19:	List<Double> wordList8 = Utility.getDoubleList(Arrays.asList(xmlStreamReader.getText().replace("[", "").replace("]", "").split(",")));
								temp.add(wordList8); break;
					}
                	antennaIndex = 0;
                    break;
                    
                case XMLStreamConstants.END_ELEMENT:
                    break;
                }
                
                if (!xmlStreamReader.hasNext()) { 

                	// set ampArray to DataType
                	data.setAmpArray(temp);
                	Notification.success("Einstellungen wurden eingelesen"); break; }
                	

              event = xmlStreamReader.next();
            }
            
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
            Notification.error("Fehler beim Einlesen der Datei");
        }
        return data;
    }
	
    /**
     * - Erstellt neue XML-Datei
     * - Speichert alle Eingabeparameter in die Datei
     * 
     * @param file	-> Dateipfad
     * @param data	-> Eingabeparameter
     * @throws IOException
     */
    public void saveXML(File file, SimantInputData data) throws IOException {
		
		DataExport xmlWriter = new DataExport();
        
        Map<String,String> elementsMap = new HashMap<String, String>();
        
        elementsMap.put("id", "1");
        elementsMap.put("antenna", ""+ 			data.getAnt());
        elementsMap.put("form", ""+ 			data.getForm());
        elementsMap.put("dLambda", ""+ 			data.getDLambda());
        elementsMap.put("direction", ""+ 		data.getDir());
        elementsMap.put("dirHauptKaeule", ""+ 	data.getDirHauptk());
        elementsMap.put("amplitude", ""+ 		data.getAmp());
        elementsMap.put("sliderPercent", ""+ 	data.getAmpPercent());
        elementsMap.put("reflDist", ""+ 		data.getDist());
        elementsMap.put("refl", ""+ 			data.getReflektor());
        elementsMap.put("antVert", ""+ 			data.getAntVertikal());
        elementsMap.put("advanced", ""+ 		data.getAdvanced());
        // loop through ampArray (Anzahl Kolonen)
        for (int i = 0; i < data.getAmpArray().size(); i++) {
        	elementsMap.put("ampArray"+i, ""+	data.getAmpArray().get(i));
		}
        
        xmlWriter.writeXML(file.toString(), elementsMap);
        
        Notification.success("Einstellungen erfolgreich gespeichert");
	}

    /**
     * - liest alle gespeicherten Eingabeparameter aus XML-Datei aus
     * 
     * @param file	-> Dateipfad
     * @return	-> alle Eingabeparameter
     * @throws IOException
     */
    public SimantInputData openXML(File file) throws IOException {		
        return parseXML(file.toString());
	}
}
