package Server;

public class ServerMain {

	public static void main(String[] args) {
		RandomAgeGenerator ages = new RandomAgeGenerator();
		
		
		Dragon dragon = new Dragon();
		dragon.setEdad(ages.getRandomAge());
		
		System.out.println(dragon.edad);

	}

}
