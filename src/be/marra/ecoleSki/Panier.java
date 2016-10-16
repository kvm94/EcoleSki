package be.marra.ecoleSki;

import java.util.ArrayList;

public class Panier
{
  double total;
  ArrayList<Reservation> reservations;
  
  public Panier(){
	  //Initialise le panier avec la base de données.
	  initTotal();
  }
  
  public void initTotal() {}
  
  public void checkReduction() {}
  
  public void vider() {}
  
  public void ajouterReservation() {}
  
  public void supprimerReservation() {}
  
  public void consulterReservation() {}
}
