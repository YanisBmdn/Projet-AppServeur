package client;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Yanis, Rémy et Alexis
 * 
 *         Classe permettant de créer un abonné, client de la médiathèque.
 */
public class Abonné {
	private final int numéro;
	@SuppressWarnings("unused")
	private String nom;
	private LocalDate naissance;

	/**
	 * Constructeur d'un abonné permettant de lui assigner un identifiant, un nom et
	 * une date de naissance
	 * 
	 * @param num       l'identifiant de l'abonné
	 * @param nom       le nom de l'abonné
	 * @param naissance la date de naissance de l'abonné
	 */
	public Abonné(int num, String nom, LocalDate naissance) {
		this.numéro = num;
		this.nom = nom;
		this.naissance = naissance;
	}

	/**
	 * Getter permettant d'obtenir le numéro de l'abonné
	 * 
	 * @return le numéro identifiant l'abonné
	 */
	public int getNuméro() {
		return numéro;
	}

	/**
	 * Getter permettant d'obtenir l'âge de l'abonné
	 * 
	 * @return l'âge de l'abonné
	 */
	public int getAge() {
		return Period.between(naissance, LocalDate.now()).getYears();
	}
}
