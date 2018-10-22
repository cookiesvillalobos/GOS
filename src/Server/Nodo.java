package Server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Nodo")
 public class Nodo<T> {
	
	T data;
	
	public T getData() {
		return this.data;
	}
	
	Nodo<T> next;
	
	
	public Nodo(T data2) {
		this.data = data2;
		this.next = null;
	}
}