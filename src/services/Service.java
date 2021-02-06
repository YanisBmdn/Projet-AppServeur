package services;

import java.net.Socket;

import documents.Médiathèque;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe abstraite permettant de définir les attributs et la structure
 *         générale d'un service. Permet d'avoir une médiathèque généralisé
 *         entre service associé.
 */
public abstract class Service {
	private Socket client;
	private static Médiathèque médiathèque;

	/**
	 * Constructeur par défaut d'un Service
	 * 
	 * @param socket la socket du service
	 */
	public Service(Socket socket) {
		client = socket;
	}

	/**
	 * Getter permettant d'obtenir la socket associée
	 * 
	 * @return la socket associée
	 */
	public Socket getSocket() {
		return client;
	}

	/**
	 * Getter permettant d'obtenir la médiathèque associée au service
	 * 
	 * @return la médiathèque associée
	 */
	public Médiathèque getMédiathèque() {
		return médiathèque;
	}

	/**
	 * Setter permettant de définir la médiathèque associé aux services. (méthode
	 * statique).
	 * 
	 * @param m la médiathèque associée
	 */
	public static void setMédiathèque(Médiathèque m) {
		médiathèque = m;
	}
}
