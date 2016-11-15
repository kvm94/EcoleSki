package be.marra.ecoleSki;

import java.util.ArrayList;

import be.marra.ecoleSki.Reservation.E_Statut;


public class Panier {
	
	//[start]Attributs
	
	private ArrayList<Reservation> 	reservations;
	private double 					total;
	private boolean 				reduction;
	
	//[end]Attributs
	
	//[start]Constructeur
	
	public Panier(){
		reservations = new ArrayList<Reservation>();
		total = 0;
		reduction = false;
	}
	
	//[end]Constructeur
	
	//[start]M�thodes
	
	/**
	 * Initialise l'acc�s � la base de donn�es.
	 */
	public void initTotal() {
		this.total = 0;
		
		for(int i=0 ; i< reservations.size() ; i++){
			total += reservations.get(i).getPrix();		
		}
		checkReduction();
	}
	
	/**
	 * Paye le contenus du panier.
	 * @throws Exception 
	 */
	public void payer() throws Exception{
		for(int i=0 ; i< reservations.size() ; i++){
			reservations.get(i).setStatut(E_Statut.Paye);	
			reservations.get(i).update();
		}

	}
	

	/**
	 * V�rifie si le panier a droit � 15% de r�duction si une r�servation a �t� faite le matin et l'apr�s midi.
	 */
	@SuppressWarnings("deprecation")
	private void checkReduction() {
		
		boolean matin =false;
		boolean apresMidi = false;
		
		for(int i=0 ; i< reservations.size() ; i++){
			if(reservations.get(i).getHeure().getHours() < 12)
				matin = true;
			else if(reservations.get(i).getHeure().getHours() == 14)
				apresMidi =true;
			
			//R�duction de 15%
			if(matin && apresMidi){
				total *= 0.85;
				reduction = true;
			}
		}
		
	}

	/**
	 * Vide le panier.
	 */
	public void vider() {
		for(int i=0; i < reservations.size() ;i++)
			reservations.get(i).supprimer();
		reservations.clear();
	}
	
	/**
	 * Ajoute une r�servation au panier.
	 * @param r la r�servation � ajouter.
	 */
	public void ajouter(Reservation r){
		reservations.add(r);
	}

	/**
	 * Supprime une r�servation.
	 * @param index La position de la r�servation dans la liste.
	 */
	public void supprimerReservation(int index) {
		reservations.get(index).supprimer();
	}
	
	//[end]M�thodes
	
	//[start]Accesseurs
	
	public boolean isEmpty(){
		boolean check = true;
		
		if(reservations.size() >= 1)
			check = false;
		
		return check;
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isReduction() {
		return reduction;
	}

	public void setReduction(boolean reduction) {
		this.reduction = reduction;
	}
	
	//[end]Accesseurs


}
