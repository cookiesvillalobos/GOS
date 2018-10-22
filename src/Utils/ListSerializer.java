package Utils;

import java.io.File;

//JAXB imports
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

//Local imports
import Server.List;

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

}
