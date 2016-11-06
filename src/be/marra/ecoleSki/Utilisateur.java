package be.marra.ecoleSki;

public abstract class Utilisateur extends Personne {
	
	//-------------------------Attributs-------------------------//
	
	protected String	passwd;
	  
	public Utilisateur(String nom, String prenom, int jour, int mois, int annee){
		super(nom, prenom, jour, mois, annee);
	}
	
	//-------------------------Méthodes-------------------------//
	
	abstract public boolean inscription();
	abstract public boolean connexion();
	
	//-------------------------Accesseurs-------------------------//

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
