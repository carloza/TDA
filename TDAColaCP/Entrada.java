package TDAColaCP;

public class Entrada<K, V> implements Entry<K,V> {
	
	private K key;
	private V value;

	/**
	 * constructor clase Entrada, para Entry
	 * @param k clave
	 * @param v valor
	 */
	public Entrada(K k, V v)
	{
		key = k;
		value = v;
	}

	/**
	 * consulta de clave almacenada
	 * @return clave almacenada
	 */
	@Override
	public K getKey() {
		return key;
	}

	/**
	 * consulta de valor almacenada
	 * @return valor almacenada
	 */
	@Override
	public V getValue() {
		return value;
	}

	/**
	 * setea una clave nueva a la entrada
	 * @param k clave nueva
	 * @return clave vieja
	 */
	public K setKet(K k)
	{
		K old = key;
		key = k;
		return old;
	}

	/**
	 * setea una valor nueva a la entrada
	 * @param v valor nueva
	 * @return valor vieja
	 */
	public V setValue(V v)
	{
		V old = value;
		value = v;
		return old;
	}

}
