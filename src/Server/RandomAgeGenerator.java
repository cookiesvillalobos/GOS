package Server;
 import java.util.Random;
 public class RandomAgeGenerator {
 	List<Integer> ListEges; 
	
	
	public RandomAgeGenerator() {
		List<Integer> list = new List<Integer>();
		int max = 1000;
		for (int i = 0; i >= max;i++) {
			list.addLast(i);
		}
		this.ListEges = list;
	}
	
	
	public int getRandomAge(){
		Random rn = new Random();
		int n = 1000 - 0 + 1;
		int i = rn.nextInt() % n;
		int randomNum =  0 + i;
		int num = ListEges.getData(randomNum);
		ListEges.deleteData(randomNum);
		return num;
	}
}