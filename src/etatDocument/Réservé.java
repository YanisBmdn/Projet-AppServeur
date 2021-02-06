package etatDocument;

import client.Abonné;
import documents.Document;
import exceptions.EmpruntException;
import exceptions.RetourException;
import exceptions.RéservationException;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant de caractérisé l'état réservé d'un document. Dans
 *         ce cas de figure, on peut emprunter ou retourner le document associé.
 *         La réservation lève les exceptions associées
 */
public class Réservé implements EtatDocument {

	/**
	 * @see EtatDocument#emprunter(Document, Abonné)
	 */
	@Override
	public void emprunter(Document D, Abonné A) throws EmpruntException {
		return;
	}

	/**
	 * @see EtatDocument#réserver(Document, Abonné)
	 */
	@Override
	public void réserver(Document D, Abonné A) throws RéservationException {
		throw new RéservationException();
	}

	/**
	 * @see EtatDocument#retourner(Document, Abonné)
	 */
	@Override
	public void retourner(Document D) throws RetourException {
		return;
	}

	public String toString() {
		return "Réservé";
	}
}
