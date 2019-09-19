package TDAColaCP;

public class InvalidKeyException extends Exception {
	/**
	 * default seria version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public InvalidKeyException()
	{
		super();
	}

	/**
	 * contructor clase InvalidKeyException 
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public InvalidKeyException(String S)
	{
		super(S);
	}


}
