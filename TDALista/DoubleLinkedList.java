package TDALista;

import java.util.Iterator;

/**
 * Implementacion de TDALista. el modelo es una secuencia de elementos, en la cual se mantiene el 
 * orden que el usuario dá al ingresar dichos elementos, se ofrecen metodos para insertar al inicio,
 * al final, despues o antes de tal posicion, asi mismo como la consulta de ellos, tambien ofrece
 * iterativos para recorrer la lista, este diseño usa un una estructu doblemente enlazada, y fue
 * pensada y programada de tal forma que todas los metodos y consultas tengan tiempo de ejecucion
 * constante O(1), exceptuando la consulta "positions()" que posee tiempo de orde n O(n)
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 * @param <E> el parametro E es el tipo (generico) de elementos que la lista va a manejar
 */
public class DoubleLinkedList<E> implements PositionList<E> {
	
	//ATRIBUTOS
	
	private int tamaño;
	private DLNodo<E> primero;
	private DLNodo<E> ultimo;
	
	
	//CONSTRUCTOR
	
	/**
	 * constructor de la lista, crea una lista vacia, es decir, de tamaño cero
	 * O(1)
	 */
	public DoubleLinkedList()
	{
		tamaño = 0;
		primero = null;
		ultimo = null;
	}

	/**
	 * metodo que calcula el tamaño de la lista (o si se quere, retorna la cantidad de elementos
	 * almacenados)
	 * O(1)
	 * 
	 * @return tamaño de la lista, cero si esta vacia
	 */
	@Override
	public int size() 
	{
		return tamaño;
	}

	/**
	 * Consulta si la lista está vacía.
	 * O(1)
	 * 
	 * @return true si esta vacia, false caso contrario
	 */
	@Override
	public boolean isEmpty() 
	{
		return (tamaño==0);
	}

	/**
	 * Devuelve la posición del primer elemento de la lista. Si la lista está vacía devuelve nulo.
	 * O(1)
	 * 
	 * @return Posición del primer elemento de la lista.
	 */
	@Override
	public Position<E> first() throws EmptyListException
	{
		if(isEmpty()) throw new EmptyListException();
		return primero;
	}

	/**
	 * Devuelve la posición del último elemento de la lista. Si la lista está vacía devuelve nulo.
	 * O(1)
	 * 
	 * @return Posición del último elemento de la lista.
	 */
	@Override
	public Position<E> last() throws EmptyListException
	{
		if(isEmpty()) throw new EmptyListException();
		return ultimo;
	}

	/**
	 * Devuelve la posición del elemento siguiente a la posición pasada por parámetro.
	 * O(1)
	 * 
	 * @param p Posición a obtener su elemento siguiente.
	 * @return Posición del elemento siguiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si el posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al último elemento de la lista.
	 */
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException,BoundaryViolationException 
	{
		DLNodo<E> n = checkPosition(p);
		if(n.equals(ultimo))
		{
			throw new BoundaryViolationException("no existe siguiente del ultimo");
		}
		return n.getearSiguiente();
	}

	/**
	 * Devuelve la posición del elemento anterior a la posición pasada por parámetro.
	 * O(1)
	 * 
	 * @param p Posición a obtener su elemento anterior.
	 * @return Posición del elemento anterior a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al primer elemento de la lista.
	 */
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException,BoundaryViolationException 
	{
		DLNodo<E> n = checkPosition(p);
		if(n==primero)
		{
			throw new BoundaryViolationException("no existe anterior al primero");
		}
		return n.getearAnterior();
	}

	/**
	 * Inserta un elemento al principio de la lista.
	 * O(1)
	 * 
	 * @param element Elemento a insertar al principio de la lista.
	 */
	@Override
	public void addFirst(E element) 
	{
		DLNodo<E> aux = new DLNodo<E>(element);
		if(tamaño==0)
		{
			primero=aux;
			ultimo = aux;
			tamaño = 1;
		}
		else
		{
			aux.setearSiguiente(primero);
			primero.setearAnterior(aux);
			primero=aux;
			tamaño++;
		}
	}

	/**
	 * Inserta un elemento al final de la lista.
	 * O(1)
	 * 
	 * @param element Elemento a insertar al final de la lista.
	 */
	@Override
	public void addLast(E element) 
	{
		DLNodo<E> aux = new DLNodo<E>(element);
		if(tamaño==0)
		{
			primero= aux;
			ultimo = aux;
			tamaño = 1;
		}
		else
		{
			aux.setearAnterior(ultimo);
			ultimo.setearSiguiente(aux);
			ultimo=aux;
			tamaño++;
		}
	}

	/**
	 * Inserta un elemento luego de la posición pasada por parámatro.
	 * O(1)
	 * 
	 * @param p Posición en cuya posición siguiente se insertará el elemento pasado por parámetro.
	 * @param element Elemento a insertar luego de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException 
	{
		DLNodo<E> pe = checkPosition(p);
		if(pe==ultimo)
		{
			this.addLast(element);
			//aca no incremento tamaño ya que lo hace "addLast()"
		}
		else
		{
			//obtenemos los que van a ser sig y ant al nuevo
			DLNodo<E> aux = new DLNodo<E>(element);
			DLNodo<E> sig = pe.getearSiguiente();
			//refenciamos a aux
			pe.setearSiguiente(aux);
			sig.setearAnterior(aux);
			//hacemos referenciar ant y sig de aux
			aux.setearAnterior(pe);
			aux.setearSiguiente(sig);
			//incrementamos tamaño
			tamaño++;
		}
	}

	/**
	 * Inserta un elemento antes de la posición pasada como parámetro.
	 * O(1)
	 * 
	 * @param p Posición en cuya posición anterior se insertará el elemento pasado por parámetro. 
	 * @param element Elemento a insertar antes de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException 
	{
		DLNodo<E> pe = checkPosition(p);
		if(p==primero)
		{
			this.addFirst(element);
			//aca no incremento tamaño ya que lo hace "addFirst()"
		}
		else
		{
			//obtenemos los que van a ser sig y ant al nuevo
			DLNodo<E> aux = new DLNodo<E>(element);
			DLNodo<E> ant = pe.getearAnterior();
			//refenciamos a aux
			pe.setearAnterior(aux);
			ant.setearSiguiente(aux);
			//hacemos referenciar ant y sig de aux
			aux.setearAnterior(ant);
			aux.setearSiguiente(pe);
			//incrementamos tamaño
			tamaño++;
		}
	}

	/**
	 * Remueve el elemento que se encuentra en la posición pasada por parámetro.
	 * O(1)
	 * 
	 * @param p Posición del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DLNodo<E> pe = checkPosition(p);
		E salida = pe.element();
		if(tamaño==1)
		{
			primero = null;
			ultimo = null;
		}
		else
		{
			if((pe!=ultimo)&&(pe!=primero))
			{
				//obtenemos anterior y siguiente
				DLNodo<E> sig = pe.getearSiguiente();
				DLNodo<E> ant = pe.getearAnterior();
				//unimos anterior con siguiente y viseversa
				sig.setearAnterior(ant);
				ant.setearSiguiente(sig);
				//quitamos la referencias de pe
				pe.setearAnterior(null);
				pe.setearSiguiente(null);
			}else{
			if(pe==primero)// if (pe.equals(primero))
			{
				primero = pe.getearSiguiente();
				primero.setearAnterior(null);
				pe.setearSiguiente(null);
			}else{
			if(pe==ultimo)
			{
				ultimo = pe.getearAnterior();
				ultimo.setearSiguiente(null);
				pe.setearAnterior(null);
			}}}
			
		}
		//le quitamos el elemento a pe, para que no se pueda usar mas
		pe.setearElemento(null);
		tamaño--;
		return salida;
	}

	/**
	 * Establece el elemento en la posición pasados por parámetro. Reemplaza el elemento que se encontraba anteriormente en esa posición y devuelve el elemento anterior.
	 * O(1)
	 * 
	 * @param p Posición a establecer el elemento pasado por parámetro.
	 * @param element Elemento a establecer en la posición pasada por parámetro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.	 
	 */
	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DLNodo<E> pe = checkPosition(p);
		E salida= p.element();
		pe.setearElemento(element);
		return salida;
	}

	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * O(1)
	 * 
	 * @return Un iterador de todos los elementos de la lista.
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementoIterador<E>(this);
	}

	/**
	 * Devuelve una colección iterable de posiciones.
	 * O(n)
	 * @return Una colección iterable de posiciones.
	 */
	@Override
	public Iterable<Position<E>> positions() {
		
		PositionList<Position<E>> P = new DoubleLinkedList<Position<E>>();
		if(!isEmpty())
		{
			Position<E> pos = primero;
			while(pos!=ultimo)
			{
				P.addLast(pos);
				try {
					pos=next(pos);
				} catch (Exception e) {
					// esto nunca va a pasar
					System.out.println("se supone que no tenia que pasar");
				}
			}
			//agrego el ultimo que no fue agregado en el bucle
			P.addLast(pos);
		}
		return P;
	}

	/**
	 * metodo privado que verifica el buen estado de la posiciones ingresadas, y la castea a nodo
	 * O(1)
	 * 
	 * @param p posicion a verificar
	 * @return posicion casteada a tipo nodo
	 * @throws InvalidPositionException si la posicion presenta agun defecto
	 */
	private DLNodo<E> checkPosition(Position<E> p) throws InvalidPositionException
	{
		DLNodo<E> salida;
		
		if(p==null||p.element()==null||tamaño==0)
		{
			throw new InvalidPositionException("posicion o elemento nulo");
		}
		
		try{
			salida = (DLNodo<E>)p;
		}catch(Exception e){
			throw new InvalidPositionException("esta posicion no es de esta lista");
		}
		return salida;
	}

}
