package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;

import client.Abonné;
import documents.Document;
import documents.Médiathèque;
import exceptions.RéservationException;
import timer.TimerRetour;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant le lancement du service de réservation de document.
 */
public class ServiceReservation extends Service implements Runnable {

	/**
	 * Le constructeur d'un service de réservation
	 * 
	 * @param socket la socket associée
	 */
	public ServiceReservation(Socket socket) {
		super(socket);
	}

	/**
	 * Méthode Run permettant d'offrir au client le service de réservation. Après la
	 * saisie du numéro du DVD qu'il veut réserver et de son numéro client il
	 * réserve un document de la médiathèque pendant 2 heures s'il est disponible.
	 * S'il ne vient pas le chercher dans les 2 heures suivant la réservation,
	 * celle-ci est annulée et le document retrouve son état Disponible. Essayer de
	 * réserver un document dans d'autres conditions lève des exceptions associées.
	 */
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			out.println(getMédiathèque().toString());
			Thread.sleep(500);
			out.flush();
			out.println("Entrez le numéro du DVD à réserver");
			out.println("Entrez votre numéro client");
			int noDoc = Integer.parseInt(in.readLine());
			int noAbonné = Integer.parseInt(in.readLine());

			Document doc = null;
			Abonné ab = null;
			doc = getMédiathèque().getDocument(noDoc);
			ab = getMédiathèque().getAbonne(noAbonné);
			synchronized (doc) {
				getMédiathèque();
				if (doc != null && !Médiathèque.getAbonnésBannis().contains(ab)) {
					try {
						doc.reservationPour(ab);
						getMédiathèque().getRéservé().put(doc, ab);
						out.println(toString());
						Timer réservation = new Timer();
						réservation.schedule(new TimerRetour(doc, getMédiathèque(), ab), 7200000);
					} catch (RéservationException e) {
						out.println(e);
					}
				}
			}
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
		return "Le document à été réservé avec succès";
	}
}
