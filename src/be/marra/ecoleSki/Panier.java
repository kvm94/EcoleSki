package be.marra.ecoleSki;

import java.util.ArrayList;

public class Panier {
	
	//-------------------------Attributs-------------------------//
	
	ArrayList<Reservation> reservations;
	double total;
	
	//-------------------------Constructeur-------------------------//
	
	public Panier(){
		//Initialise le panier avec la base de données.
		initTotal();
	}

	
	//-------------------------Méthodes-------------------------//
	
	/**
	 * Calcule le total du panier.
	 */
	public void initTotal() {}
	
	/**
	 * Vérifie si le panier a droit à 15% de réduction si une réservation a été faite le matin et l'après midi.
	 */
	public void checkReduction() {}

	/**
	 * Vide le panier.
	 */
	public void vider() {}

	/**
	 * Ajoute une réservation.
	 */
	public void ajouterReservation() {}

	/**
	 * Supprime une réservation.
	 */
	public void supprimerReservation() {}

	/**
	 * Consulte une réservation.
	 */
	public void consulterReservation() {}
}
