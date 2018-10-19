package Server;

public class RandomAgeGenerator {

	List<Integer> ListEges; 
	
	
	public RandomAgeGenerator() {
		List<Integer> list = new List<Integer>();
		int max = 1000;
		for (int i = 1; i <= max;i++) {
			list.addLast(i);
		}
		this.ListEges = list;
	}
	
	
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
