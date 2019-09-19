package TDAArbol;

/**
 * EmptyTreeException
 * Esta excepción se lanza si el arbol esta vacio.
 * 
 * @author Agra Federico (94186)
 * @author Loza Carlos (94399)
 *
 */
public class EmptyTreeException extends Exception {
	
	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public EmptyTreeException()
	{
		super();
	}

	/**
	 * contructor clase EmptyTreeException
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public EmptyTreeException(String S)
	{
		super(S);
	}

}
