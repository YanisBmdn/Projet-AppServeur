package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import factory.ServerSocketFactory;
import factory.ServiceFactory;
import services.Services;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Serveur de la médiathèque, écoutant à un port particulier (associé à
 *         un service).
 */
public class ServeurMediatheque implements Runnable {
	private ServerSocket listen_socket;

	/**
	 * Constructeur d'un ServeurMediatheque
	 * 
	 * @param Service le service associé à la ServerSocket
	 * @throws IOException
	 */
	public ServeurMediatheque(Services Service) throws IOException {
		listen_socket = ServerSocketFactory.créerServerSocket(Service);
	}

	/**
	 * Méthode Run permettant de lancer la ServerSocket au port associé, et
	 * d'attendre l'arrivé d'un client pour offrir le service associé. Ces
	 * ServerSocket sont sur écoute tant que l'application est en route (à priori
	 * 24h/24h).
	 */
	@Override
	public void run() {
		try {
			System.err.println("Lancement du serveur au port " + this.listen_socket.getLocalPort());
			System.out.println(this.listen_socket.toString());
			ServiceFactory.créerService(listen_socket);

		} catch (IOException e) {
			try {
				this.listen_socket.close();
				System.err.println("Arrêt du serveur au port " + this.listen_socket.getLocalPort());
			} catch (IOException e1) {
			}
		}
	}

	protected void finalize() throws Throwable {
		try {
			this.listen_socket.close();
		} catch (IOException e1) {
		}
	}
}
