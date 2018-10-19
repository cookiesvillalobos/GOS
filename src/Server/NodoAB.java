package Server;

public class NodoAB<T> {
	
	T dato;
	NodoAB<T> padre;
	NodoAB<T> hojaIzquierda;
	NodoAB<T> hojaDerecha;
	
	
	
	public T getData() {
		return dato;
	}
	public void setData(T data) {
		this.dato = data;
	}
	public NodoAB<T> getPadre() {
		return padre;
	}
	public void setPadre(NodoAB<T> padre) {
		this.padre = padre;
	}
	public NodoAB<T> getHojaIzquierda() {
		return hojaIzquierda;
	}
	public void setHojaIzquierda(NodoAB<T> hojaIzquierda) {
		this.hojaIzquierda = hojaIzquierda;
	}
	public NodoAB<T> getHojaDerecha() {
		return hojaDerecha;
	}
	public void setHojaDerecha(NodoAB<T> hojaDerecha) {
		this.hojaDerecha = hojaDerecha;
	}
	
	
	
}
