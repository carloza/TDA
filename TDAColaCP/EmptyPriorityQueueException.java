package TDAColaCP;

public class EmptyPriorityQueueException extends Exception {
	/**
	 * default seria version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public EmptyPriorityQueueException()
	{
		super();
	}

	/**
	 * contructor clase EmptyPriorityQueueException 
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public EmptyPriorityQueueException(String S)
	{
		super(S);
	}


}
