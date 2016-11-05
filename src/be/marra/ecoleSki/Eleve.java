package be.marra.ecoleSki;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.DAO;

public class Eleve extends Personne {
	
	//-------------------------Attributs-------------------------//
	
	private int 	id;
	private boolean assurance;
	
	//Base de données//
	private AbstractDAOFactory adf;
	DAO<Eleve> eleveDAO;

	
	//-------------------------Constructeurs-------------------------//
	
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

	//-------------------------Méthode-------------------------//
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		eleveDAO = adf.getEleveDAO();
	}
	
	
	//-------------------------Accesseurs-------------------------//
	
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
}
