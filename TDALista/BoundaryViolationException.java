package TDALista;

/**
 * BoundaryViolationException
 * Esta excepción se lanza si se sobrepasan los limites de la estructura.
 * 
 * @author Agra Federico (94186)
 * @author Loza Carlos (94399)
 *
 */
public class BoundaryViolationException extends Exception {
	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor por defecto, no recive ningun parametro
	 */
	public BoundaryViolationException()
	{
		super();
	}

	/**
	 * contructor clase BoundaryViolationException 
	 * @param S string que entra como parametro, es el mensaje que dice como se produce
	 */
	public BoundaryViolationException(String S)
	{
		super(S);
	}

}
