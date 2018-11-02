package Server;

public class ArbolBinario {
	
	NodoAB raiz;



	public ArbolBinario() {
		
	}


	public void addNodo(Dragon drag) {
		if (drag == null) {
			return;
		}else {
			addNodo(drag, raiz);
		}	
	}


	private void addNodo(Dragon drag, NodoAB raiz2) {
		if(raiz2 == null) {
			return;
		}
		if (raiz2.dato.edad == drag.padre.edad) {
			NodoAB nodo = new NodoAB(drag,raiz);
			if (raiz2.getHojaDerecha() == null) {
				raiz2.setHojaDerecha(nodo);
			}else {
				raiz2.setHojaIzquierda(nodo);
			}
		}else {
			addNodo(drag,raiz2.hojaDerecha);
			addNodo(drag,raiz2.hojaIzquierda);
		}
		
	}
	
	public NodoAB getRaiz() {
		return raiz;
	}


	public void setRaiz(NodoAB raiz) {
		this.raiz = raiz;
	}
}
