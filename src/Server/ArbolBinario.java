package Server;

public class ArbolBinario {
	
	NodoAB raiz;

	private void addNodo(Dragon drag, Nodo<Dragon> padre, NodoAB raiz) {
		if (raiz.dato == null || padre.data == null) {
			return;
		}
       if (raiz.dato.edad == padre.data.edad) {
    	   if (raiz.getHojaDerecha() == null) {
    		   raiz.setHojaDerecha(new NodoAB(drag,raiz));
    	   }else if (raiz.getHojaIzquierda() == null) {
    		   raiz.setHojaIzquierda(new NodoAB(drag,raiz));
    	   }
    	   return;
       }else {
    	   if (raiz.hojaDerecha != null) {
    		   addNodo(drag,padre,raiz.hojaDerecha);
    	   }
    	   if (raiz.hojaIzquierda != null) {
    		   addNodo(drag,padre,raiz.hojaIzquierda);
    	   }
       }
       return;
    }


    public void eliminarNodo(int pos) {
    	//Aliminar nodo y asignar padre a hijos
    }
    
    private List<Dragon> getHijos(List<Dragon> lista, Dragon padre) {
    	Nodo<Dragon> temp = lista.first;
    	List<Dragon> CS = new List<Dragon>();
    	int cont = 0;
    	while(temp.next != null) {
    		if (temp.data.padre.edad == padre.edad) {
    			CS.addLast(temp.data);
    			cont++;
    		}
    		temp = temp.next;
    	}
    	if (temp.data.padre.edad == padre.edad) {
			CS.addLast(temp.data);
			cont++;
    	}
    	if(cont == 0) {
    		return null;
    	}
    	return CS;
    }
  
    
    public void la(List<Dragon> list) {
    	int cont = 0;
    	Nodo<Dragon> temp = list.first;
    	if(raiz == null) {
    		this.raiz = new NodoAB(temp.data, null);
    		cont++;
    	}
    	
    	while(temp.next != null) {
    		if (cont == 0) {
    			addPadre(temp,list);
    		}
    		cont = 0;
    		addhijos(temp, list);
    		temp = temp.next;
    	}
    	addhijos(temp, list);
    }
    
    private void addhijos(Nodo<Dragon> tata, List<Dragon> list) {
    	List<Dragon> l = getHijos(list,tata.data);
    	if(l == null || l.length() == 1) {
    		return;
    	}
    	if (l.getData(1) != null && l.getData(2) != null)
    		addNodo(l.getData(1), tata, raiz);
    		addNodo(l.getData(2), tata, raiz);
    }
    
    private void addPadre(Nodo<Dragon> padre, List<Dragon> list) {
    	addNodoPadre(padre.data,list);
    }


	private void addNodoPadre(Dragon data,List<Dragon> list) {
		Nodo<Dragon> temp = list.first;
		while(temp.next != null) {
			if (temp.data.edad == data.padre.edad) {
				addNodo(data,temp,raiz);
			}
			temp= temp.next;
		}
		if (temp.data.edad == data.padre.edad) {
			addNodo(data,temp,raiz);
		}
	}
    
    
}

