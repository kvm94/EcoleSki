package be.marra.ecoleSki;

public class Client extends Personne {
	
	//-------------------------Attributs-------------------------//
	
	private Panier pan;
	
	  
	//-------------------------Constructeurs-------------------------//
	
	public Client(){
	    super();
	    this.pan = new Panier();
	}
	
	public Client(String nom, String prenom, int jour, int mois, int annee){
	    super(nom, prenom, jour, mois, annee);
	    this.pan = new Panier();
	}
	
	
	//-------------------------Méthodes-------------------------//
	
	/**
	 * Vide le panier du client.
	 */
	public void viderPanier() {}
	
	
	//-------------------------Accesseurs-------------------------//
	
	public Panier getPan(){
	    return this.pan;
	}
	  
	public void setPan(Panier pan){
		this.pan = pan;
	}
}
