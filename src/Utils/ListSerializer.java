package Utils;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

//JAXB imports
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//Gson imports
import com.google.gson.*;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;

//Local imports
import Server.List;
import Server.Nodo;
import Server.Dragon;
import Objects.Player;
import Objects.Enemy;

public class ListSerializer {
	
	private static JAXBContext context;
	
	//Method which receives a List<T> object and location to save the file. Then returns it as an XMLFile on the assign location
	public static <T> void marshall(List<T> list, String location) throws JAXBException {
		context = JAXBContext.newInstance(List.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(list, System.out);
		marshaller.marshal(list, new File(location));
		
	}
	
	//Method which receives a location of an XML file and parse it back to a List<T> object
	public static <T> List<T> unMarshall(String location) throws JAXBException {
		context = JAXBContext.newInstance(List.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) unmarshaller.unmarshal(new File(location));
		return list;	
		
	}
	
	//Methods that receives an XML as a Document object and converts it to String
	public static String XMLtoString(Document doc) throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
		return output;
	}
	
	//Method that receives a String XML and parses it as a Document Object
	public static Document StringtoXML(String doc) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder; 
	    builder = factory.newDocumentBuilder();  
	    Document document = builder.parse(new InputSource(new StringReader(doc)));
	    return document;
	}
	
	//Methods that receives a Object and returns it in a jsonString
	public static String toJsonString(Object obj) {
		return new Gson().toJson(obj);
	}
	
	//Method that receives a JSONString and return an object of the desire class
	public static Object fromJsonString(String json, Class T) {
		return new Gson().fromJson(json, T);
	}
	
}
