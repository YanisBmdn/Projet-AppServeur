package timer;

import java.util.TimerTask;

import client.Abonné;
import documents.Document;
import documents.Médiathèque;
import exceptions.RetourException;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant la mise en place du compte à rebours pour la
 *         réservation
 */
public class TimerRetour extends TimerTask {
	private Document document;
	private Médiathèque médiathèque;
	@SuppressWarnings("unused")
	private Abonné abonné;

	/**
	 * Constructeur de la tâche du timer de réservation
	 * 
	 * @param D le document réservé
	 * @param M la médiathèque dans laquelle le document est réservé
	 * @param A l'abonné qui a réservé le document
	 */
	public TimerRetour(Document D, Médiathèque M, Abonné A) {
		document = D;
		médiathèque = M;
	}

	/**
	 * Méthode Run permettant, si le Timer n'a pas été interrompu, de retourner le
	 * document dans un état disponible (Temps écoulé...).
	 */
	@Override
	public void run() {
		if (médiathèque.getRéservé().containsKey(document)) {
			try {
				document.retour();
				médiathèque.getRéservé().remove(document);
			} catch (RetourException e) {
				e.printStackTrace();
			}
		}
	}

}
