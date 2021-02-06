package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import client.Abonné;
import documents.Document;
import exceptions.RetourException;
import timer.TimerInterdictionEmprunt;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant le lancement du service de retour de document
 */
public class ServiceRetour extends Service implements Runnable {

	/**
	 * Le constructeur d'un service de retour
	 * 
	 * @param socket la socket associée
	 */
	public ServiceRetour(Socket socket) {
		super(socket);
	}

	/**
	 * Méthode Run permettant d'offrir au client le service de retour. Après la
	 * saisie du numéro du DVD qu'il veut retourner, le document est retourné s'il
	 * est actuellement emprunté ou réservé. Essayer de retourner un document dans
	 * d'autres conditions lève des exceptions associées.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			out.println("Bienvenue dans le service de retour !");
			Thread.sleep(500);

			out.println("Entrez le numéro du Document à retourner");
			out.println("Entrez votre numéro d'abonné");
			out.println("Le document à t-il été dégradé ? rendu en retard ? O - N");
			int noDoc = Integer.parseInt(in.readLine());
			int noAbonné = Integer.parseInt(in.readLine());
			String faute = in.readLine();
			Date today = new Date();
			today = Calendar.getInstance().getTime();
			today.setMonth(Calendar.getInstance().getTime().getMonth() + 1);
			Document doc = null;
			Abonné ab = null;
			String S = "";
			doc = getMédiathèque().getDocument(noDoc);
			synchronized (doc) {
				if (doc != null && (ab = getMédiathèque().getAbonne(noAbonné)) != null) {
					try {
						doc.retour();
						if (faute.toUpperCase().startsWith("O")) {
							Timer T = new Timer();
							T.schedule(new TimerInterdictionEmprunt(ab), today);
							S = ". Vous avez été banni 1 mois pour votre faute";
						}
						out.println(toString() + S);
					} catch (RetourException e) {
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
		return "Le document a été retourné avec succès";
	}
}
