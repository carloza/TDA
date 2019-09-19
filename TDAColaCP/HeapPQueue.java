package TDAColaCP;
import java.util.Comparator;

/**
 * Implementacion del TDA cola con prioridad. este modelo es similar a una cola simple, con la condicion
 * de que el ultimo en ingresar no es necesariamente el ultimo en salir, sino que su salida depende de
 * el orden de salida que determine la clave, este orden se calcula con la clave y establece la priorida
 * de salir antes o despues de los demas elementos almacenados. Para esta implementacion se ultiliza
 * un arbol heap que garantiza el tiempo de ejecucion de orden logaritmico O(log2(n)).
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 * @param <K> clave
 * @param <V> valor
 */
public class HeapPQueue<K,V> implements PriorityQueue<K,V> {

	private Entry<K,V>[] arbol;
	private int cant;
	private Comparator<K> compar;
	
	@SuppressWarnings("unchecked")//esto lo pongo por el contructor del arreglo generico
	public HeapPQueue(){
		arbol = new Entry[50];
		compar = null;
		cant = 0;
	}
	
	@SuppressWarnings("unchecked")//esto lo pongo por el contructor del arreglo generico
	public HeapPQueue(Comparator<K> c){
		arbol = new Entry[2000];
		compar = c;
		cant = 0;
	}

	/**
	 * Consulta la cantidad de elementos de la cola.
	 * O(1)
	 * 
	 * @return Cantidad de elementos de la cola.
	 */
	@Override
	public int size() {
		return cant;
	}

	/**
	 * Consulta si la cola está vacía.
	 * O(1)
	 * 
	 * @return Verdadero si la cola está vacía, falso en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		return (cant==0);
	}

	/**
	 * Devuelve la entrada con menor prioridad de la cola.
	 * O(1)
	 * 
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola está vacía.
	 */
	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(cant == 0){
			throw new EmptyPriorityQueueException("imposible obtener minimo de ColaCP vacia");
		}
		return arbol[1];
	}

	/**
	 * Inserta un par clave-valor y devuelve la entrada creada.
	 * O(log2(n))
	 * 
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * @return Entrada creada.
	 * @throws InvalidKeyException si la clave es inválida.
	 */
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> nueva = new Entrada<K,V>(key, value);
		if((cant+1)==arbol.length){
			duplicar();
		}
		arbol[cant+1] = nueva;
		cant++;
		upHeap(cant);
		return nueva;
	}

	/**
	 * Remueve y devuelve la entrada con menor prioridad de la cola.
	 * O(log2(n))
	 * 
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola está vacía.
	 */
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(cant == 0)
		{
			throw new EmptyPriorityQueueException("imposible remover minimo de ColaCP vacia");
		}
		Entry<K,V> salida = arbol[1];
		arbol[1] = arbol[cant];
		arbol[cant] = null;
		cant--;
		downHeap(1);
		return salida;
	}
	
	private void upHeap(int c) {
		if(c!=1)
		{
			if(compar.compare(arbol[c/2].getKey(),arbol[c].getKey())>0){
			//if(compar.compare(arbol[c].getKey(),arbol[c/2].getKey())>0){
				Entry<K,V> aux= arbol[c];
				arbol[c]=arbol[c/2];
				arbol[c/2]= aux;
				upHeap(c/2);
			}
		}
	}
	
	private void downHeap(int c){
		int izq = c*2;
		int der = c*2+1;
		if(izq<=cant){//pregunto si tengo hijo izq
			if(der<=cant){//pregunto si tengo hijo derecho
				if(compar.compare(arbol[izq].getKey(), arbol[der].getKey())>0){
					//aca el de la derecha es el menor de los dos
					if(compar.compare(arbol[c].getKey(), arbol[der].getKey())>0){
						intercambiar(c,der);
						downHeap(der);
					}
				}else{//son iguales o el de la izq es el mayor de los dos
					if(compar.compare(arbol[c].getKey(), arbol[izq].getKey())>0){
						intercambiar(c,izq);
						downHeap(izq);
					}
				}
			}else{//si no tengo hijo derecho, osea, solo izq
				if(compar.compare(arbol[c].getKey(), arbol[izq].getKey())>0){
					intercambiar(c,izq);
					downHeap(izq);
				}
			}
		}//si no tengo hijo izq, no tengo hijos, porque el arbol es completo
	}

	private void intercambiar(int c, int pos) {
		Entry<K,V> aux = arbol[c];
		arbol[c]= arbol[pos];
		arbol[pos]= aux;
		
	}

	private void checkKey(K key) throws InvalidKeyException {
		try{
			compar.compare(key, key);
		}
		catch(Exception e){
			throw new InvalidKeyException("key ingresada invalida");
		}
	}
	
	@SuppressWarnings("unchecked")//este lo pongo por el constructor del arreglo
	private void duplicar() {
		Entry<K,V>[] aux = new Entry[arbol.length*2];
		for(int i=0;i<arbol.length;i++){
			aux[i] = arbol[i];
		}
		
	}


}
