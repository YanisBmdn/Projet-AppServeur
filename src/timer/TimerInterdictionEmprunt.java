package timer;

import java.util.TimerTask;
import client.Abonné;
import documents.Médiathèque;

/**
 * @author Yanis, Rémy et Alexis
 * 
 * Classe permettant la mise en place du compte à rebours pour le banissement
 * d'un utilisateur après retard ou dégradation
 */
public class TimerInterdictionEmprunt extends TimerTask{
	private Abonné abonnéInterdit;
	
	/**
	 * Constructeur de la tâche du timer d'interdiction d'emprunt
	 * @param A l'abonné qui a effectué la faute
	 */
	public TimerInterdictionEmprunt(Abonné A) {
		abonnéInterdit = A;
	}
	
	/**
	 * Méthode Run qui, après que le temps du Timer soit écoulé, retire l'abonné
	 * bannis de la liste des bannis des médiathèques.
	 */
	@Override
	public void run() {
		Médiathèque.getAbonnésBannis().remove(abonnéInterdit);
	}
}
