package TDACola;

/**
 * EmptyQueueException
 * Esta excepción se lanza si la Cola esta vacia.
 * 
 * @author Agra Federico (94186)
 * @author Loza Carlos (94399)
 *
 */
public class EmptyQueueException extends Exception {

	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public EmptyQueueException()
	{
		super();
	}

	/**
	 * contructor clase EmptyQueueException
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public EmptyQueueException(String S)
	{
		super(S);
	}

}
