package Proyecto2Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Server.Dragon;
import Server.List;
import Server.Server;

class ServerTest {

	@Test
	void testGetListaDragones() {
		Server S = new Server();
		List<Dragon> d = S.getListaDragones(1);
		int f = d.length();
		assertEquals(100, f);	
	}

	@Test
	void testSelectionSort() {
		Server S = new Server();
		List<Dragon> d = S.getListaDragones(1);
		S.selectionSort(d);
		int f = d.length();
		assertEquals(100, f);
	}

	@Test
	void testInsertionSort() {
		Server S = new Server();
		List<Dragon> d = S.getListaDragones(1);
		S.insertionSort(d);
		int f = d.length();
		assertEquals(100, f);
	}

	@Test
	void testQuickSort() {
		Server S = new Server();
		List<Dragon> d = S.getListaDragones(1);
		S.quickSort(d);
		int f = d.length();
		assertEquals(100, f);
	}

	@Test
	void testArbolBinario() {
		fail("Not yet implemented");
	}

	@Test
	void testArbolALV() {
		fail("Not yet implemented");
	}

}
