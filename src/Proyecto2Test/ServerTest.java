package Proyecto2Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Server.AVLTree;
import Server.ArbolBinario;
import Server.Dragon;
import Server.List;
import Server.Server;


class ServerTest {
	

	@Test
	void testGetListaDragones() {
		ListTest lt = new ListTest();
		lt.testAddLast();
		lt.testChangeData();
		lt.testDeleteData();
		lt.testGetData();
		lt.testLength();
		
		Server S = new Server();
		List<Dragon> d = S.getListaDragones(1);
		int f = d.length();
		assertEquals(100, f);	
	}

	@Test
	void testSelectionSort() {
		Server S = new Server();
		List<Dragon> d = new List<Dragon>();
		Dragon drag = new Dragon();
		drag.setEdad(1);
		Dragon drag2 = new Dragon();
		drag2.setEdad(100);
		Dragon drag3 = new Dragon();
		drag3.setEdad(300);
		d.addLast(drag);
		d.addLast(drag2);
		d.addLast(drag3);
		S.selectionSort(d);
		int f = d.length();
		assertEquals(3, f);		
	}

	@Test
	void testInsertionSort() {
		Server S = new Server();
		List<Dragon> d = new List<Dragon>();
		Dragon drag = new Dragon();
		drag.setVelocidadRecarga(1);
		Dragon drag2 = new Dragon();
		drag2.setVelocidadRecarga(2);
		Dragon drag3 = new Dragon();
		drag3.setVelocidadRecarga(3);
		d.addLast(drag);
		d.addLast(drag2);
		d.addLast(drag3);
		S.insertionSort(d);
		int f = d.length();
		assertEquals(3, f);	
	}

	@Test
	void testQuickSort() {
		Server S = new Server();
		List<Dragon> d = new List<Dragon>();
		Dragon drag = new Dragon();
		drag.setEdad(1);
		Dragon drag2 = new Dragon();
		drag2.setEdad(100);
		Dragon drag3 = new Dragon();
		drag3.setEdad(300);
		d.addLast(drag);
		d.addLast(drag2);
		d.addLast(drag3);
		S.quickSort(d);
		int f = d.length();
		assertEquals(3, f);
	}

	@Test
	void testArbolBinario() {
		Server S = new Server();
		List<Dragon> d = new List<Dragon>();
		Dragon drag = new Dragon();
		drag.setEdad(50);
		Dragon drag2 = new Dragon();
		drag2.setPadre(drag);
		drag2.setEdad(100);
		Dragon drag3 = new Dragon();
		drag3.setPadre(drag);
		drag3.setEdad(1);
		drag.setHijo1(drag2);
		drag.setHijo2(drag3);
		d.addLast(drag);
		d.addLast(drag2);
		d.addLast(drag3);
		ArbolBinario A = S.arbolBinario(d);
		int k = A.getRaiz().getData().getEdad();
		int m = A.getRaiz().getHojaDerecha().getData().getEdad();
		int v = A.getRaiz().getHojaIzquierda().getData().getEdad();
		assertEquals(50, k);
		assertEquals(100, m);
		assertEquals(1, v);
	}

	@Test
	void testArbolALV() {
		Server S = new Server();
		List<Dragon> d = new List<Dragon>();
		Dragon drag = new Dragon();
		drag.setEdad(1);
		Dragon drag2 = new Dragon();
		drag2.setEdad(100);
		Dragon drag3 = new Dragon();
		drag3.setEdad(300);
		d.addLast(drag);
		d.addLast(drag2);
		d.addLast(drag3);
		AVLTree G = S.arbolALV(d);
		int f = G.root.data.getEdad();
		assertEquals(100, f);
	}

}
