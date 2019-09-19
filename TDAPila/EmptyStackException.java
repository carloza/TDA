package TDAPila;

/**
 * EmptyStackException
 * Esta excepción se lanza si la pila esta vacia.
 * 
 * @author Agra Federico (94186)
 * @author Loza Carlos (94399)
 *
 */
public class EmptyStackException extends Exception {
	
	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public EmptyStackException()
	{
		super();
	}

	/**
	 * contructor clase EmptyStackException
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public EmptyStackException(String S)
	{
		super(S);
	}

}
