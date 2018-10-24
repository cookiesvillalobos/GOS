package Server;

public class NodoAB {
	
	Dragon dato;
	NodoAB padre;
	NodoAB hojaIzquierda;
	NodoAB hojaDerecha;
	
	
	public NodoAB(Dragon dato, NodoAB padre) {
		this.dato = dato;
		this.padre = padre;
	}
	public Dragon getData() {
		return dato;
	}
	public void setData(Dragon data) {
		this.dato = data;
	}
	public NodoAB getPadre() {
		return padre;
	}
	public void setPadre(NodoAB padre) {
		this.padre = padre;
	}
	public NodoAB getHojaIzquierda() {
		return hojaIzquierda;
	}
	public void setHojaIzquierda(NodoAB hojaIzquierda) {
		this.hojaIzquierda = hojaIzquierda;
	}
	public NodoAB getHojaDerecha() {
		return hojaDerecha;
	}
	public void setHojaDerecha(NodoAB hojaDerecha) {
		this.hojaDerecha = hojaDerecha;
	}
	
	
	
}
