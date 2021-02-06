package documents;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import client.Abonné;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant de créer une médiathèque. Cette médiathèque
 *         peut-être assimilé à une base de données, avec des tables pour les
 *         documents, les abonnés et les documents réservés.
 */
public class Médiathèque {
	@SuppressWarnings("unused")
	private int IDMediatheque;
	private Vector<Document> documents;
	private Vector<Abonné> abonnés;
	private static Vector<Abonné> abonnésBannis;
	private Map<Document, Abonné> réservés;

	/**
	 * Constructeur d'une médiathèque, permettant de lui assigner un numéro
	 * identifiant. Il initialise les différentes listes de la médiathèques
	 * (documents, abonnés et réservés).
	 * 
	 * @param num l'identifiant de la médiathèque.
	 */
	public Médiathèque(int num) {
		IDMediatheque = num;
		documents = new Vector<>();
		abonnés = new Vector<>();
		abonnésBannis = new Vector<>();
		réservés = Collections.synchronizedMap(new HashMap<Document, Abonné>());
	}

	/**
	 * Setter permettant d'ajouter un document à la médiathèque.
	 * 
	 * @param doc le document à ajouter.
	 */
	public void ajouterDocument(Document doc) {
		documents.add(doc);
	}

	/**
	 * Setter permettant d'ajouter un abonné à la médiathèque.
	 * 
	 * @param ab le nouvel abonné à ajouter.
	 */
	public void ajouterAbonné(Abonné ab) {
		abonnés.add(ab);
	}

	/**
	 * Getter permettant de récupérer le document associé au numéro passé en
	 * paramètre.
	 * 
	 * @param numéro l'identifiant du document.
	 * @return le document associé au numéro passé en paramètre, null sinon.
	 */
	public Document getDocument(int numéro) {
		for (Document D : documents) {
			if (D.numero() == numéro) {
				return D;
			}
		}
		return null;
	}

	/**
	 * Getter permettant de récupérer l'abonné associé au numéro passé en paramètre.
	 * 
	 * @param numéro l'identifiant de l'abonné.
	 * @return l'abonné associé au numéro passé en paramètre, null sinon.
	 */
	public Abonné getAbonne(int numéro) {
		for (Abonné A : abonnés) {
			if (A.getNuméro() == numéro) {
				return A;
			}
		}
		return null;
	}

	/**
	 * Getter permettant de récupérer les documents réservés.
	 * 
	 * @return la HashMap contenant les documents réservés.
	 */
	public Map<Document, Abonné> getRéservé() {
		return réservés;
	}

	/**
	 * Getter permettant de récupérer les abonnés bannis
	 * 
	 * @note Les abonnés sont bannis pour toutes les médiathèques (méthode static)
	 * @return la liste des abonnés bannis
	 */
	public static Vector<Abonné> getAbonnésBannis() {
		return abonnésBannis;
	}

	/**
	 * Méthode de vérification de la réservation. Permet de vérifier qu'un document
	 * est associé au bon abonné passé en paramètre.
	 * 
	 * @param D le document réservé
	 * @param A l'abonné qui a à priori réservé le document
	 * @return true si l'abonné à réservé le document, false sinon.
	 */
	public boolean vérificationRéservation(Document D, Abonné A) {
		for (Entry<Document, Abonné> entry : réservés.entrySet()) {
			if (entry.getKey() == D && entry.getValue() == A) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode permettant de renvoyer les documents possédés par la médiathèque sous
	 * forme de chaîne de caractères. Ces documents peuvent être disponible, réservé
	 * ou emprunté.
	 * 
	 * @return la chaine de caractères correspondante.
	 */
	public String toString() {
		String s = "";
		s += "Documents présents dans la médiathèque\n";
		for (Document D : documents) {
			s += D.toString() + "\n";
		}
		return s;
	}
}
