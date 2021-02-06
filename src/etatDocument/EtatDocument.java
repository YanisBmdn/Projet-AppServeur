package etatDocument;

import client.Abonné;
import documents.Document;
import exceptions.EmpruntException;
import exceptions.RetourException;
import exceptions.RéservationException;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Interface permettant de définir la structure générale d'un
 *         EtatDocument. Permet de déléguer l'état d'un document à des
 *         "objets-états".
 */
public interface EtatDocument {
	/**
	 * Permet la vérification de la possibilité d'emprunt d'un document
	 * 
	 * @param D le document à emprunter
	 * @param A l'abonné qui emprunte le document
	 * @throws EmpruntException si l'état du document ne permet pas l'emprunt
	 */
	public void emprunter(Document D, Abonné A) throws EmpruntException;

	/**
	 * Permet la vérification de la possibilité de réservation d'un document
	 * 
	 * @param D le document à réserver
	 * @param A l'abonné qui réserve le document
	 * @throws RéservationException si l'état du document ne permet pas la
	 *                              réservation
	 */
	public void réserver(Document D, Abonné A) throws RéservationException;

	/**
	 * Permet la vérification de la possibilité de retour d'un document
	 * 
	 * @param D le document à retourner
	 * @throws RetourException si l'état du document ne permet pas le retour
	 */
	public void retourner(Document D) throws RetourException;
}
