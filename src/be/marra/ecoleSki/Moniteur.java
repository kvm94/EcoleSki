package be.marra.ecoleSki;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.DAO;

public class Moniteur extends Personne {
	
	//-------------------------Attributs-------------------------//
	
	private Accreditation acre;
	private int id;
	private String passwd;

	//Base de données//
	private AbstractDAOFactory adf;
	DAO<Moniteur> moniteurDAO;
	
	//-------------------------Constructeurs-------------------------//
	
	public Moniteur() {
		super();
		this.acre = null;
		
		initDB();
	}
	
	public Moniteur(String nom, String prenom, int jour, int mois, int annee, Accreditation acre) {
		super(nom, prenom, jour, mois, annee);
		this.acre = acre;
		
		initDB();
	}
	
	public Moniteur(String nom, String prenom, int jour, int mois, int annee, Accreditation acre, int id) {
		super(nom, prenom, jour, mois, annee);
		this.acre = acre;
		this.setId(id);
		
		initDB();
	}
	
	//-------------------------Méthodes-------------------------//
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		moniteurDAO = adf.getMoniteurDAO();
	}
	
	public void setIdAcre(int id){
		acre.setId(id);
	}

	
	//-------------------------Accesseurs-------------------------//
	
	public Accreditation getAcre()
	{
		return this.acre;
	}

	public void setAcre(Accreditation acre)
	{
		this.acre = acre;
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
