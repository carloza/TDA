package TDAColaCP;

import java.util.Comparator;

/** 
 * Comparator based on the natural ordering
 */
public class MyComparador<E> implements Comparator<E> {
	
	/** 
	 * Compares two given elements
	 */
	@SuppressWarnings("unchecked")//asumo que los elementos son comparables
	public int compare(E a, E b) throws ClassCastException { 
		return ((Comparable<E>) a).compareTo(b);
	}
	
}
