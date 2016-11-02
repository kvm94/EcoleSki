package be.marra.ecoleSki;

public class Accreditation {
	
	//-------------------------Enumérations-------------------------//

	protected static enum E_Categorie{
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
	  
	protected static enum E_Sport
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
	
	
	//-------------------------Attributs-------------------------//
	
	private int 		id;
	private E_Categorie cat;
	private E_Sport 	sport;
	
	//Base de données//
	private AbstractDAOFactory adf;
	DAO<Accreditation> accreditationDAO;
	
	
	//-------------------------Constructeurs-------------------------//
	
	public Accreditation()
	{
		this.cat 	= null;
		this.sport 	= null;
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
	
	//-------------------------Méthodes-------------------------//
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		accreditationDAO = adf.getAccreditationDAO();
	}
	
	public void init(int id){
		Accreditation a = accreditationDAO.find(id);
		this.id = id;
		this.sport = a.sport;
		this.cat = a.cat;
	}
	
	public void init(){
		Accreditation a = accreditationDAO.find(id);
		this.sport = a.sport;
		this.cat = a.cat;
	}
	
	
	//-------------------------Accesseurs-------------------------//
	
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
}
