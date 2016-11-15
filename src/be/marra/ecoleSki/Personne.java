package be.marra.ecoleSki;

import java.time.LocalDate;


public abstract class Personne
{
	
	//[start]Attributs
	
	protected String 	nom;
	protected String 	prenom;
	protected LocalDate dateNaissance;

	//[end]Attributs

	//[start]Constructeurs
	
	public Personne() {
		this.nom = null;
		this.prenom = null;
		this.setDateNaissance(null);
	}
	
	public Personne(String nom, String prenom, int jour, int mois, int annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.setDateNaissance(LocalDate.of(annee, mois, jour));
	}

	//[end]Constructeurs
	
	//[start]Méthode
	
	/**
	 * Calcule l'age de la personne.
	 * @return int L'age de la personne.
	 */
	public int age()
	{
		LocalDate now = LocalDate.now();
		int age = now.getYear() - this.dateNaissance.getYear();
		if ((now.getMonth()  == this.dateNaissance.getMonth()) || ((now.getMonth() == this.dateNaissance.getMonth()) && (now.getDayOfMonth()) == this.dateNaissance.getDayOfMonth())) {
			age--;
		}
		return age;
	}
	
	//[end]Méthode
	
	//[start]Accesseurs
	
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

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	//[end]Accesseurs
}
