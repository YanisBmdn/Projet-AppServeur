package serveur;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;

import client.Abonné;
import documents.DVD;
import documents.Médiathèque;
import services.Service;
import services.Services;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant le lancement de l'application serveur, qui
 *         initialise les différents documents et abonnés de la médiathèque et
 *         qui par la suite lance les différents Threads d'écoute sur les ports
 *         liés aux services
 *
 */
public class AppliServeur {
	public static void main(String[] args) throws ParseException {
		Médiathèque médiathèque = new Médiathèque(1);
		médiathèque.ajouterAbonné((new Abonné(1, "Bouboucars", LocalDate.of(2012, Month.JANUARY, 21))));
		médiathèque.ajouterAbonné((new Abonné(2, "Brette", LocalDate.of(2000, Month.JUNE, 14))));

		médiathèque.ajouterDocument((new DVD(1, "Les sockets de Noël", false)));
		médiathèque.ajouterDocument((new DVD(2, "Hide and Seek", true)));
		médiathèque.ajouterDocument((new DVD(3, "Martine fait du ski", false)));
		médiathèque.ajouterDocument((new DVD(4, "Petits Pieds du bonheur", false)));
		médiathèque.ajouterDocument((new DVD(5, "Bagnoles 2", false)));
		médiathèque.ajouterDocument((new DVD(6, "Le jeu de la faim", true)));
		médiathèque.ajouterDocument((new DVD(7, "T'choupi et la magie de noël", false)));
		médiathèque.ajouterDocument((new DVD(8, "Rapides et dangereux", true)));
		médiathèque.ajouterDocument((new DVD(9, "Poulets en fuite", false)));
		médiathèque.ajouterDocument((new DVD(10, "Histoire de jouets", false)));

		Service.setMédiathèque(médiathèque);

		try {
			new Thread(new ServeurMediatheque(Services.RESERVATION)).start();
			new Thread(new ServeurMediatheque(Services.EMPRUNT)).start();
			new Thread(new ServeurMediatheque(Services.RETOUR)).start();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
