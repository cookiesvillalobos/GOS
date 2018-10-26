package Server;

public class Dragon {
	// Atributes
	/*
	 * @param nombre : nombre generado aleatoriamente
	 * @param edad : edad del dragon, todos los dragones tienen diferentes edades.
	 * @param velocidadRecarga: va de 1 a 100, es aleatorio
	 * @param resistencia: va de 1 a 3, indica cuantos disparos resiste
	 * @param clase: puede ser comandante (3), capitanes(2) o infanteria (1)
	 * @param padre : todos los dragones tienen un padre, menos uno por oleada.
	 * 
	 */
	
	String nombre;
	int edad;
	int velocidadRecarga;
	int resistencia;
	int clase;
	Dragon padre;
	
	/*
	 * Contructor
	 * La velocidad de recarga se asigna aleatoriamente de 1 a 100
	 * La resistencia se asigna aleatoriamente de 1 a 3
	 * La clase se asigna aleatoriamente, hay 70% posibilidades
	 * de ser infanteria y un 30% de ser capitan. 
	 */
	public Dragon() {
		//Velocidad de Recarga
		int Velocidad = (int) (Math.random() * 100) + 1;
		this.velocidadRecarga = Velocidad;
		
		//Resistencia
		int Resistencia = (int) (Math.random() * 3) + 1;
		this.resistencia = Resistencia;
		
		//Clase
		int Clase = (int) (Math.random() * 100) + 1;
				//Hay 70 porciento posibilidades de que sea infanteria
		if (Clase >= 70) {
			this.clase = 1;
		}else {
			this.clase = 2;
		}
	}
	/*
	 * Getters and Setters
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setPadre(Dragon padre) {
		this.padre = padre;
	}
	public void setClase(int clase) {
		this.clase = clase;
	}

	
}
