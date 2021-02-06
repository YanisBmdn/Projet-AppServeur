package factory;

import java.io.IOException;
import java.net.ServerSocket;

import services.Services;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe Factory permettant de fabriquer des ServerSocket suivant le
 *         service associé.
 */
public class ServerSocketFactory {
	/**
	 * Méthode permettant de créer une ServerSocket suivant un Service
	 * 
	 * @param Service le Service de la ServerSocket
	 * @return un objet ServerSocket ouvert au port associé au Service, null si le
	 *         service n'existe pas
	 * @throws IOException
	 */
	public static ServerSocket créerServerSocket(Services Service) throws IOException {
		switch (Service) {
		case RESERVATION:
			return new ServerSocket(3000);
		case EMPRUNT:
			return new ServerSocket(4000);
		case RETOUR:
			return new ServerSocket(5000);
		}
		return null;
	}
}
