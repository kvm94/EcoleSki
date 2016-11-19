package be.marra.ecoleSki;

import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.ClientDAO;
import be.marra.ecoleSki.Reservation.E_Statut;

public class Client extends Utilisateur {
	
	//[start]Attributs
	
	private int 	id;
	private Panier 	pan;
	
	//Base de données//
	private AbstractDAOFactory 	adf;
	private ClientDAO 			clientDAO;
	
	//[end]Attributs
	  
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
	
	//[end]Constructeurs
	
	//[start]Méthodes
	
	/**
	 * Initialise l'accès à la base de données.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		clientDAO = (ClientDAO)adf.getClientDAO();
	}
	
	/**
	 * Charge les réservations dans le panier et calcul le total.
	 */
	public void initPanier(){
		//pan.setReservations(Reservation.loadByIdClient(E_Statut.Reserve, id));
		pan.getReservations().clear();
		ArrayList<Reservation> reservations = Reservation.loadByIdClient(E_Statut.Reserve, id);
		for(int i = 0 ; i< reservations.size() ; i++)
			pan.ajouter(reservations.get(i));
		pan.initTotal();
	}
	
	/**
	 * Vide le panier du client.
	 */
	public void viderPanier() {
		pan.vider();
	}
	
	/**
	 * Paye les réservations du panier.
	 * @throws Exception 
	 */
	public void payerPanier() throws Exception{
		pan.payer();
	}
	
	/**
	 * Charge le client à partir de la base de données.
	 */
	public void charger(){
		Client temp =  clientDAO.find(id);
		
		this.dateNaissance = temp.dateNaissance;
		this.nom = temp.nom;
		this.prenom = temp.prenom;
	}
	
	/**
	 * Inscrit le client dans la base de données.
	 * @return True si l'inscription c'est effectué.
	 * @throws Exception Erreur lors de la création d'un client dans la base de données.
	 */
	@Override
	public boolean inscription() throws Exception{
		if(clientDAO.create(this)){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Récupère les information du client de la base de données.
	 * @return True si la connexion c'est effectué.
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
	
	/**
	 * Déconnecte le client, rénitialise ses attributs.
	 */
	@Override
	public void deconnexion(){
		setNom("");
		setPrenom("");
		setPasswd("");
		setDateNaissance(null);
		setId(0);		
		pan = null;
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

	//[end]Accesseurs
}
