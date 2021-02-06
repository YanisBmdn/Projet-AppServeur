package exceptions;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Exception lié au fait que le document ne peut pas être réservé, soit
 *         par ce qu'il est emprunté soit par ce qu'il est réservé par un autre
 *         utilisateur
 *
 */
public class RéservationException extends Exception {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Ce document ne peut pas être réservé pour le moment";
	}
}
