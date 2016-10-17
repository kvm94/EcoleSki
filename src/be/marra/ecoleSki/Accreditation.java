package be.marra.ecoleSki;

public class Accreditation {
	
	//-------------------------Enumérations-------------------------//

	protected static enum E_Categorie{
	    Enfant,  Adulte;
	}
	  
	protected static enum E_Sport
	{
	    Ski,  Snowboard,  Telemark,  SkiFond;
	}
	
	
	//-------------------------Attributs-------------------------//
	
	E_Categorie cat;
	E_Sport 	sport;
  
	
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
}
