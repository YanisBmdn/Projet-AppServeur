package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant le lancement de l'application cliente qui permet
 *         d'accéder à un service (à noter que le port du service et l'ip du
 *         serveur doivent être rentrés dans le tableau d'arguments du main()).
 */
public class AppliClient {
	/**
	 * @note Il faut rentrer le port associé à la socket client en argument de la
	 *       ligne de commande Cela facilitera l'implémentation du logiciel sur les
	 *       bornes de la médiathèque.
	 * @param args[0] l'ip du serveur (ici localhost)
	 * @param args[1] le port associé au service demandé
	 */
	public static void main(String[] args) {
		try {
			Socket socket = null;
			socket = new Socket(args[0], Integer.parseInt(args[1]));
			BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println(sin.readLine());
				if (!sin.ready())
					break;
			}

			while (true) {
				sout.flush();
				System.out.println(sin.readLine());
				String UserIn = clavier.readLine();
				sout.println(UserIn);
				if (!sin.ready())
					break;
			}
			System.out.println(sin.readLine());
			socket.close();

		} catch (IOException e) {
		}

	}

}
