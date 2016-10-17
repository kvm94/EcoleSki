package be.marra.ecoleSki;

public class Moniteur extends Personne {
	
	//-------------------------Attributs-------------------------//
	
	private Accreditation acre;
	
	
	//-------------------------Constructeurs-------------------------//
	
	public Moniteur() {
		super();
		this.acre = null;
	}
	
	public Moniteur(String nom, String prenom, int jour, int mois, int annee, Accreditation acre) {
		super(nom, prenom, jour, mois, annee);
		this.acre = acre;
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
}
