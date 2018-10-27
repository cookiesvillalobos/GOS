package DataStructure;

import Server.List;

public class prueba {
	
	public static void main(String[] args ) {
		List<Integer> list = new List<>();
		
		list.addLast(8);
		list.addLast(7);
		list.addLast(9);
		list.addLast(10);
		
		for (int i = 0; i<4; i++) {
			System.out.println("inicio "+ i+ list.getData(i));
		}
		list.deleteData(0);
		
		for (int i = 0; i<4; i++) {
			System.out.println("fin"+ i +list.getData(i));
		}
		
	}

}
