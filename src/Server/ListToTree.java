package Server;


public class ListToTree {
	
	ArbolBinario b;

	public void reforma(List<Dragon> list) {
		Nodo<Dragon> temp = list.first;
		b = new ArbolBinario();
		b.setRaiz(new NodoAB(temp.data,null));
		temp = temp.next;
		while(temp.next != null) {
			b.addNodoReforma(temp.data);
			temp = temp.next;
		}
		b.addNodoReforma(temp.data);
	}

}
