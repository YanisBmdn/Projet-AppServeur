package exceptions;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Exception lié au fait que l'utilisateur qui essaie d'emprunter le
 *         document est trop jeune
 *
 */
public class AgeException extends EmpruntException {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Vous n'avez pas l'âge pour emprunter ce DVD";
	}
}
