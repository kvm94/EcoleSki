package be.marra.ecoleSki;

import java.sql.Time;

public class Reservation {
	//-------------------------Enumération-------------------------//
	
	protected static enum E_Statut
	{
		Paye,  Reserve;
	}
	
	//-------------------------Attributs-------------------------//
	
	private E_Statut 	statut;
	private Time 		heure;
	private double 		prix;
	private Eleve 		eleve;
	private Cours 		cours;
	private Semaine 	semaine;

	
	//-------------------------Constructeurs-------------------------//
	
	public Reservation()
	{
		this.statut = null;
		this.heure = null;
		this.prix = 0;
		this.eleve = null;
		this.cours = null;
		this.semaine = null;
	}
	
	public Reservation(E_Statut statut, Time heure, double prix)
	{
		this.statut = statut;
		this.heure = heure;
		this.prix = prix;
		this.eleve = new Eleve();
		this.cours = new Cours();
		this.semaine = new Semaine();
	}

	public Reservation(E_Statut statut, Time heure, double prix, Eleve eleve, Cours cours, Semaine semaine)
	{
		this.statut = statut;
		this.heure = heure;
		this.prix = prix;
		this.eleve = eleve;
		this.cours = cours;
		this.semaine = semaine;
	}

	
	//-------------------------Méthodes-------------------------//
	
	public void appliquerReduction() {}

	public void afficher() {}

	
	//-------------------------Accesseurs-------------------------//
	
	public E_Statut getStatut()
	{
		return this.statut;
	}

	public void setStatut(E_Statut statut)
	{
		this.statut = statut;
	}

	public Time getHeure()
	{
		return this.heure;
	}

	public void setHeure(Time heure)
	{
		this.heure = heure;
	}

	public double getPrix()
	{
		return this.prix;
	}

	public void setPrix(double prix)
	{
		this.prix = prix;
	}


	public Eleve getEleve() {
		return eleve;
	}


	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}


	public Cours getCours() {
		return cours;
	}


	public void setCours(Cours cours) {
		this.cours = cours;
	}


	public Semaine getSemaine() {
		return semaine;
	}


	public void setSemaine(Semaine semaine) {
		this.semaine = semaine;
	}
}
