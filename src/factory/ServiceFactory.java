package factory;

import java.io.IOException;
import java.net.ServerSocket;

import services.ServiceEmprunt;
import services.ServiceReservation;
import services.ServiceRetour;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe Factory permettant de fabriquer un service associé au port
 *         d'une ServerSocket.
 */
public class ServiceFactory {
	/**
	 * Méthode permettant de créer un nouveau Thread associé à un service (suivant
	 * le port de la socket entrée en paramètre)
	 * 
	 * @param listen_socket la socket d'écoute
	 * @throws IOException
	 */
	public static void créerService(ServerSocket listen_socket) throws IOException {
		switch (listen_socket.getLocalPort()) {
		case 3000:
			while (true)
				new Thread(new ServiceReservation(listen_socket.accept())).start();
		case 4000:
			while (true)
				new Thread(new ServiceEmprunt(listen_socket.accept())).start();
		case 5000:
			while (true)
				new Thread(new ServiceRetour(listen_socket.accept())).start();
		}
	}
}
