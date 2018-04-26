package _Model;

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
	
	static int antennaIndex = 0;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    private static Map<String, String> parseXML(String fileName) {
    	XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            int event = xmlStreamReader.getEventType();
            while(true){
                switch(event) {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("Antenna")){
//                        emp.setId(Integer.parseInt(xmlStreamReader.getAttributeValue(0)));
                    }else if(xmlStreamReader.getLocalName().equals("antenna")){
                    	antennaIndex = 1;
                    }else if(xmlStreamReader.getLocalName().equals("form")){
                    	antennaIndex = 2;
                    }else if(xmlStreamReader.getLocalName().equals("quantity")){
                    	antennaIndex = 3;
                    }else if(xmlStreamReader.getLocalName().equals("dLambda")){
                    	antennaIndex = 4;
                    }else if(xmlStreamReader.getLocalName().equals("direction")){
                    	antennaIndex = 5;
                    }else if(xmlStreamReader.getLocalName().equals("amplitude")){
                    	antennaIndex = 6;
                    }
                    break;
                    
                case XMLStreamConstants.CHARACTERS:
                	switch (antennaIndex) {
					case 1:		System.out.println("antenna " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 2:		System.out.println("form " + (xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 3:		System.out.println("quantity " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 4:		System.out.println("dLambda " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 5:		System.out.println("direction " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
					case 6:		System.out.println("amplitude " + Double.parseDouble(xmlStreamReader.getText())); antennaIndex = 0;	break;
							
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
        return null;
    }
	
    /**
     * Creates a XML File and saves its content
     * 
     * @throws IOException
     */
    public static void saveXML() throws IOException {
		
		String fileName = ""+				null;
        
        DataExport xmlWriter = new DataExport();
        
        Map<String,String> elementsMap = new HashMap<String, String>();
        
        elementsMap.put("id", "1");
//        elementsMap.put("antenna", ""+ 		SubscribeOld.antenna.get());
//        elementsMap.put("form", ""+ 		SubscribeOld.form.get());
//        elementsMap.put("quantity", ""+ 	SubscribeOld.quantity.get());
//        elementsMap.put("dLambda", ""+ 		SubscribeOld.dLambda.get());
//        elementsMap.put("direction", ""+ 	SubscribeOld.direction.get());
//        elementsMap.put("amplitude", ""+ 	SubscribeOld.amplitude.get());
        
        xmlWriter.writeXML(fileName, elementsMap);
	}

    /**
     * Opens a XML File and reads out its values
     * 
     * @throws IOException
     */
    public static void openXML() throws IOException {
    	
		String fileName = ""+				null;
		
        parseXML(fileName);
	}
}
