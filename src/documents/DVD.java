package documents;

import client.Abonné;
import etatDocument.Disponible;
import etatDocument.Emprunté;
import etatDocument.EtatDocument;
import etatDocument.Réservé;
import exceptions.AgeException;
import exceptions.EmpruntException;
import exceptions.RetourException;
import exceptions.RéservationException;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant de créer un DVD. On peut par la suite effectuer des
 *         opérations dessus (emprunt, réservation et retour).
 */
public class DVD implements Document {
	private final int numéro;
	private String titre;
	private boolean adulte;
	private EtatDocument état;

	/**
	 * Constructeur d'un DVD permettant de lui assigner un numéro, un titre et de
	 * savoir s'il est pour adulte ou non (booléen).
	 * 
	 * @param num    le numéro du DVD
	 * @param titre  le titre du DVD
	 * @param adulte l'âge recommandé pour le film (true si pour adulte, false
	 *               sinon).
	 */
	public DVD(int num, String titre, boolean adulte) {
		this.numéro = num;
		this.titre = titre;
		this.adulte = adulte;
		this.état = new Disponible();
	}

	/**
	 * @see Document#numero()
	 */
	@Override
	public int numero() {
		return numéro;
	}

	/**
	 * @see Document#reservationPour(Abonné)
	 */
	@Override
	public void reservationPour(Abonné ab) throws RéservationException {
		this.état.réserver(this, ab);
		this.état = new Réservé();
	}

	/**
	 * @note Si l'abonné à moins de 18 ans et qu'il essaye d'emprunter un DVD pour
	 *       adulte, la méthode lève l'exception lié
	 * @see AgeException
	 * @see Document#empruntPar(Abonné)
	 */
	@Override
	public void empruntPar(Abonné ab) throws EmpruntException {
		if (adulte && ab.getAge() < 18) {
			throw new AgeException();
		}
		this.état.emprunter(this, ab);
		this.état = new Emprunté();
	}

	/**
	 * @see Document#retour()
	 */
	@Override
	public void retour() throws RetourException {
		this.état.retourner(this);
		this.état = new Disponible();
	}

	/**
	 * Méthode permettant de représenter un document sous forme de chaîne de
	 * caractères (titre, état et numéro).
	 * 
	 * @return la chaine de caractères correspondante.
	 */
	public String toString() {
		return titre + " : " + état.toString() + "\n identifiant : " + numéro + "\n";
	}

}
