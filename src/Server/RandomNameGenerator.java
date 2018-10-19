package Server;

public class RandomNameGenerator {
		
	List<String> listNames = new List<String>();
	List<String> listlastNames = new List<String>();
	
	
	public RandomNameGenerator() {
		listNames.addLast("Esben");listNames.addLast("Gerd");listNames.addLast("Gisli");
		listNames.addLast("Evar");listNames.addLast("Jorgen");listNames.addLast("Odín");
		listNames.addLast("Sigurd");listNames.addLast("Thor");listNames.addLast("Viggo");
		listNames.addLast("Aren");listNames.addLast("Axe");listNames.addLast("Haakon");
		listNames.addLast("Niels");listNames.addLast("Olaf");listNames.addLast("Karen");
		listNames.addLast("Nilsa");listNames.addLast("Astrid");listNames.addLast("Erika");
		listNames.addLast("Valkiria");listNames.addLast("Siriana");listNames.addLast("Ondina");
		
		
		listlastNames.addLast("Abels");listlastNames.addLast("Adamsen");listlastNames.addLast("Årud");
		listlastNames.addLast("Dahl");listlastNames.addLast("Haakonsson");listlastNames.addLast("Erickson");
		listlastNames.addLast("Holt");listlastNames.addLast("Kristiansen");listlastNames.addLast("Lange");
		listlastNames.addLast("Næss");listlastNames.addLast("Nielsen");listlastNames.addLast("Oliversen");
		listlastNames.addLast("Ostberg");listlastNames.addLast("Rolvsson");listlastNames.addLast("Spillum");
		listlastNames.addLast("Tennfjord");listlastNames.addLast("Vilhjalmsson");listlastNames.addLast("Wolff");
		listlastNames.addLast("Sørensen");listlastNames.addLast("Raske");listlastNames.addLast("Spillum");
		
	}
	
	public String getRandomName() {
		int randomNum = (int) (Math.random() * (listNames.length())) + 1;
		String N = listNames.getData(randomNum);
		
		int randomNum2 = (int) (Math.random() * (listlastNames.length())) + 1;
		String L = listlastNames.getData(randomNum2);
		
		String name = N + " " + L;
		return name;
	}
		
}
