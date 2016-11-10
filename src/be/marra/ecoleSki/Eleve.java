package be.marra.ecoleSki;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.EleveDAO;

public class Eleve extends Personne {
	
	//[start]Attributs
	
	private int 	id;
	private boolean assurance;
	
	//Base de données//
	private AbstractDAOFactory adf;
	EleveDAO eleveDAO;

	//[end]
	
	//[start]Constructeurs
	
	public Eleve() {
		super();
		this.assurance = false;
		
		initDB();
	}
	
	public Eleve(String nom, String prenom, int jour, int mois, int annee) {
		super(nom, prenom, jour, mois, annee);
		this.assurance = false;
		
		initDB();
	}

	public Eleve(String nom, String prenom, int jour, int mois, int annee, boolean assurance) {
		super(nom, prenom, jour, mois, annee);
		this.assurance = assurance;
		
		initDB();
	}

	//[end]
	
	//[start]Méthode
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		eleveDAO = (EleveDAO)adf.getEleveDAO();
	}
	
	/**
	 * Fait appel à la classe DAO pour enregister un eleve dans la base de données.
	 */
	public void insertIntoDB(){
		eleveDAO.create(this);
		eleveDAO.getId(this);
	}
	
	public void deleteFromDB(){
		eleveDAO.delete(this);
	}
	
	//[end]
	
	//[start]Accesseurs
	
	public boolean isAssurance()
	{
		return this.assurance;
	}

	public void setAssurance(boolean assurance)
	{
		this.assurance = assurance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//[end]
}
