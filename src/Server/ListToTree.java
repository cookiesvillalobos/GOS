package Server;

public class ListToTree {
	
	ArbolBinario b;
	
	public void listToTree2(List<Dragon> list) {
		b = new ArbolBinario();
		Dragon p = buscarPadre(list);
		if (p == null) {
			//NO se PUEDE CREAR UN ARBOL BINARIO
			//NO hay dragon asignable como raiz
			return;	
		}
		NodoAB nodo = new NodoAB(p,null);
		b.setRaiz(nodo);
		if(p.getHijo1() != null) {
			b.addNodo(p.getHijo1());
			agregarHijos(p.getHijo1());
		}if (p.getHijo2() != null) {
			b.addNodo(p.getHijo2());
			agregarHijos(p.getHijo2());
		}
	}
	
	private void agregarHijos(Dragon drag){
		if(drag.getHijo1() != null) {
			b.addNodo(drag.getHijo1());
			agregarHijos(drag.getHijo1());
		}if (drag.getHijo2() != null) {
			b.addNodo(drag.getHijo2());
			agregarHijos(drag.getHijo2());
		}
	}


	private Dragon buscarPadre(List<Dragon> list) {
		Nodo<Dragon> temp = list.first;
		while(temp.next != null) {
			if (temp.data.clase == 3) {
				return temp.data;
			}else if(temp.data.padre == null) {
				return temp.data;
			}
			temp = temp.next;
		}
		if (temp.data.clase == 3) {
			return temp.data;
		}
		return null;
	}
}
