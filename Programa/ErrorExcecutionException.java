package Programa;

public class ErrorExcecutionException extends Exception {

	/**
	 * default seria version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public ErrorExcecutionException()
	{
		super();
	}

	/**
	 * contructor clase ErrorExcecutionException
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public ErrorExcecutionException(String S)
	{
		super(S);
	}

}
