package Programa;

import java.util.Comparator;

/**
 * Este comparador coloca como menor al elemento mayor (invierte el orden)
 */
public class ComparadorInverso implements Comparator<Integer> {
	
	/** 
	 * Retorna un entero menor que cero si el primer elemento es mayor.
	 * 
	 * @return -1 si a>b, 0 si a=b y 1 si a<b.
	 */
	public int compare(Integer a, Integer b) throws ClassCastException { 
		return (-1)*(a.compareTo(b));
	}
	
}