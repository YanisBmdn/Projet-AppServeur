package exceptions;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Exception lié au fait que le document est réservé par un autre
 *         abonné, il ne peut donc pas être emprunté
 *
 */
public class DocumentRéservéException extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Ce document est réservé, il ne peut pas être emprunté";
	}
}
