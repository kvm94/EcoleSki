package be.marra.ecoleSki;

import java.util.ArrayList;

import be.marra.ecoleSki.Reservation.E_Statut;


public class Panier {
	
	//-------------------------Attributs-------------------------//
	
	private ArrayList<Reservation> reservations;
	private double total;
	private boolean reduction;
	
	//-------------------------Constructeur-------------------------//
	
	public Panier(){
		reservations = new ArrayList<Reservation>();
		total = 0;
		reduction = false;
	}
	
	//-------------------------M�thodes-------------------------//
	
	public void initTotal() {
		this.total = 0;
		
		for(int i=0 ; i< reservations.size() ; i++){
			total += reservations.get(i).getPrix();		
		}
		checkReduction();
	}
	
	public void payer(){
		for(int i=0 ; i< reservations.size() ; i++){
			reservations.get(i).setStatut(E_Statut.Paye);	
			reservations.get(i).updateIntoDB();
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
			reservations.get(i).deleteFromDB();
	}

	/**
	 * Supprime une r�servation.
	 */
	public void supprimerReservation(int index) {
		reservations.get(index).deleteFromDB();
	}

	/**
	 * Consulte une r�servation.
	 */
	public void consulterReservation() {}
	
	public boolean isEmpty(){
		boolean check = true;
		
		if(reservations.size() >= 1)
			check = false;
		
		return check;
	}
	
	//[start]Accesseurs
	
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
	
	//[end]


}
