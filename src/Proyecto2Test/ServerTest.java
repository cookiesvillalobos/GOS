package Proyecto2Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Server.AVLTree;
import Server.ArbolBinario;
import Server.Dragon;
import Server.List;
import Server.NodoAB;
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
		drag.setVelocidadRecarga(50);
		Dragon drag2 = new Dragon();
		drag2.setPadre(drag);
		drag2.setVelocidadRecarga(100);
		Dragon drag3 = new Dragon();
		drag3.setPadre(drag);
		drag3.setVelocidadRecarga(1);
		drag.setHijo1(drag2);
		drag.setHijo2(drag3);
		d.addLast(drag);
		d.addLast(drag2);
		d.addLast(drag3);
		ArbolBinario A = S.arbolBinario(d);
		int k = A.getRaiz().getData().getVelocidadRecarga();
		int m = A.getRaiz().getHojaDerecha().getData().getVelocidadRecarga();
		int v = A.getRaiz().getHojaIzquierda().getData().getVelocidadRecarga();
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
		int f = G.root.data.getAge();
		assertEquals(100, f);
		G.printBalance();
	}
	
	@Test
	void treeToList() {
		Server S = new Server();
		AVLTree j = new AVLTree();
		Dragon drag = new Dragon();
		drag.setEdad(50);
		Dragon drag2 = new Dragon();
		drag2.setEdad(100);
		Dragon drag3 = new Dragon();
		drag3.setEdad(300);
		Dragon drag4 = new Dragon();
		drag3.setEdad(500);
		Dragon drag5 = new Dragon();
		drag3.setEdad(600);
		Dragon drag6 = new Dragon();
		drag3.setEdad(1);
		Dragon drag7 = new Dragon();
		drag3.setEdad(10);
		Dragon drag8 = new Dragon();
		drag3.setEdad(20);
		j.insert(drag);
		j.insert(drag2);
		j.insert(drag3);
		j.insert(drag4);
		j.insert(drag5);
		j.insert(drag6);
		j.insert(drag7);
		j.insert(drag8);
		List<Dragon> d = S.treeToList(j);
		int o = d.length();
		assertEquals(8, o);
			
		}
		
	@Test
	void gettersSettersDrag(){
		Dragon j = new Dragon();
		Dragon drag = new Dragon();
		Dragon drag2 = new Dragon();
		Dragon drag3 = new Dragon();
		
		drag.setEdad(50);
		drag2.setEdad(100);
		drag3.setEdad(300);
		
		j.setClase(1);
		j.setEdad(2);
		j.setHijo1(drag);
		j.setHijo2(drag2);
		j.setPadre(drag3);
		j.setPos(3);
		j.setResistencia(4);
		j.setVelocidadRecarga(5);
		
		assertEquals(2,j.getAge());
		assertEquals("Infanteria",j.getClas());
		assertEquals(50, j.getHijo1().getAge());
		assertEquals(100,j.getHijo2().getAge());
		assertEquals(300,j.getFather().getAge());
		assertEquals(3,j.getPos());
		assertEquals(4,j.getResistencia());
		assertEquals(5,j.getVelocidadRecarga());
	}
	
	@Test
	void gettersSettersNodoAB(){
		Dragon drag = new Dragon();
		Dragon drag2 = new Dragon();
		drag2.setEdad(100);
		Dragon drag3 = new Dragon();
		drag3.setEdad(300);
		NodoAB i = new NodoAB(drag,null);
		NodoAB o = new NodoAB(drag3,null);
		
		i.setData(drag2);
		i.setPadre(o);
		i.setHojaDerecha(o);
		i.setHojaIzquierda(o);
		
		assertEquals(100,i.getData().getAge());
		assertEquals(300,i.getHojaDerecha().getData().getAge());
		assertEquals(300,i.getHojaIzquierda().getData().getAge());
		assertEquals(300,i.getPadre().getData().getAge());
	}
	
}
