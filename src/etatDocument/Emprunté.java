package etatDocument;

import client.Abonné;
import documents.Document;
import exceptions.EmpruntException;
import exceptions.RetourException;
import exceptions.RéservationException;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant de caractériser l'état emprunté d'un document. Dans
 *         ce cas de figure, on ne peut que retourner le document associé.
 *         L'emprunt ou la réservation lève les exceptions associées
 */
public class Emprunté implements EtatDocument {

	/**
	 * @see EtatDocument#emprunter(Document, Abonné)
	 */
	@Override
	public void emprunter(Document D, Abonné A) throws EmpruntException {
		throw new EmpruntException();
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
		return "Emprunté";
	}
}
