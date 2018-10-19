package Server;

public class ArbolBinario<T> {
	
	NodoAB<T> raiz;
	
	public ArbolBinario(NodoAB<T> raiz) {
		this.raiz = raiz;
	}
	
	public void addNodo( NodoAB<T> nodo, NodoAB<T> raiz ) {
        if ( raiz == null ) {
            this.raiz = nodo;
        }else {
            if ( nodo.dato <= raiz.dato ) {   // SERIAS DUDAS
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
 
    public void addNodo( NodoAB<T> nodo ) {
        this.addNodo( nodo , this.raiz );
    }
	
	
}
