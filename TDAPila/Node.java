package TDAPila;

public class Node<E> {

	/**
	 * Atributos de instancia
	 */
	private E elemento;
	private Node<E> siguiente;
	
	/**
	 * Node
	 * Constructor de la clase Node.
	 * 
	 * @param e es el elemento almacenado en el nodo.
	 */
	public Node (E e)
	{
		elemento = e;
		siguiente = null;
	}
	
	/**
	 * Consultas y Comandos
	 */
	
	/**
	 * getElemento
	 * Retorna el elemento almacenado en el nodo.
	 * 
	 * @return el elemento almacenado en el nodo.
	 */
	public E getElemento()
	{
		return elemento;
	}
	
	/**
	 * getSiguiente
	 * Retorna el nodo siguiente.
	 * 
	 * @return el nodo próximo al actual.
	 */
	public Node<E> getSiguiente()
	{
		return siguiente;
	}
	
	/**
	 * setElemento
	 * Setea el elemento almacenado en el nodo por uno ingresado.
	 * 
	 * @param e es el elemento nuevo del nodo.
	 * @return el elemento seteado.
	 */
	public E setElemento(E e)
	{
		E old = elemento;
		elemento = e;
		return old;
	}
	
	/**
	 * setSiguiente
	 * Setea el nodo siguiente al actual.
	 * 
	 * @param n es el nodo siguente al actual.
	 */
	public void setSiguiente(Node<E> n)
	{
		siguiente = n;
	}
	
}
