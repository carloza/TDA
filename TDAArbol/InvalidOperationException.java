package TDAArbol;

/**
 * InvalidOperationException
 * Esta excepción se lanza si se intenta realizar una opearion invalida.
 * 
 * @author Agra Federico (94186)
 * @author Loza Carlos (94399)
 *
 */
public class InvalidOperationException extends Exception {
	
	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public InvalidOperationException()
	{
		super();
	}

	/**
	 * contructor clase InvalidOperationException
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public InvalidOperationException(String S)
	{
		super(S);
	}

}
