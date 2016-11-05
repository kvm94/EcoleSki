package be.marra.ecoleSki;

public abstract class Utilisateur extends Personne {
	
	//-------------------------Attributs-------------------------//
	
	private String	passwd;
	  
	public Utilisateur(String nom, String prenom, int jour, int mois, int annee){
		super(nom, prenom, jour, mois, annee);
	}
	
	//-------------------------M�thodes-------------------------//
	
	abstract public boolean inscription();
	abstract public void connexion();
	
	//-------------------------Accesseurs-------------------------//

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
