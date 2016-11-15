package be.marra.ecoleSki;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.AccreditationDAO;

public class Accreditation {
	
	//[start]Enumérations

	public static enum E_Categorie{
	    Enfant(0),  
	    Adulte(1);
		
		private final int value;
		
		private E_Categorie(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	  
	public static enum E_Sport
	{
	    Ski(0),
	    Snowboard(1),
	    Telemark(2),
	    SkiFond(3);
		
		private final int value;
		
		private E_Sport(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	//[end]Enumérations
	
	//[start]Attributs
	
	private int 		id;
	private E_Categorie cat;
	private E_Sport 	sport;
	
	//Base de données//
	private AbstractDAOFactory 	adf;
	private AccreditationDAO 	accreditationDAO;
	
	//[end]Attributs
	
	//[start]Constructeurs
	
	public Accreditation()
	{
		this.cat 	= null;
		this.sport 	= null;
		initDB();
	}
	
	public Accreditation(E_Categorie cat, E_Sport sport)
	{
		this.cat 	= cat;
		this.sport 	= sport;
		
		initDB();
	}
	
	public Accreditation(int id, E_Categorie cat, E_Sport sport)
	{
		this.id 	= id;
		this.cat 	= cat;
		this.sport 	= sport;
		
		initDB();
	}
	
	//[end]Constructeurs
	
	//[start]Méthodes
	
	/**
	 * Initialise l'accès à la base de données.
	 * @return void
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		accreditationDAO = (AccreditationDAO)adf.getAccreditationDAO();
	}
	
	/**
	 * Ajoute une accréditation dans la base de données.
	 * @return void
	 */
	public void ajouter(){
		accreditationDAO.create(this);
		id = accreditationDAO.getId(this);
	}
	
	/**
	 * Supprime une accréditation de la base de données.
	 */
	public void supprimer(){
		accreditationDAO.delete(this);
		this.cat 	= null;
		this.sport 	= null;
	}
	
	/**
	 * Charge un accréditation à partir de la base de données.
	 * @param id L'id de l'accréditation à charger.
	 */
	public void charger(int id){
		Accreditation temp = new Accreditation();
		temp = accreditationDAO.find(id);
		
		this.cat = temp.getCat();
		this.sport = temp.getSport();
		this.id = id;
	}
	
	//[end]Méthodes
	
	//[start]Accesseurs
	
	public E_Categorie getCat()
	{
		return this.cat;
	}
  
	public void setCat(E_Categorie cat)
	{
		this.cat = cat;
	}
  
	public E_Sport getSport()
	{
		return this.sport;
	}
  
	public void setSport(E_Sport sport)
	{
		this.sport = sport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//[end]Accesseurs
}
