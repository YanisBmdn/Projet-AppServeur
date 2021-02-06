package exceptions;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Exception lié au fait que le retour du document est impossible,
 *         puisqu'il n'est ni emprunté ni réservé
 *
 */
public class RetourException extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Retour impossible, le document n'est ni réservé ni emprunté";
	}
}
