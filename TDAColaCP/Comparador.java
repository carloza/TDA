package TDAColaCP;

public class Comparador<E> implements java.util.Comparator<E> {

	@SuppressWarnings("unchecked")//asume que los elementos son comparables
	@Override
	public int compare(E o1, E o2) {
		
		return ((Comparable<E>) o1).compareTo(o2);
	}
	
	

}
