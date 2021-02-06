package exceptions;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Exception lié au fait que le document est déjà emprunté
 *
 */
public class EmpruntException extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Ce document est déjà emprunté";
	}
}
