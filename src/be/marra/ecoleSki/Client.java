package be.marra.ecoleSki;

import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.ClientDAO;

public class Client extends Utilisateur {
	
	//[start]Attributs
	
	private int 	id;
	private Panier 	pan;
	
	//Base de donn�es//
		private AbstractDAOFactory adf;
		ClientDAO clientDAO;
	
	//[end]
	  
	//[start]Constructeurs
		
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
	
	//[end]
	
	//[start]M�thodes
	
	/**
	 * Vide le panier du client.
	 */
	public void viderPanier() {}
	
	/**
	 * Initialise l'acc�s � la base de donn�es.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		clientDAO = (ClientDAO)adf.getClientDAO();
	}
	
	
	/**
	 * Inscrit le client dans la base de donn�es.
	 * @return True si l'inscription c'est effectu�.
	 */
	@Override
	public boolean inscription(){
		if(clientDAO.create(this)){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * R�cup�re les information du client de la base de donn�es.
	 * @return True si la connexion c'est effectu�.
	 */
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
	
	//[end]
	
	//[start]Accesseurs
	
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

	//[end]
}
