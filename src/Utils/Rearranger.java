package Utils;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import DataStructure.LinkList;

public class Rearranger {
	
	//Method that receives a list and based on that return the number of columns that list should have
	public static int getColumns(LinkList list) {
		int columns = 0;
		int length = list.length();
		while(length != 0) {
			columns++;
			length = length / 2;
		} return columns;
	}
	
	
}
