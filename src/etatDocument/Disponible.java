package etatDocument;

import client.Abonné;
import documents.Document;
import exceptions.EmpruntException;
import exceptions.RetourException;
import exceptions.RéservationException;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant de caractériser l'état disponible d'un document.
 *         Dans ce cas de figure, on peut emprunter ou réserver le document
 *         associé. Le retour lève les exceptions associées
 */
public class Disponible implements EtatDocument {

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
		return;
	}

	/**
	 * @see EtatDocument#retourner(Document, Abonné)
	 */
	@Override
	public void retourner(Document D) throws RetourException {
		throw new RetourException();
	}

	public String toString() {
		return "Disponible";
	}
}
