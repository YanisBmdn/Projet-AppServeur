package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import documents.Document;
import documents.Médiathèque;
import exceptions.DocumentRéservéException;
import exceptions.EmpruntException;
import client.Abonné;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant le lancement du service d'emprunt de document.
 */
public class ServiceEmprunt extends Service implements Runnable {

	/**
	 * Le constructeur d'un service de réservation
	 * 
	 * @param socket la socket associée
	 */
	public ServiceEmprunt(Socket socket) {
		super(socket);
	}

	/**
	 * Méthode Run permettant d'offrir au client le service d'emprunt. Après la
	 * saisie du numéro du DVD qu'il veut réserver et de son numéro client il
	 * emprunte le document de la médiathèque s'il est disponible ou qu'il l'a
	 * réservé. Essayer de retourner un document dans d'autres conditions lève des
	 * exceptions associées.
	 */
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			out.println(getMédiathèque().toString());
			Thread.sleep(500);
			out.flush();
			out.println("Entrez le numéro du DVD à louer");
			out.println("Entrez votre numéro d'abonné");
			int noDoc = Integer.parseInt(in.readLine());
			int noAbonné = Integer.parseInt(in.readLine());
			Document doc = null;
			Abonné ab = null;
			doc = getMédiathèque().getDocument(noDoc);
			ab = getMédiathèque().getAbonne(noAbonné);
			synchronized (doc) {
				if (doc != null && !Médiathèque.getAbonnésBannis().contains(ab)) {
					try {
						if (getMédiathèque().getRéservé().containsKey(doc)
								&& !getMédiathèque().vérificationRéservation(doc, ab)) {
							throw new DocumentRéservéException();
						} else {
							doc.empruntPar(ab);
							if (getMédiathèque().getRéservé().containsKey(doc))
								getMédiathèque().getRéservé().remove(doc);
							out.println(toString());
						}
					} catch (EmpruntException | DocumentRéservéException e) {
						out.println(e);
					}
				}
			}
			System.out.println("Connexion terminée");

			getSocket().close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant d'obtenir la chaîne de caractères assurant du bon
	 * déroulement du service.
	 */
	@Override
	public String toString() {
		return "Le document a été emprunté avec succès";
	}
}
