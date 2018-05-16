package _Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
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
            writeElement(xmlStreamWriter, "quantity", elementsMap);
            writeElement(xmlStreamWriter, "direction", elementsMap);
            writeElement(xmlStreamWriter, "amplitude", elementsMap);
            writeElement(xmlStreamWriter, "sliderPercent", elementsMap);
            
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
                    }else if(xmlStreamReader.getLocalName().equals("quantity")){
                    	this.antennaIndex = 3;
                    }else if(xmlStreamReader.getLocalName().equals("dLambda")){
                    	this.antennaIndex = 4;
                    }else if(xmlStreamReader.getLocalName().equals("direction")){
                    	this.antennaIndex = 5;
                    }else if(xmlStreamReader.getLocalName().equals("amplitude")){
                    	this.antennaIndex = 6;
                    }else if(xmlStreamReader.getLocalName().equals("sliderPercent")){
                    	this.antennaIndex = 7;
                    }
                    break;
                    
                case XMLStreamConstants.CHARACTERS:
                	switch (antennaIndex) {
					case 1:		data.setAnt(Integer.parseInt(xmlStreamReader.getText()));
						System.out.println("antenna " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 2:		data.setForm(Integer.parseInt(xmlStreamReader.getText()));
						System.out.println("form " + (xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 3:		data.setQuant(Integer.parseInt(xmlStreamReader.getText()));
						System.out.println("quantity " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 4:		data.setDLambda(Double.parseDouble(xmlStreamReader.getText()));
						System.out.println("dLambda " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 5:		data.setDir(Integer.parseInt(xmlStreamReader.getText()));
						System.out.println("direction " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 6:		data.setAmp(Double.parseDouble(xmlStreamReader.getText()));
						System.out.println("amplitude " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 7:		data.setAmpPercent(Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					}
                    break;
                    
                case XMLStreamConstants.END_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("Employee")){
                    	
                    }
                    break;
                }
                
                if (!xmlStreamReader.hasNext()) { break; }
                	

              event = xmlStreamReader.next();
            }
            
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return data;
    }
	
    /**
     * Creates a XML File and saves its content
     * 
     * @param file	-> Save File to this Data Path
     * @param data	-> Data to save
     * @throws IOException
     */
    public void saveXML(File file, SimantInputData data) throws IOException {
		
		DataExport xmlWriter = new DataExport();
        
        Map<String,String> elementsMap = new HashMap<String, String>();
        
        elementsMap.put("id", "1");
        elementsMap.put("antenna", ""+ 			data.getAnt());
        elementsMap.put("form", ""+ 			data.getForm());
        elementsMap.put("quantity", ""+ 		data.getQuant());
        elementsMap.put("dLambda", ""+ 			data.getDLambda());
        elementsMap.put("direction", ""+ 		data.getDir());
        elementsMap.put("amplitude", ""+ 		data.getAmp());
        elementsMap.put("sliderPercent", ""+ 	data.getAmpPercent());
        
        xmlWriter.writeXML(file.toString(), elementsMap);
	}

    /**
     * Opens a XML File and reads out its values
     * 
     * @throws IOException
     */
    public SimantInputData openXML(File file) throws IOException {		
        return parseXML(file.toString());
	}
}
