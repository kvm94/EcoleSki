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
