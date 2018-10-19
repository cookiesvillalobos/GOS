package Server;

public class Server {

	
	public List<Dragon> getListaDragones(int wave){
		RandomAgeGenerator rag = new RandomAgeGenerator();
		RandomNameGenerator rng = new RandomNameGenerator();
		List<Dragon> list = new List<Dragon>();
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
		
		//----Crear resto de dragones----
		for (int i = 0; i <= (num-1); i++) {
			drag = new Dragon();
			drag.setEdad(rag.getRandomAge());
			drag.setNombre(rng.getRandomName());
			list.addLast(drag);
		}
		list = asignarPadres(list);
		return list;
	}
	
	
	
	private List<Dragon> asignarPadres(List<Dragon> list) {
		int max = (list.length()-2);
		int piv = 0;
		Nodo<Dragon> F = list.first;
		while (piv <= max) {
			Dragon d = list.getData(piv);
			d.setPadre(F.data);
			list.changeData(piv, d);
			piv++;
			Dragon m = list.getData(piv);
			m.setPadre(F.data);
			list.changeData(piv, m);
			piv++;
			F = F.next;
		}
		return list;
	}



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
	public void quickSort() {
		
	}
	
	//arbolBinario por familias ----------------------------------------------
	public void arbolBinario() {
		
	}
	
	//por edades ----------------------------------------------
	public void arbolALV() {
		
	}
	
}

