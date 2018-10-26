package Testing_package;

import Utils.ListSerializer;

import javax.xml.bind.JAXBException;

import Server.List;
import Server.Nodo;

public class Main {
	
	public static void main(String[] args) throws JAXBException {
		List test = new List();
		test.addLast("Im first");
		test.addLast("Im second");
		test.addLast("Im third");
		ListSerializer.marshall(test, "src//TrashBin//test.xml");
	}

}
