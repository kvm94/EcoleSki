package be.marra.ecoleSki;

import java.util.Date;

@SuppressWarnings("deprecation")
public abstract class Personne
{
	
	//-------------------------Attributs-------------------------//
	
	private String nom;
	private String prenom;
	private Date dateNaissance;


	//-------------------------Constructeurs-------------------------//
	
	public Personne() {
		this.nom = null;
		this.prenom = null;
		this.dateNaissance = new Date();
	}
	
	public Personne(String nom, String prenom, int jour, int mois, int annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = new Date(annee, mois, jour);
	}

	//-------------------------Méthodes-------------------------//
	
	/**
	 * Calcule l'age de la personne.
	 * @return int age
	 */
	public int age()
	{
		Date now = new Date();
		int age = now.getYear() - this.dateNaissance.getYear();
		if ((now.getMonth()  == this.dateNaissance.getMonth()) || ((now.getMonth() == this.dateNaissance.getMonth()) && (now.getDay() == this.dateNaissance.getDay()))) {
			age--;
		}
		return age;
	}

	
	//-------------------------Accesseurs-------------------------//
	
	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return this.prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public Date getDateNaissance()
	{
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance)
	{
		this.dateNaissance = dateNaissance;
	}
}
