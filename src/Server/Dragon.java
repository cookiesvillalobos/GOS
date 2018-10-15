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
	
	public Dragon() {
		//Velocidad de Recarga
		int Velocidad = (int) (Math.random() * 100) + 1;
		this.velocidadRecarga = Velocidad;
		
		//Resistencia
		int Resistencia = (int) (Math.random() * 3) + 1;
		this.resistencia = Resistencia;
		
		//Clase ** nose si se puede crear aqui
		
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
 	public void setEdad(int edad) {
		this.edad = edad;
	}
 	public void setPadre(Dragon padre) {
		this.padre = padre;
	}
	
	
}