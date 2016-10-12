package be.marra.ecoleSki;
import java.util.Date;

@SuppressWarnings("deprecation")
public abstract class Personne {
	
	/**Attributs**/
	
	String 	nom;
	String 	prenom;
	Date 	dateNaissance;
	
	/**Construteur**/
	
	public Personne(String nom, String prenom, int jour, int mois, int annee){
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = new Date(annee, mois, jour);
	}
	
	/**Méthodes**/
	
	/* Calcul l'age.
	 * return : Renvoi l'age de la personne.
	 */
	public int age(){
		int age;
		Date now = new Date();
		age = now.getYear() - dateNaissance.getYear();
		if(now.getMonth() < dateNaissance.getMonth() || (now.getMonth() == dateNaissance.getMonth() && now.getDay() < dateNaissance.getDay()))
			age--;

		return age;
	}
	
	/**Accesseurs**/
	
	public String getNom(){
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	
	
}
