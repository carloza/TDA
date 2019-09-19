package TDADiccionario;

import TDAColaCP.*;
import TDALista.*;

/**
 * Implementación del TDA Diccionario. Este modelo permite elazar claves a valores, estas claves
 * pueden repetirse con distintos valores, es decir, que con una misma clave, puedo dirigirme
 * a distintos valores, el enlace de claves a valores esta dado por el par (Key,Value) donde "key"
 * es la clave para determinados valores "values" (uno o mas). Para esta modelo se utiliza como
 * estructura una tabla hash cerrada, donde cada lugar de la tabla almacena una sola entrada, y las
 * entradas con mismas clave se encuentran una próxima a la otra.
 * 
 * @author LOZA, Carlos (94399)
 * @author AGRA, Federico (94186)
 *
 * @param <K> clave
 * @param <V> valor
 */
public class ClosedHDictionary<K,V> implements Dictionary<K,V>{

	//Atributos
	private Entrada<K,V>[] tabla;
	private int cant;
	
	/**
	 * Constructor
	 * Crea un Diccionario vacio.
	 */
	@SuppressWarnings("unchecked")//este lo pongo por el constructor del arreglo
	public ClosedHDictionary()
	{
		tabla = new Entrada[100];
		cant = 0;
	}
	
	/**
	 * Consulta el número de entradas del diccionario.
	 * @return Número de entradas del diccionario.
	 */
	@Override
	public int size() {
		return cant;
	}
	
	/**
	 * Consulta si el diccionario está vacío.
	 * @return Verdadero si el diccionario está vacío, falso en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		return (cant==0);
	}

	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		
		int pos = checkKey(key);
		
		Entry<K,V> salida = null;
		if(!isEmpty())
		{
			boolean esta = false;
			while ((tabla[pos]!=null)&&(!esta))
			{
				if (tabla[pos].getKey()!=null)
					if(tabla[pos].getKey().equals(key))
					{
						salida = tabla[pos];
						esta = true;
					}
				pos = ((pos+1)%tabla.length);
			}
		}
		
		return salida;
	}
	
	/**
	 * Retorna una colección iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colección iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		
		PositionList<Entry<K,V>> salida = new DoubleLinkedList<Entry<K,V>>();
		int pos = checkKey(key);
		
		while(tabla[pos]!=null)
		{
			if (tabla[pos].getKey()!=null)
			{
				if (tabla[pos].getKey().equals(key))
				{
					salida.addLast(tabla[pos]);
				}
			}
			pos = ((pos+1)%tabla.length);
		}
				
		return salida;
	}
	
	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por parámetro es inválida.
	 */
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		
		if(cant/tabla.length>0.5){
			rehash();
		}
		
		Entrada<K,V> salida = new Entrada<K,V>(key,value);
		if (value==null) { throw new InvalidKeyException("No se puede insertar una entrada con valor nulo."); }
		int pos = checkKey(key);
		if (tabla[pos]==null)
		{
			tabla[pos] = salida;
		}
		else
		{
			boolean cortar = false;
			while ((tabla[pos]!=null)&&(!cortar))
			{
				if(tabla[pos].getKey()==null)
				{
					tabla[pos] = salida;
					cortar = true;
				}
				pos = ((pos+1)%tabla.length);
			}
			if(!cortar)
			{ 
				tabla[pos] = salida;
			}
		}
		cant++;
		return salida;
	}

	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la entrada no está en el diccionario o es inválida.
	 */
	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		
		if (isEmpty()) { throw new InvalidEntryException("Diccionario vacio."); }
		
		Entry<K,V> salida = null;
		try
		{
			int pos = checkKey(e.getKey());
			boolean esta = false;
			
			while ((tabla[pos]!=null)&&(!esta))
			{
				if (tabla[pos].getKey()!=null)
				{
					if (tabla[pos].getKey().equals(e.getKey())&&tabla[pos].getValue().equals(e.getValue()))
					{
						salida = tabla[pos];
						tabla[pos].setKet(null);
						tabla[pos].setValue(null);
						cant--;
						esta = true;
					}
				}
				pos = ((pos+1)%tabla.length);
			}
		}
		catch(InvalidKeyException exc) { throw new InvalidEntryException("Key invalida."); }
		
		
		return salida;
	}
	
	/**
	 * Retorna una colección iterable con todas las entradas en el diccionario.
	 * @return Colección iterable de todas las entradas.
	 */
	@Override
	public Iterable<Entry<K, V>> entries() {
		
		PositionList<Entry<K,V>> salida = new DoubleLinkedList<Entry<K,V>>();
		for(Entrada<K,V> e : tabla){
			if ((e!=null)&&(e.getKey()!=null))
			{
				salida.addLast(e);
			}
		}
		return salida;
	}
	
	//Método privado. verifica si la key pasada es valida y retorna su hash code
	private int checkKey(K k) throws InvalidKeyException{
		
		int salida = 0;
		try{
			salida = k.hashCode()%tabla.length;
		}catch(Exception e){
			throw new InvalidKeyException("key invalida");
		}
		return salida;
	}
	
	//Método privado. Duplica la longitud de la tabla.
	@SuppressWarnings("unchecked")//este lo pongo por el contructor del arreglo
	private void rehash() {
		
		Iterable<Entry<K,V>> entradasViejas = entries();
		Entry<K,V>[] old = tabla;
		tabla = new Entrada[old.length*2];
		cant = 0;
		for(Entry<K,V> e : entradasViejas){
			try 
			{
				insert(e.getKey(),e.getValue());
			} catch (InvalidKeyException e1) {
				// se supone que esto nunca va a pasar, ya que tenia key buenas guardadas
				System.out.println("si ves este mensaje, se acaba de hacer bosta la estructura");
			}
		}
	}

}
