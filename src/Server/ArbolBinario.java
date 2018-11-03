package Server;

public class ArbolBinario {
	
	NodoAB raiz;
	public ArbolBinario() {	
	}
	
	public void addNodoReforma(Dragon data) {
		if (data == null) {
			return;
		}else {
			addNodoReforma(data, raiz);
		}	
	}
	private void addNodoReforma(Dragon data, NodoAB raiz2) {
		if ( raiz2 == null ) {
            raiz2 = new NodoAB(data,null);
        }else {
            if ( data.velocidadRecarga <= raiz2.dato.velocidadRecarga ) {    
                if (raiz2.getHojaIzquierda() == null) {
                    raiz2.setHojaIzquierda(new NodoAB(data,raiz2));
                } else {
                	addNodoReforma(data,raiz2.getHojaIzquierda());
                }
            }else {
                if (raiz2.getHojaDerecha() == null) {
                    raiz2.setHojaDerecha(new NodoAB(data,raiz2));
                }else {
                	addNodoReforma(data,raiz2.getHojaDerecha() );
                }
            }
        }
    }
	
	public NodoAB getRaiz() {
		return raiz;
	}
	public void setRaiz(NodoAB raiz) {
		this.raiz = raiz;
	}
}
