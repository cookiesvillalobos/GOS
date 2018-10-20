package Server;
/*
 * Clase capas de crear numeros aleatorios de 1 a 1000
 * pero sin que se repitan
 */
public class RandomAgeGenerator {
	/*
	 * @param ListEges: lista generica con las edades que no se 
	 * han usado
	 */
	List<Integer> ListEges; 
	
	/*
	 * Constructor:
	 * rellena la lista con 999 elementos tipo entero
	 */
	public RandomAgeGenerator() {
		List<Integer> list = new List<Integer>();
		int max = 1000;
		for (int i = 1; i <= max;i++) {
			list.addLast(i);
		}
		this.ListEges = list;
	}
	
	/*
	 * Funcion que toma un elemento aleatorio de la lista
	 * lo retorna, pero anteas lo elimina de la lista
	 * para que no se repita.
	 */
	public int getRandomAge(){
		int randomNum = (int) (Math.random() * (ListEges.length())) + 1;
		if (randomNum < 0 || randomNum > ListEges.length()) {
			int num = getRandomAge();
			return num;
		}
		int num = ListEges.getData(randomNum);
		ListEges.deleteData(randomNum);
		return num;
	}
}
