package be.marra.ecoleSki;

import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.DAO;

public class Client extends Utilisateur {
	
	//-------------------------Attributs-------------------------//
	
	private int 	id;
	private Panier 	pan;
	
	//Base de données//
		private AbstractDAOFactory adf;
		DAO<Client> clientDAO;
	
	  
	//-------------------------Constructeurs-------------------------//
	
	public Client(){
	    super(null, null, 1,1,1);
	    this.pan = new Panier();
	    
	    initDB();
	}
	
	public Client(String nom, String prenom, int jour, int mois, int annee){
	    super(nom, prenom, jour, mois, annee);
	    this.pan = new Panier();
	    
	    initDB();
	}
	
	public Client(int id, String nom, String prenom, int jour, int mois, int annee, String passwd){
	    super(nom, prenom, jour, mois, annee);
	    this.setId(id);
	    this.setPasswd(passwd);
	    this.pan = new Panier();
	    
	    initDB();
	}
	
	
	//-------------------------Méthodes-------------------------//
	
	/**
	 * Vide le panier du client.
	 */
	public void viderPanier() {}
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		clientDAO = adf.getClientDAO();
	}
	
	@Override
	public boolean inscription(){
		if(clientDAO.create(this)){
			return true;
		}
		else
			return false;
	}
	
	@Override
	public boolean connexion(){
		boolean check = false;
		ArrayList<Client> temp;
	
		temp = clientDAO.find(nom, prenom, passwd);
		
		if(temp.size() > 0){
			setNom(nom);
			setPrenom(prenom);
			setPasswd(passwd);
			setDateNaissance(temp.get(0).getDateNaissance());
			setId(temp.get(0).getId());
			check = true;
		}
		else
			check = false;
			
		return check;
	}
	
	//-------------------------Accesseurs-------------------------//
	
	public Panier getPan(){
	    return this.pan;
	}
	  
	public void setPan(Panier pan){
		this.pan = pan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
