package Server;

public class ArbolBinario {
	
	NodoAB raiz;
	
	public ArbolBinario(NodoAB raiz) {
		this.raiz = raiz;
	}
	
	public void addNodo( NodoAB nodo, NodoAB raiz ) {
        if ( raiz == null ) {
            this.raiz = nodo;
        }else {
            if ( nodo.dato.edad <= raiz.dato.edad ) {   
                if (raiz.getHojaIzquierda() == null) {
                    raiz.setHojaIzquierda(nodo);
                }
                else {
                    addNodo( nodo , raiz.getHojaIzquierda() );
                }
            }else {
                if (raiz.getHojaDerecha() == null) {
                    raiz.setHojaDerecha(nodo);
                }else {
                    addNodo( nodo, raiz.getHojaDerecha() );
                }
            }
        }
    }
 
    public void addNodo(NodoAB nodo ) {
        this.addNodo( nodo , this.raiz );
    }
	
    public void eliminarNodo(int pos) {
    	//Aliminar nodo y asignar padre a hijos
    }
	
}
