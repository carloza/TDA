package TDALista;

public class DLNodo<E> implements Position<E> {
	
	//ATRIBUTOS
	
	private DLNodo<E> siguiente;
	private DLNodo<E> anterior;
	private E elemento;
	
	
	/**
	 * contructor clase DLNodo (nodo para lista de coble enlace) crea un nodo con seguite anterior y
	 * elemento nulo
	 * O(1)
	 */
	public DLNodo()
	{
		siguiente = null;
		anterior = null;
		elemento = null;
	}

	/**
	 * contructor clase DLNodo (nodo para lista de coble enlace) crea un nodo con seguite y anterior
	 * nulo, pero permite definir el elemento que almacena
	 *  
	 * @param e elemento que se va a almacenar
	 */
	public DLNodo(E e)
	{
		siguiente = null;
		anterior = null;
		elemento = e;
	}

	/**
	 * metodo que setear el siguiente
	 * @param n nodo que sera el siguiente
	 */
	public void setearSiguiente(DLNodo<E> n)
	{
		siguiente = n;
	}

	/**
	 * metodo que setear el anterior
	 * @param n nodo que sera el anterior
	 */
	public void setearAnterior(DLNodo<E> n)
	{
		anterior = n;
	}

	/**
	 * metodo que setea el metodo almacenado, si si existia uno este se pierde
	 * @param e nuevo elemento a almacenar
	 */
	public void setearElemento(E e)
	{
		elemento = e;
	}

	/**
	 * consulta que obtiene el nodo siguiente
	 * @return el nodo siguiente
	 */
	public DLNodo<E> getearSiguiente()
	{
		return siguiente;
	}

	/**
	 * consulta que obtiene el nodo anterior
	 * @return el nodo anterior
	 */
	public DLNodo<E> getearAnterior()
	{
		return anterior;
	}

	@Override
	public E element() {
		return elemento;
	}

}
