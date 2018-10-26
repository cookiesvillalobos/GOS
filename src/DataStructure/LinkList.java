package DataStructure;

/**
 * Esta clase para crear las listas enlazada
 * @param <T> genérico, porque acepta cualquier tipo de dato
 */

public class LinkList <T> {
    public Node<T> head = null;
    private int size = 0;

    public LinkList(){

    }

    /**
     * Este método funciona para añadir un dato anterior
     * @param object es el dato que se va a añadir
     */
    public void addPrev( T object){
        if (this.head == null){
            this.head = new Node<T>(object);
        }

        else {
            Node<T> temp = this.head;
            Node<T> element = new Node<T>(object);
            element.Add(temp);
            this.head = element;
        }
    }

    /**
     * Este método sirve para ver un dato en cierta posición
     * @param index cual posición de la lista se quiere ver
     * @return devulve el dato deseado
     */
    public T see (int index){
        Node<T> temp = this.head;

        for (int i=0; i < index; i++){
            temp = temp.viewNext();
        }
        return temp.viewElement();
    }


}
