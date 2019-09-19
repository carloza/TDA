package TDALista;

/**
 * InvalidPositionException
 * Esta excepción se lanza si se detecta alguna posicion invalida o defectuosa.
 * 
 * @author Agra Federico (94186)
 * @author Loza Carlos (94399)
 *
 */
public class InvalidPositionException extends Exception {
	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public InvalidPositionException()
	{
		super();
	}

	/**
	 * contructor clase InvalidPositionException
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public InvalidPositionException(String S)
	{
		super(S);
	}

}
