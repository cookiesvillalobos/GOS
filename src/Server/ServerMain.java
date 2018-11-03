package Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {

	public static void main(String[] args) {
		
		final Logger logger = LoggerFactory.getLogger(ServerMain.class);
		
		RandomAgeGenerator ages = new RandomAgeGenerator();
		logger.info("Se inica la lista de los Dragones");
		logger.error("Prueba 1");
		logger.debug("PRUEBA FADA ");
		
		Dragon dragon = new Dragon();
		dragon.setEdad(ages.getRandomAge());
		
		System.out.println(dragon.edad);
		
		System.out.println("------------- PRUEBA PARA LISTAS ENLAZADAS -------------");
		List<Integer> l = new List<Integer>();
		l.addLast(1);
		l.addLast(2);
		l.addLast(3);
		l.addLast(4);
		System.out.println("Tiene que dar (4). Length: "+ l.length());
		System.out.println("Tiene que ser (2). Dato: "+ l.getData(1));
		System.out.println("Se borra un dato");
		l.deleteData(1);
		System.out.println("Tiene que dar (3). Length: "+ l.length());
		System.out.println("Tiene que ser (3). Dato: "+ l.getData(1));
		l.deleteData(2);
		System.out.println("Tiene que dar (2). Length: "+ l.length());
		System.out.println("Tiene que ser (1). Dato: "+ l.getData(0));
		
		Server S = new Server();
		List<Dragon> d = S.getListaDragones(1);
		System.out.println("lista dragones:  "+ d.length());
		System.out.println("Nombre es: "+ d.getData(52).padre.nombre);
		
		System.out.println("------------- PRUEBA sorts -------------");
		S.selectionSort(d);
		System.out.print("Edades: "+ d.getData(5).edad);
		System.out.print(" - "+ d.getData(6).edad);
		System.out.println(" - "+ d.getData(7).edad);
		
		S.insertionSort(d);
		System.out.print("Velocidad: "+ d.getData(5).velocidadRecarga);
		System.out.print(" - "+ d.getData(6).velocidadRecarga);
		System.out.println(" - "+ d.getData(7).velocidadRecarga);
		
		S.quickSort(d);
		System.out.print("Edad: "+ d.getData(5).edad);
		System.out.print(" - "+ d.getData(6).edad);
		System.out.println(" - "+ d.getData(7).edad);
		
		System.out.println("------------- PRUEBA Arboles -------------");
		ArbolBinario A = S.arbolBinario(d);
		System.out.println(A.raiz.dato.nombre);
		System.out.println(A.raiz.hojaDerecha.dato.nombre);
		System.out.println(A.raiz.hojaIzquierda.dato.nombre);
		System.out.println(A.raiz.hojaIzquierda.hojaIzquierda.dato.nombre);
		
		System.out.println("------------- PRUEBA Arboles AVL-------------");
		
		AVLTree G = S.arbolALV(d);
		System.out.println(G.root.data.nombre);

		System.out.println("--------- PRUEBA Arboles AVL a Lista --------");
		List<Dragon> R = S.treeToList(G);
		System.out.println(R.length());
	}

}
