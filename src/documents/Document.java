package documents;

import client.Abonné;
import exceptions.EmpruntException;
import exceptions.RetourException;
import exceptions.RéservationException;

/**
 * @author JFBrette
 * 
 *         Interface permettant de définir la structure générale d'un document
 */
public interface Document {

	/**
	 * Getter permettant de récuperer le numéro du document
	 * 
	 * @return le numéro du document
	 */
	int numero();

	/**
	 * Permet de réserver un document pour un abonné donné. Change l'état du
	 * document de disponible à réservé.
	 * 
	 * @param ab l'abonné qui réserve le document
	 * @throws RéservationException si le document ne peut être réservé
	 */
	void reservationPour(Abonné ab) throws RéservationException;

	/**
	 * Permet d'emprunter un document pour un abonné donné Change l'état du document
	 * de disponible/réservé à emprunté
	 * 
	 * @param ab l'abonné qui emprunte le document
	 * @throws EmpruntException si le document ne peut pas être réservé
	 */
	void empruntPar(Abonné ab) throws EmpruntException;

	/**
	 * Permet de retourner un document emprunté ou réservé Change l'état du document
	 * de emprunté/réservé à disponible
	 * 
	 * @throws RetourException si le document ne peut pas être retourner
	 */
	void retour() throws RetourException;
}
