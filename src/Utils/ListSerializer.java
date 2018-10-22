package Utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

//Local imports
import Server.List;

public class ListSerializer {
	
	JAXBContext context;
	
	public ListSerializer() throws JAXBException {
		//this.context = JAXBContext.newInstance(List.class);
	}
	
	public <T> void marshall(List<T> list) throws JAXBException {
		this.context = JAXBContext.newInstance(List.class);
		Marshaller marshaller = this.context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(list, (Result) System.in);
		marshaller.marshal(list, new File("src\\TrashBin\\list.xml"));
		
	}
	
	public void unMarshall() {
		
	}

}
