package TDAArbol;

import TDALista.*;

public class TNodo<E> implements Position<E> {
	
	//ATRIBUTOS
	private TNodo<E> padre;
	private E elemento;
	private PositionList<TNodo<E>> hijos;
	
	//CONSTRUCTOR
	/**
	 * contructor clase TNodo (nodo para Arbol enlazado) crea un nodo con padre y elemento nulo y
	 * una lista de hijos vacia
	 * O(1)
	 */
	public TNodo(){
		padre= null;
		elemento = null;
		hijos = new DoubleLinkedList<TNodo<E>>();
	}
	
	/**
	 * contructor clase TNodo (nodo para Arbol enlazado) crea un nodo con padre nulo y
	 * una lista de hijos vacia, pero permite definir el elemento que almacena
	 * O(1)
	 */
	public TNodo(E e){
		padre= null;
		elemento = e;
		hijos = new DoubleLinkedList<TNodo<E>>();
	}

	//METODOS
	@Override
	public E element() {
		return elemento;
	}
	
	/**
	 * metodo que cambia el elemento alacenado y devuelve el anterior
	 * O(1)
	 * 
	 * @param e nuevo elemento
	 * @return el elemento anterior
	 */
	public E setearElemento(E e){
		E salida = elemento;
		elemento = e;
		return salida;
	}
	
	/**
	 * devuelve el nodo padre de este nodo, puede devolver null si no existe
	 * O(1)
	 * 
	 * @return nodo padre
	 */
	public TNodo<E> getearPadre(){
		return padre;
		
	}
	
	/**
	 * metodo que setea un nuevo padre, se pierde el anterior
	 * O(1)
	 * 
	 * @param n nodo que sera el nuevo padre
	 */
	public void setearPadre(TNodo<E> n){
		padre = n;
	}
	
	/**
	 * devuelve una coleccion de los hijos
	 * O(1)
	 * 
	 * @return una PositionList con los hijos dentro
	 */
	public PositionList<TNodo<E>> getearHijos(){
		return hijos;
	}

}
