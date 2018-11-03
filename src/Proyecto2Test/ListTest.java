package Proyecto2Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import Server.List;

class ListTest {

	@Test
	void testAddLast() {
		List<Integer> i = new List<Integer>();
		i.addLast(1);
		i.addLast(2);
		i.addLast(3);
		i.addLast(4);
		int f = i.getData(1);
		assertEquals(2, f);
		
	}

	@Test
	void testGetData() {
		List<Integer> i = new List<Integer>();
		i.addLast(1);
		i.addLast(2);
		i.addLast(3);
		i.addLast(4);
		int f = i.getData(0);
		int w = i.getData(1);
		int e = i.getData(2);
		assertEquals(1, f);
		assertEquals(2, w);
		assertEquals(3, e);
		assertNull(i.getData(6));
	}

	@Test
	void testDeleteData() {
		List<Integer> i = new List<Integer>();
		i.addLast(1);
		i.addLast(2);
		i.addLast(3);
		i.addLast(4);
		i.deleteData(1);
		int f = i.getData(1);
		assertEquals(3, f);
	}

	@Test
	void testLength() {
		List<Integer> i = new List<Integer>();
		i.addLast(1);
		i.addLast(2);
		i.addLast(3);
		i.addLast(4);
		int y = i.length();
		i.deleteData(1);
		int f = i.length();
		assertEquals(4, y);
		assertEquals(3, f);
	}

	@Test
	void testChangeData() {
		List<Integer> i = new List<Integer>();
		i.addLast(1);
		i.addLast(2);
		i.addLast(3);
		i.addLast(4);
		int f = i.getData(1);
		i.changeData(1, 5);
		int u = i.getData(1);
		assertEquals(2, f);
		assertEquals(5, u);
	}

}
