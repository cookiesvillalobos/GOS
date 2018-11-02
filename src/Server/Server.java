package Server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * Clase que maneja otras, crea dragones y los acomoda
 * ademas de seriaizar a Xml
 */
public class Server {
	
	private static final Logger logger = LoggerFactory.getLogger(Server.class);
	
	
	

	/*
	 * Funcion que crea una lista de dragonos,el tamaño de esta
	 * lista varia dependiendo de la wave. la ronda 1 empueza con 100
	 * dragones, en en cada nueva ronda, se suma el 20% de la anterior
	 * @param wave: ronda en la que se encuentra el juego
	 */
	public List<Dragon> getListaDragones(int wave){
		logger.info("Se inica la lista de los Dragones");
		RandomAgeGenerator rag = new RandomAgeGenerator();
		RandomNameGenerator rng = new RandomNameGenerator();
		List<Dragon> list = new List<Dragon>();
		int pos2 = 1;
		//Cantidad de dragones por oleada
		float num = 100;
		int a = 0;
		while(a != (wave-1)) {
			num = num + ((20/num)*100);
			a++;
		}
		num = (int)num;
		
			//Generar Comandante, El cual no posee Padre.
		Dragon drag = new Dragon();
		drag.setEdad(rag.getRandomAge());
		drag.setNombre(rng.getRandomName());
		drag.setClase(3);
		drag.setPos(pos2);
		list.addLast(drag);
		pos2++;
		
		//----Crear resto de dragones----
		for (int i = 0; i <= (num-1); i++) {
			drag = new Dragon();
			drag.setEdad(rag.getRandomAge());
			drag.setNombre(rng.getRandomName());
			list.addLast(drag);
			drag.setPos(pos2);
			pos2++;
		}
		list = asignarPadres(list);
		return list;
	}
	
	
	/*
	 * Funcion que dada una lista de dragones sin un padre 
	 * asignado en cada dragon, le coloca uno, con el objetivo de 
	 * despues crear una arbol de familias. El UNICO dragon sin padres 
	 * es el comandante que solo hay uno por ronda.
	 */
	private List<Dragon> asignarPadres(List<Dragon> list) {
		int max = (list.length()-2);
		int piv = 1;
		Nodo<Dragon> F = list.first;
		while (piv <= max) {
			Dragon d = list.getData(piv);
			d.setPadre(F.data);
			list.changeData(piv, d);
			F.data.setHijo1(list.getData(piv));
			
			piv++;
			d = list.getData(piv);
			d.setPadre(F.data);
			list.changeData(piv, d);
			F.data.setHijo2(list.getData(piv));
			piv++;
			
			F = F.next;
		}
		return list;
	}


	/*
	 * Todos los Sorts que son necesarios para jugar
	 * Estos tienen un 50% de ordenanrlos en forma acendente y 
	 * 50% que forma desendente
	 * 
	 * selectionSort Por edades
	 * insertionSort por velocidad de recarga
	 * quickSort por edad
	 * arbolBinario por familias
	 * arbolALV por edades
	 */
	
	//selectionSort Por edad  ----------------------------------------------
	public List<Dragon> selectionSort(List<Dragon> list){
		int ranNum = (int) (Math.random() * 100) + 1;
		if (ranNum < 50) {
			list = selectionSortM_m(list);
		}else {
			list = selectionSortm_M(list);
		}
		return list;
	}	
	private List<Dragon> selectionSortm_M(List<Dragon> list) {
		int n = list.length();
		for (int i = 0; i < (n-1); i++) {
			int mIndex = i;
			for (int j = i+1; j < n; j++) {
				if(list.getData(j).edad > list.getData(mIndex).edad) {
					mIndex = j;
				}	
			}
			Dragon temp = list.getData(mIndex);
			list.changeData(mIndex,list.getData(i));
			list.changeData(i,temp);
		}
		return list;
	}
	private List<Dragon> selectionSortM_m(List<Dragon> list) {
		int n = list.length();
		for (int i = 0; i < (n-1); i++) {
			int mIndex = i;
			for (int j = i+1; j < n; j++) {
				if(list.getData(j).edad < list.getData(mIndex).edad) {
					mIndex = j;
				}	
			}
			Dragon temp = list.getData(mIndex);
			list.changeData(mIndex,list.getData(i));
			list.changeData(i,temp);
		}
		return list;
	}
	
	
	//insertionSort por velocidad ----------------------------------------------
	public List<Dragon> insertionSort(List<Dragon> list){
		int ranNum = (int) (Math.random() * 100) + 1;
		if (ranNum < 50) {
			list = insertionSortM_m(list);
		}else {
			list = insertionSortm_M(list);
		}
		return list;
	}
	private List<Dragon> insertionSortm_M(List<Dragon> list) {
		int n = list.length(); 
	     for (int i=1; i<n; ++i){ 
	    	 Dragon temp = list.getData(i); 
	         int j = i-1; 
	         while (j >= 0 && list.getData(j).velocidadRecarga > temp.velocidadRecarga){ 
	        	 list.changeData(j+1, list.getData(j));
	             j = j-1; 
	            } 
	         list.changeData(j+1, temp);
	    } 
		return list;
	}
	private List<Dragon> insertionSortM_m(List<Dragon> list) {
		int n = list.length(); 
	     for (int i=1; i<n; ++i){ 
	    	 Dragon temp = list.getData(i); 
	         int j = i-1; 
	         while (j >= 0 && list.getData(j).velocidadRecarga < temp.velocidadRecarga){ 
	        	 list.changeData(j+1, list.getData(j));
	             j = j-1; 
	            } 
	         list.changeData(j+1, temp);
	    }	
		return list;
	}


	//quickSort por edad ----------------------------------------------
	public List<Dragon> quickSort(List<Dragon> list) {
		int ranNum = (int) (Math.random() * 100) + 1;
		if (ranNum < 50) {
			list = quickSortM_m(list);
		}else {
			list = quickSortm_M(list);
		}
		return list;
	}

	private List<Dragon> quickSortm_M(List<Dragon> list) {
		int low = 0;
		int high = list.length();
		return quickSortm_M_aux(list,low,(high -1));
	}
	private List<Dragon> quickSortm_M_aux(List<Dragon> list, int low ,int high){
		if (low < high) {
            int pi = partition1(list, low, high); 
            quickSortm_M_aux(list, low, pi-1); 
            quickSortm_M_aux(list, pi+1, high);
		}		
		return list;
	}

	private int partition1(List<Dragon> list, int low, int high) {
		Dragon pivot = list.getData(high);
		int i = (low-1);
		for (int j = low; j < high; j++){
			if (list.getData(j).edad <= pivot.edad) {
				i++;
				Dragon D = list.getData(i);
				list.changeData(i, list.getData(j));
				list.changeData(j, D);
			}
		}
		Dragon G = list.getData((i+1));
		list.changeData((i+1), list.getData(high));
		list.changeData(high, G);
		return i+1;
	}
	private List<Dragon> quickSortM_m(List<Dragon> list) {
		int low = 0;
		int high = list.length();
		return quickSortM_m_aux(list,low,(high -1));
	}
	private List<Dragon> quickSortM_m_aux(List<Dragon> list, int low, int high) {
		if (low < high) {
            int pi = partition(list, low, high); 
            quickSortm_M_aux(list, low, pi-1); 
            quickSortm_M_aux(list, pi+1, high);
		}		
		return list;
	}
	private int partition(List<Dragon> list, int low, int high) {
		Dragon pivot = list.getData(high);
		int i = (low-1);
		for (int j = low; j < high; j++){
			if (list.getData(j).edad >= pivot.edad) {
				i++;
				Dragon D = list.getData(i);
				list.changeData(i, list.getData(j));
				list.changeData(j, D);
			}
		}
		Dragon G = list.getData((i+1));
		list.changeData((i+1), list.getData(high));
		list.changeData(high, G);
		return i+1;
	}


	//arbolBinario por familias ----------------------------------------------
	public ArbolBinario arbolBinario(List<Dragon> list) {
		ListToTree p = new ListToTree();
		p.listToTree2(list);
		return p.b;
	}
	
	//arbolALV por edades ----------------------------------------------
	public AVLTree arbolALV(List<Dragon> list) {
		AVLTree a = new AVLTree();
		
		Nodo<Dragon> temp = list.first;
		while(temp.next != null) {
			a.insert(temp.data);
			temp = temp.next;
		}
		a.insert(temp.data);
		return a;
	}
	
}

