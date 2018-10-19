package Server;

public class List<T> {
	
	Nodo<T> first = null;
	
	
	public void addLast(T data) {
		if (first == null) {
			first = new Nodo<T>(data);
		}else {
			Nodo<T> temp = this.first;
			while(temp.next != null) {
				temp= temp.next;
			}
			temp.next = new Nodo<T>(data);
			}
		}
	

	public T getData(int num) {
		if (first == null) {
			return null;
		}
		Nodo<T> temp = first;
		try {
			for(int i=0; i <= (num-1); i++) {
				temp = temp.next;
			}
			return temp.data;
			
		}catch(Exception ex) {
			return null;	
		}
	}
		


	public void deleteData(int num) {
		if (first == null) {
			return;
		}
		Nodo<T> temp = first;
		try {
			for(int i=0; i <= (num-2); i++) {
				temp = temp.next;
			}
			if (temp.next != null) {
				Nodo<T> temp2 = temp.next;
				if(temp2.next != null) {
					temp.next = temp2.next;
				}
				else if (temp2.next == null){
					temp.next = null;
				}
			}	
		}catch(Exception ex) {
			throw new Error("La lista no tiene esa cantidad de datos");	
		}
		return;
	}
	
	public int length() {
		Nodo<T> temp = first;
		int i = 1;
		while(temp.next != null) {
			temp = temp.next;
			i++;
		}
		return i;
	}


	public void changeData(int pos, T data) {
		if (first == null) {
			return;
		}
		Nodo<T> temp = first;
		try {
			for(int i=0; i <= (pos-1); i++) {
				temp = temp.next;
			}
			temp.data = data;
			
		}catch(Exception ex) {
			return;	
		}
		
	}
		
}
