package TDAMapeo;

import TDAColaCP.*;
import TDALista.*;

/**
 * implementación de un TDAMapeo, este modelo permite enlazar claves a valores, estas claves no
 * pueden repetirse, es decir, que con una clave puedo dirigirme a un solo valor, 
 * el enlace de claves a valores esta dado por el par (Key,Value) donde "key"
 * es la clave para un determinado valor "value". Para este modelo se utiliza como
 * estructura una tabla hasha abierta, donde cada lugar de la tabla almacena el valor de cada clave
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 * @param <K> clave
 * @param <V> valor
 */
public class OpenHMap<K,V> implements Map<K,V> {
	
	private PositionList<Entrada<K,V>>[] tabla;
	private int cant;

	/**
	 * contructor de OpenHMap, crea un mapeo vacio, no recibe parametros
	 * O(1);
	 */
	@SuppressWarnings("unchecked")//este lo pongo por el constructor del arreglo
	public OpenHMap(){
		cant = 0;
		tabla = new PositionList[123];
		for(int i=0; i<tabla.length; i++){
			tabla[i]= new DoubleLinkedList<Entrada<K,V>>();
		}
	}

	/**
	 * Consulta el número de entradas del mapeo.
	 * 0(1)
	 * 
	 * @return Número de entradas del mapeo.
	 */
	@Override
	public int size() {
		return cant;
	}

	/**
	 * Consulta si el mapeo está vacío.
	 * O(1)
	 * 
	 * @return Verdadero si el mapeo está vacío, falso en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		return cant==0;
	}

	/**
	 * Busca la entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * O(1)
	 * 
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public V get(K key) throws InvalidKeyException {
		V salida = null;
		int pos = checkKey(key);
		for(Entry<K,V> e : tabla[pos]){
			if(e.getKey()==key){
				salida = e.getValue();
				break;
			}
		}
		return salida;
	}


	/**
	 * Inserta una entrada con una clave y un valor dado en el mapeo y retorna la entrada creada.
	 * O(1)
	 * 
	 * @param key Clave de la entrada a crear.
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(cant/tabla.length>0.5){
			rehash();
		}
		int pos = checkKey(key);
		boolean esta = false;
		if(value == null){
			throw new InvalidKeyException("no se puedo ingresar claver valor");
		}
		
		V salida = null;
		for(Position<Entrada<K,V>> p : tabla[pos].positions()){
			if(p.element().getKey().equals(key)){
				salida = p.element().getValue();
				p.element().setValue(value);
				esta=true;
				break;
			}
		}
		
		if(!esta){
			tabla[pos].addLast(new Entrada<K,V>(key,value));
			cant++;
		}
		
		return salida;
	}

	/**
	 * Remueve la entrada con clave dada en el mapeo y devuelve el valor de la entrada removida.
	 * O(1)
	 * 
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public V remove(K k) throws InvalidKeyException {
		int pos = checkKey(k);
		V salida = null;
		for(Position<Entrada<K,V>> p : tabla[pos].positions()){
			if(p.element().getKey().equals(k)){
				salida = p.element().getValue();
				try {
					tabla[pos].remove(p);
					cant--;
				} catch (InvalidPositionException e1) {
					//esto nunca va a pasar, se supone que la lista anda del 10
				}
				break;
			}
		}
		return salida;
	}

	/**
	 * Retorna una colección iterable con todas las entradas en el mapeo.
	 * O(n)
	 * 
	 * @return Colección iterable de todas las entradas.
	 */
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> salida = new DoubleLinkedList<Entry<K,V>>();
		for(PositionList<Entrada<K,V>> l : tabla){
			for(Entry<K,V> e : l){
				salida.addLast(e);
			}
		}
		return salida;
	}
	
	private int checkKey(K k) throws InvalidKeyException{
		int salida= 0;
		try{
			salida = k.hashCode()%tabla.length;
		}catch(Exception e){
			throw new InvalidKeyException("key invalida");
		}
		return salida;
	}

	@SuppressWarnings("unchecked")//este lo pongo por el contructor del arreglo
	private void rehash() {
		Iterable<Entry<K,V>> entradasViejas = entries();
		PositionList<Entrada<K,V>>[] old = tabla;
		tabla = new PositionList[old.length*2];
		cant = 0;
		for(int i=0; i<tabla.length; i++){
			tabla[i]= new DoubleLinkedList<Entrada<K,V>>();
		}
		for(Entry<K,V> e : entradasViejas){
			try {
				put(e.getKey(),e.getValue());
			} catch (InvalidKeyException e1) {
				// se supone que esto nunca va a pasar, ya que tenia key buenas guardadas
				System.out.println("si ves este mensaje, se acaba de hacer bosta la estructura");
			}
		}
	}

	/**
	 * Retorna una colección iterable con todas las claves del mapeo.
	 * O(n)
	 * 
	 * @return Colección iterable con todas las claves del mapeo.
	 */
	@Override
	public Iterable<K> keys() {
		PositionList<K> salida = new DoubleLinkedList<K>();
		for(PositionList<Entrada<K,V>> l : tabla){
			for(Entry<K,V> e : l){
				salida.addLast(e.getKey());
			}
		}
		return salida;
	}

	/**
	 * Retorna una colección iterable con todas los valores del mapeo.
	 * O(n)
	 * 
	 * @return Colección iterable con todas los valores del mapeo.
	 */
	@Override
	public Iterable<V> values() {
		PositionList<V> salida = new DoubleLinkedList<V>();
		for(PositionList<Entrada<K,V>> l : tabla){
			for(Entry<K,V> e : l){
				salida.addLast(e.getValue());
			}
		}
		return salida;
	}

}
