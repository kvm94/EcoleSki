package be.marra.ecoleSki;

import java.util.ArrayList;

public class Panier {
	
	//-------------------------Attributs-------------------------//
	
	ArrayList<Reservation> reservations;
	double total;
	
	//-------------------------Constructeur-------------------------//
	
	public Panier(){
		//Initialise le panier avec la base de donn�es.
		initTotal();
	}

	
	//-------------------------M�thodes-------------------------//
	
	/**
	 * Calcule le total du panier.
	 */
	public void initTotal() {}
	
	/**
	 * V�rifie si le panier a droit � 15% de r�duction si une r�servation a �t� faite le matin et l'apr�s midi.
	 */
	public void checkReduction() {}

	/**
	 * Vide le panier.
	 */
	public void vider() {}

	/**
	 * Ajoute une r�servation.
	 */
	public void ajouterReservation() {}

	/**
	 * Supprime une r�servation.
	 */
	public void supprimerReservation() {}

	/**
	 * Consulte une r�servation.
	 */
	public void consulterReservation() {}
}
