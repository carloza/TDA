package TDACola;
/**
 * Implementacion del TDA Cola. esta implementacion funciona y ofrece metedos para una cola simple, 
 * su funcionamiento consta de una secuecia de elementos, donde los extremos se distinguen como "head"
 * y "tail", los elementos ingresan por "tail" a la secuencia y son extraidos por "head". para esta
 * implementacion se usa un arreglo, que eventualmente se duplicará si este llena; recordemos
 * que los arreglos son estructuras estaticas, y cuando hablamos de duplicar el tamaño, hablamos de
 * traspasar del arreglo original a otro del doble de tamaño, esto nos lleva a pensar que en algun 
 * momento debemos ejecutar algun procedimiento que tiene un orde de ejecucion O(n), pero resulta
 * que esto no es asi, ya que este evento sucede cuando el arreglo esta lleno, osea, cuando se 
 * realiza n inserciones; en el peor caso inserto siempre y llegada a la insercion n se produce el
 * traspaso de un arreglo a otro, en este punto tenemos n inserciones y n traspasos para n entradas,
 * lo que nos dá una insercion y un traspaso para cada entrada ingresada (las inserciones son
 * constantes y el traspaso es solo asignacion de referencia) que en conclucion nos queda de orden
 * constante
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 * @param <E> el parametro E es el tipo (generico) de elementos que la lista va a manejar
 */
public class ArrayQueue<T> implements Queue<T> {
	
	//ATRIBUTOS
	private T[] arreglo;
	private int cant,head,tail;
	
	/**
	 * constructor de la clase "ArrayQueue", crea un Cola vacia, no recibe parametros
	 * O(1)
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(){
		arreglo = (T[]) new Object[32];
		cant = head = tail = 0;
	}
	
	/**
	 * metodo que devuelve la cantidad de elementos en la cola
	 * O(1)
	 * 
	 * @return la cantidad de elementos en la cola, cero si la cola esta vacia
	 */
	@Override
	public int size() {
		return cant;
	}
	
	/**
	 * metodo que consulta si la cola está vacía
	 * O(1)
	 * 
	 * @return true si la cola está vacía, false en caso contrario
	 */
	@Override
	public boolean isEmpty() {
		return (cant==0);
	}
	
	/**
	 * metodo que inspecciona el elemento que se encuentra en el frente de la cola, no lo desencola
	 * sino que solo lo muestra
	 * O(1)
	 * 
	 * @return el elemento que se encuentra en el frente de la cola
	 * @throws EmptyQueueException si la cola está vacía
	 */
	@Override
	public T front() throws EmptyQueueException {
		if(isEmpty()) throw new EmptyQueueException();
		return arreglo[head];
	}
	
	/**
	 * metodo que inserta un elemento en el final de la cola
	 * O(1)
	 * 
	 * @param element Nuevo elemento a insertar
	 */
	@Override
	public void enqueue(T element) {
		if(arreglo.length==cant) duplicar();
		arreglo[tail] = element;
		tail = (tail+1)%arreglo.length;
		cant++;
	}
	
	@SuppressWarnings("unchecked")
	private void duplicar() {
		T[] aux = (T[]) new Object[arreglo.length*2];
		for(int i = 0; i<cant; i++){
			aux[i]= arreglo[(head+i)%cant];
		}
		arreglo = aux;
		head = 0; tail = cant;
	}

	/**
	 * metodo que remueve el elemento en el frente de la cola, y lo devuelve
	 * O(1)
	 * 
	 * @return el elemento removido
	 * @throws EmptyQueueException si la cola está vacía
	 */
	@Override
	public T dequeue() throws EmptyQueueException {
		if(isEmpty()) throw new EmptyQueueException();
		T salida = arreglo[head];
		arreglo[head]= null;
		head = (head+1)%arreglo.length; 
		cant--;
		return salida;
	}
}
