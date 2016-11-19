package be.marra.ecoleSki;

public abstract class Utilisateur extends Personne {
	
	//[start]Attributs
	
	protected String	passwd;
	  
	//[end]Attributs
	
	//[start]Constructeur
	
	public Utilisateur(String nom, String prenom, int jour, int mois, int annee){
		super(nom, prenom, jour, mois, annee);
	}
	
	//[end]Constructeur
	
	//[start]Méthodes
	
	abstract public boolean inscription() throws Exception;
	abstract public boolean connexion();
	abstract public void deconnexion();
	
	//[end]Méthodes
	
	//[start]Accesseurs

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	//[end]Accesseurs

}
