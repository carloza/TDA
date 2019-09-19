package TDAArbol;

import java.util.Iterator;
import TDALista.*;

/**
 * Implementacion para un estructura de dato que representa un arbol, en particular  (para un mejor 
 * entendimiento) este arbol debe pensarse al revez de un arbol de la naturaleza, y mirarse mas como
 * un arbol genealogico; en un arbol de este tipo ubicamos la raiz en la cima y sus hijo por debajo,
 * cada hijo de la raiz puede tener sus hijos, los cuales se van a ubicar debajo de este, naciendo
 * asi la nocion de sub arbol , donde la raiz de este sub arbol es el hijo de la raiz y los hijos de
 * este se van a encontrar debajo, esto puede repetirce nuevamente con los "nietos" de la raiz y asi
 * sucesibamente para los demas descendientes; cuando hablamos de raiz y de hijos, estamos hablando
 * de nodos, en particular, de nodos del arbol los cuales se conectan entre si a travez de arcos
 * dirijidos, los cuales permiten organizar la estructura y determinan la gerarquia entre nodos. 
 * En definiciones: canda nodo tiene un unico padre y un conjunto de hijos; el arbol posee una unica
 * raiz; y si el nodo no tiene hijos estamo hablando de un nodo hoja, es decir, un nodo externo,
 * caso contrario el nodo posee al menos un hijo, es decir, es un nodo interno.
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 * @param <E> el parametro E es el tipo (generico) de elementos que el arbol va a manejar
 */
public class LinkedTree<E> implements Tree<E> {
	
	//ATRIBUTOS
	private TNodo<E> raiz;
	private int cantidad;

	//CONSTRUCTOR
	
	/**
	 * Contructor de la clases "LinkedTree", no recive parametros, y crea un arbol vacio
	 * O(1)
	 */
	public LinkedTree(){
		raiz = null;
		cantidad = 0;
	}
	
	/**
	 * Contructor de la clases "LinkedTree", este es un construtor espesifico que permite definir la
	 * raiz del arbol
	 * O(1)
	 * 
	 * @param e raiz del nuevo arbol
	 */
	public LinkedTree(E e){
		raiz = new TNodo<E>(e);
		cantidad = 1;
	}
	
	//METODOS
	
	
	/**
	 * Consulta la cantidad de nodos en el �rbol.
	 * O(1)
	 * 
	 * @return Cantidad de nodos en el �rbol.
	 */
	@Override
	public int size(){
		return cantidad;
	}

	/**
	 * Consulta si el �rbol est� vac�o.
	 * O(1)
	 * 
	 * @return Verdadero si el �rbol est� vac�o, falso en caso contrario.
	 */
	@Override
	public boolean isEmpty(){
		return (cantidad==0);
	}

	/**
	 * Devuelve un iterador de los elementos almacenados en el �rbol en preorden.
	 * O(n)
	 * 
	 * @return Iterador de los elementos almacenados en el �rbol.
	 */
	@Override
	public Iterator<E> iterator() {
		PositionList<E> lista = new DoubleLinkedList<E>();
		if(cantidad!=0){
			PreOrdenRotulos(lista,raiz);
		}
		return lista.iterator();
	}

	private void PreOrdenRotulos(PositionList<E> lista, TNodo<E> r) {
		lista.addLast(r.element());
		if(!r.getearHijos().isEmpty()){
			for(TNodo<E> n : r.getearHijos()){
				PreOrdenRotulos(lista, n);
			}
		}
	}

	/**
	 * Devuelve una colecci�n iterable de las posiciones de los nodos del �rbol.
	 * O(n)
	 * 
	 * @return Colecci�n iterable de las posiciones de los nodos del �rbol.
	 */
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new DoubleLinkedList<Position<E>>();
		if(cantidad!=0){
			PreOrdenPosiciones(lista,raiz);
		}
		return lista;
	}

	private void PreOrdenPosiciones(PositionList<Position<E>> lista,TNodo<E> r) {
		lista.addLast(r);
		if(!r.getearHijos().isEmpty()){
			for(TNodo<E> n : r.getearHijos()){
				PreOrdenPosiciones(lista, n);
			}
		}
	}

	/**
	 * Reemplaza el elemento almacenado en la posici�n dada por el elemento pasado por par�metro. Devuelve el elemento reemplazado.
	 * O(1)
	 * 
	 * @param v Posici�n de un nodo.
	 * @param e Elemento a reemplazar en la posici�n pasada por par�metro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> ve = checkPosition(v);
		return ve.setearElemento(e);
	}

	/**
	 * Devuelve la posici�n de la ra�z del �rbol.
	 * O(1)
	 * @return Posici�n de la ra�z del �rbol.
	 * @throws EmptyTreeException si el �rbol est� vac�o.
	 */
	@Override
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
		{
			throw new EmptyTreeException("no se puede obtener la raiz de un �rbol vacio");
		}
		return raiz;
	}

	/**
	 * Devuelve la posici�n del nodo padre del nodo correspondiente a una posici�n dada.
	 * O(1)
	 * 
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del nodo padre del nodo correspondiente a la posici�n dada.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde a la ra�z del �rbol.
	 */
	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException,BoundaryViolationException {
		TNodo<E> ve = checkPosition(v);
		if(v==raiz){
			throw new BoundaryViolationException("no se puede obtener el padre de la raiz");
		}
		return ve.getearPadre();
	}

	/**
	 * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n dada.
	 * O(s) S es la cantidad de hijos, en el peor caso es n-1 es decir O(n)
	 * 
	 * @param v Posici�n de un nodo.
	 * @return Colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		PositionList<TNodo<E>> listDeNodos = checkPosition(v).getearHijos();
		PositionList<Position<E>> listDePosition = new DoubleLinkedList<Position<E>>();
		
		for(Position<E> p : listDeNodos){
			listDePosition.addLast(p);
		}
		return listDePosition;
	}

	/**
	 * Consulta si una posici�n corresponde a un nodo interno.
	 * O(1)
	 * 
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nod = checkPosition(v);
		return (!nod.getearHijos().isEmpty());
	}

	/**
	 * Consulta si una posici�n dada corresponde a un nodo externo.
	 * O(1)
	 * 
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		//si no es interno no queda otra de que sea externo :no me digas:
		return (!isInternal(v));
	}

	/**
	 * Consulta si una posici�n dada corresponde a la ra�z del �rbol.
	 * O(1)
	 * 
	 * @param v Posici�n de un nodo.
	 * @return Verdadero, si la posici�n pasada por par�metro corresponde a la ra�z del �rbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		
		return (checkPosition(v)==raiz);
	}

	/**
	 * Crea un nodo con r�tulo e como ra�z del �rbol.
	 * O(1)
	 * 
	 * @param E R�tulo que se asignar� a la ra�z del �rbol.
	 * @throws InvalidOperationException si el �rbol ya tiene un nodo ra�z.
	 */
	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if (!isEmpty())
		{
			throw new InvalidOperationException("No se puede crear raiz, ya exite una");
		}
		raiz = new TNodo<E>(e);
		cantidad=1;
				
	}

	/**
	 * Agrega un nodo con r�tulo e como primer hijo de un nodo dado.
	 * O(1) si la lista de hijos implementa operaciones de O(1)
	 * 
	 * @param e R�tulo del nuevo nodo.
	 * @param padre Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
	 */
	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nod = checkPosition(p);
		PositionList<TNodo<E>> list = nod.getearHijos();
		TNodo<E> pos = new TNodo<E>(e);
		list.addFirst(pos);
		pos.setearPadre(nod);
		cantidad++;
		return pos;
	}

	/**
	 * Agrega un nodo con r�tulo e como �timo hijo de un nodo dado.
	 * O(1) si la lista de hijos implementa operaciones de O(1)
	 * 
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
	 */
	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nod = checkPosition(p);
		PositionList<TNodo<E>> list = nod.getearHijos();
		TNodo<E> pos = new TNodo<E>(e);
		list.addLast(pos);
		pos.setearPadre(nod);
		cantidad++;
		return pos;
	}

	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre dado. El nuevo nodo se agregar� delante de otro nodo tambi�n dado.
	 * O(s) si la lista de hijos implementa operaciones de O(1), s es la cantidad de hijos, en el peor caso N-1, es decir O(n)
	 * 
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @param rb Posici�n del nodo que ser� el hermano derecho del nuevo nodo.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida, o el �rbol est� vac�o, o la posici�n rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> nod = checkPosition(p);
		PositionList<TNodo<E>> list = nod.getearHijos();
		Position<TNodo<E>> pos, ulti;pos = ulti = null;
		
		if (list.isEmpty()){
			throw new InvalidPositionException("el segundo argumento no es hijo del primero, el primer arguento es hoja");
		}else{
			try {
				pos = list.first();
				ulti = list.last();
			} catch (EmptyListException e1) {
				// esto nunca va a pasar, ya que testeo previamente si la lista esta vacia
			}
		}
		
		//busco la posicion de rb en p
		while(pos!=ulti&&pos.element()!=rb){
			try {
				pos=list.next(pos);
			} catch (BoundaryViolationException e1) {
				System.out.println("se supone que esto nunca va a pasar");
			}
		}
		if(pos.element()!=rb&&pos==ulti){
			throw new InvalidPositionException ("las posiciones no son padre e hijo entre ellas");
		}
		TNodo<E> nuevo = new TNodo<E>(e);
		list.addBefore(pos, nuevo);
		nuevo.setearPadre(nod);
		cantidad++;
		return nuevo;
	}

	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre dado. El nuevo nodo se agregar� a continuaci�n de otro nodo tambi�n dado.
	 * O(s) si la lista de hijos implementa operaciones de O(1), s es la cantidad de hijos, en el peor caso N-1, es decir O(n)
	 * 
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @param lb Posici�n del nodo que ser� el hermano izquierdo del nuevo nodo.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida, o el �rbol est� vac�o, o la posici�n lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> nod = checkPosition(p);
		PositionList<TNodo<E>> list = nod.getearHijos();
		Position<TNodo<E>> pos, ulti;pos = ulti = null;
		
		if (list.isEmpty()){
			throw new InvalidPositionException("el segundo argumento no es hijo del primero, el primer arguento es hoja");
		}else{
			try {
				pos = list.first();
				ulti = list.last();
			} catch (EmptyListException e1) {
				// esto nunca va a pasar, ya que testeo previamente si la lista esta vacia
			}
		}
		
		//busco la posicion de rb en p
		while(pos!=ulti&&pos.element()!=lb){
			try {
				pos=list.next(pos);
			} catch (BoundaryViolationException e1) {
				System.out.println("se supone que esto nunca va a pasar");
			}
		}
		if(pos.element()!=lb&&pos==ulti){
			throw new InvalidPositionException ("las posiciones no son padre e hijo entre ellas");
		}
		TNodo<E> nuevo = new TNodo<E>(e);
		list.addAfter(pos, nuevo);
		nuevo.setearPadre(nod);
		cantidad++;
		return nuevo;
	}

	/**
	 * Elimina el nodo referenciado por una posici�n dada, si se trata de un nodo externo.
	 * O(h) h es la cantidad de hermanos, O(n) en el peor caso 
	 * @param n Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o no corresponde a un nodo externo, o el �rbol est� vac�o.
	 */
	@Override
	public void removeExternalNode(Position<E> p)throws InvalidPositionException {
		TNodo<E> pe = checkPosition(p);
		if(isInternal(pe)){
			throw new InvalidPositionException("no se puede eliminar una posicion interna con removeExternalNode()");
		}
		if(pe==raiz){
			raiz= null;
			cantidad = 0;
			pe.setearPadre(null);
			pe.setearElemento(null);
		}else{
		PositionList<TNodo<E>> listaHermanos = pe.getearPadre().getearHijos();
		
		//aca lo busco en la lista de hermanos
		Position<TNodo<E>> posDePe = null, ultiHerm = null;
		try {
			posDePe = listaHermanos.first();
			ultiHerm = listaHermanos.last();
		} catch (EmptyListException e) {
			// esto nunca va a pasar porque al menos en la lista esta el propio nodo
		}
		while(posDePe!=ultiHerm&& posDePe.element()!=pe){
			try {
				posDePe = listaHermanos.next(posDePe);
			} catch (BoundaryViolationException e1) {
				System.out.println("se supone que esto nunca va a pasar");
			}
			//salgo de aca porque lo encontre o porque es el ultimo
		}
		
		//una vez que lo tengo lo elimino
		listaHermanos.remove(posDePe);
		//una vez que lo elimine de la lista de hermanos, lo rompo todo
		pe.setearPadre(null);
		pe.setearElemento(null);
		cantidad--;
		}
	}

	/**
	 * Elimina el nodo referenciado por una posici�n dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen.
	 * O(s+h) s cantidad de hijos y ha cantidad de hermanos, O(n) en el peor caso
	 * 
	 * Si el nodo a eliminar es la ra�z del �rbol,  �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param n Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o no corresponde a un nodo interno o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
	 */
	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> pe = checkPosition(p);
		if(isExternal(pe)){
			throw new InvalidPositionException("no se puede eliminar una posicion externa con removeInternalNode()");
		}
		if(pe==raiz){
			if(pe.getearHijos().size()==1){
				try {
					raiz= pe.getearHijos().first().element();
				} catch (EmptyListException e) {
					// esto nunca va a pasar porque pregunte previamente que la raiz tubiera al menos 1 hijo
				}
				pe.setearElemento(null);
				pe.setearPadre(null);// en vano pero por si las dudas
				cantidad--;
			}else{
				throw new InvalidPositionException("no se puede eleminar la raiz con mas de un hijo");
			}
		}else{
		
		TNodo<E> padre = pe.getearPadre();
		PositionList<TNodo<E>> hermanos = padre.getearHijos();
		
		Position<TNodo<E>> posDePe = null, ultiHerm = null;
		try {
			posDePe = hermanos.first();
			ultiHerm = hermanos.last();
		} catch (EmptyListException e) {
			// esto nunca va a pasar porque al menos en la lista esta el propio nodo
		}
		while(posDePe!=ultiHerm&& posDePe.element()!=pe){
			try {
				posDePe = hermanos.next(posDePe);
			} catch (BoundaryViolationException e1) {
				System.out.println("se supone que esto nunca va a pasar");
			}
			//salgo de aca porque lo encontre o porque es el ultimo
		}
		
		PositionList<TNodo<E>> hijos = pe.getearHijos();
		for(TNodo<E> h : hijos){
			hermanos.addBefore(posDePe,h);
			h.setearPadre(padre);
		}
		
		hermanos.remove(posDePe);
		pe.setearElemento(null);
		pe.setearPadre(null);
		cantidad--;
		}
	}

	/**
	 * Elimina el nodo referenciado por una posici�n dada. Si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen.
	 * O(s+h) s cantidad de hijos y ha cantidad de hermanos, O(n) en el peor caso
	 *  
	 * Si el nodo a eliminar es la ra�z del �rbol,  �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param n Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
	 */
	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if(cantidad==0){
			throw new InvalidPositionException("no hay elementos por eliminar, arbol vacio");
		}
		if(isExternal(p)){
			removeExternalNode(p);
		}else{
			removeInternalNode(p);
		}
	}
	
	private TNodo<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if(cantidad==0){
			throw new InvalidPositionException("arbol vacio");
		}
		if(v == null){
			throw new InvalidPositionException("posicion nula");
		}
		if(v.element() == null){
			throw new InvalidPositionException("posicion de arbol vacia");
		}
		TNodo<E> salida = null;
		try{
			salida = (TNodo<E>) v;
		}catch (Exception e){
			throw new InvalidPositionException("posicion invalida, no es posicion de arbol");
		}
		if (salida!=raiz&&salida.getearPadre()==null){
			throw new InvalidPositionException("la posicion ingresada no pertenece al arbol");
		}
		return salida;
	}

}
