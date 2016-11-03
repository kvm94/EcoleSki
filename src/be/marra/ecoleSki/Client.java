package be.marra.ecoleSki;

public class Client extends Personne {
	
	//-------------------------Attributs-------------------------//
	
	private int 	id;
	private String	passwd;
	private Panier 	pan;
	
	//Base de données//
		private AbstractDAOFactory adf;
		DAO<Client> clientDAO;
	
	  
	//-------------------------Constructeurs-------------------------//
	
	public Client(){
	    super();
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
