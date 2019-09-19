package TDAPila;

/**
 * Implementacion del TDA Pila, su funcionamiento es el tradicional, es decir, el primer elemento
 * en ingresar es el ultimo en salir, o si se quiere, el ultimo en ingresar es el primero en salir.
 * Internamente este TDA usa una estrctura simplemente enlazada, lo cual permite que que el uso de
 * memoria sea dinamico y el tiempo de ejecucion sea consntante en todas la operaciones
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 * @param <E> el parametro E es el tipo (generico) de elementos que la lista va a manejar
 */
public class LinkedStack<E> implements Stack<E>{

	/**
	 * Atributos
	 */
	private Node<E> cabeza;
	private Node<E> tope;
	private int cant;
	
	/**
	 * PilaEnlazada
	 * Constructor de la clase PilaEnlazada
	 */
	public LinkedStack()
	{
		cabeza = null;
		tope = null;
		cant = 0;
	}
	
	/*
	 * Comando y consultas
	 */
	
	/**
	 * size
	 * Consulta la cantidad de elementos de la pila.
	 * 
	 * @return Cantidad de elementos de la pila.
	 */
	public int size() {
		
		return cant;
	}

	/**
	 * isEmpty
	 * Consulta si la pila está vacía.
	 * 
	 * @return Verdadero si la pila está vacía, falso en caso contrario.
	 */
	public boolean isEmpty() {
		
		return (cant==0);
	}

	/**
	 * top
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * 
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila está vacía. 
	 */
	public E top() throws EmptyStackException {
		
		if (isEmpty()) { throw new EmptyStackException("No hay tope. Pila vacia."); }
		return tope.getElemento();
	}

	/**
	 * push
	 * Inserta un elemento en el tope de la pila.
	 * 
	 * @param element Elemento a insertar.
	 */
	public void push(E element) {
		
		Node<E> nuevo = new Node<E>(element);
		if (isEmpty())
		{
			cabeza = nuevo;
		}
		else
		{
			tope.setSiguiente(nuevo);
		}
		tope = nuevo;
		cant++;
	}

	/**
	 * pop
	 * Remueve el elemento que se encuentra en el tope de la pila.
	 * 
	 * @return Elemento removido.
	 * @throws EmptyStackException si la pila está vacía. 
	 */
	public E pop() throws EmptyStackException {
		
		if (isEmpty()) { throw new EmptyStackException("No se puede borrar. Pila vacia."); }
		E salida = null;
		if (tope==cabeza)
		{
			tope = null;
			salida = cabeza.setElemento(null);
			cabeza = null;
		}
		else
		{
			Node<E> cursor = cabeza;
			boolean cortar = false;
			while (!cortar)
			{
				if (cursor.getSiguiente()==tope) { cortar = true; }
				else { cursor = cursor.getSiguiente(); }
			}
			salida = tope.setElemento(null);
			cursor.setSiguiente(null);
			tope = cursor;
		}
		cant--;
		return salida;
	}

}
