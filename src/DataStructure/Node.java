package DataStructure;

/**
 * Clase que tiene los nodos
 * @param <T> que las listas son genéricas
 */
public class Node <T> {
    private Node next;
    private T element;

    /**
     * Método para crear el nodo
     * @param element el dato que se va a agregar
     */
    public Node (T element){
        this.element = element;
    }

    /**
     * Método para ver el siguiente dato
     * @return devuelve el sig dato
     */
    public Node<T> viewNext (){
        return this.next;
    }

    /**
     * Método para agregar como siguiente
     * @param next dato que se va a agregar
     */
    public void Add (Node<T> next){
        this.next = next;
    }

    /**
     * Método para ver el elemento actual
     * @return devuelve el elemento actual
     */
    public T viewElement (){
        return this.element;
    }
}
