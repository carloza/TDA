package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElementoIterador<E> implements Iterator<E> {
	
	//ATRUBUTOS
	private PositionList<E> L;
	private Position<E> cursor;
	
	/**
	 * Constructor clase Elemento iterador (para manejar esta clase use interface Iterator)
	 * O(1)
	 * 
	 * @param list es la lista sobre la cual el elemento va a iterar
	 */
	public ElementoIterador(PositionList<E> list)
	{
		L=list;
		try {
			cursor = L.isEmpty() ? null : list.first();
		} catch (EmptyListException e) {
			// esto nunca va a pasar, porque testeo previamente que no este vacia
		}
	}

	@Override
	public boolean hasNext() {
		return (cursor!=null);
	}

	public E next() throws NoSuchElementException{
		if (L.isEmpty()) throw new NoSuchElementException();
		E salida = cursor.element();
		try {
			if(cursor.equals(L.last())){
				cursor= null;
			}
			else{
				try {
					cursor = L.next(cursor);
				} catch (Exception e) {
					throw new NoSuchElementException("no hay mas eleentos por iterar");
				}
				
			}
		} catch (EmptyListException e) {
			// esto nunca va a suceder porque si la lista esta vacia lanzo excepcion
		}
		return salida;
	}
	

	@Override
	public void remove() {
		//
	}
	

}
