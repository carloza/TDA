package TDALista;

/**
 * EmptyListException
 * Esta excepción se lanza si se intenta acceder a una lista vacia.
 * 
 * @author Agra Federico (94186)
 * @author Loza Carlos (94399)
 *
 */
public class EmptyListException extends Exception {
	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public EmptyListException()
	{
		super();
	}

	/**
	 * contructor clase EmptyListException 
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public EmptyListException(String S)
	{
		super(S);
	}
}
