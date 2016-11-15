package be.marra.ecoleSki;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.EleveDAO;

public class Eleve extends Personne {
	
	//[start]Attributs
	
	private int 	id;
	private boolean assurance;
	
	//Base de données//
	private AbstractDAOFactory 	adf;
	private EleveDAO 			eleveDAO;

	//[end]Attributs
	
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

	//[end]Constructeurs
	
	//[start]Méthode
	
	/**
	 * initialise l'accès à la base de données.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		eleveDAO = (EleveDAO)adf.getEleveDAO();
	}
	
	/**
	 * Ajoute un élève à la base de données.
	 */
	public void ajouter(){
		eleveDAO.create(this);
		eleveDAO.getId(this);
	}
	
	/**
	 * Supprime un élève de la base de données.
	 */
	public void supprimer(){
		eleveDAO.delete(this);
	}
	
	/**
	 * Charge un élève à partir de la base de données.
	 */
	public void charger(){
		Eleve temp = eleveDAO.find(id);
		
		this.assurance = temp.assurance;
		this.nom = temp.nom;
		this.prenom = temp.prenom;
		this.dateNaissance = temp.dateNaissance;
		
	}
	
	//[end]Méthodes
	
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
	
	//[end]Accesseurs
}
