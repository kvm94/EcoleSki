package be.marra.ecoleSki;

import java.sql.Time;
import be.marra.ecoleSki.Accreditation.E_Categorie;
import be.marra.ecoleSki.Accreditation.E_Sport;

public class Cours {
	
	//-------------------------Enumérations-------------------------//
	
	protected static enum E_Niveaux
	{
		PetitSpirou,  Bronze,  Argent,  Or,  Platine,  Diamant,  nv1,  nv1_nv4,  nv2_nv4,  Competition,  HorsPiste;
	}
	
	
	//-------------------------Attributs-------------------------//
	
	private E_Categorie categorie;
	private E_Sport 	sport;
	private E_Niveaux 	niveaux;
	private Time 		heure;
	private double 		prix;
	private int 		minEleve;
	private int 		maxEleve;
	private boolean 	collectif;

	//-------------------------Constructeurs-------------------------//
	
	public Cours() {
		this.categorie = null;
		this.sport = null;
		this.niveaux = null;
		this.heure = null;
		this.prix = 0;
		this.minEleve = 0;
		this.maxEleve = 0;
		this.collectif = false;
	}
	
	public Cours(E_Categorie categorie, E_Sport sport, E_Niveaux niveaux, Time heure, double prix, int minEleve, int maxEleve, boolean collectif) {
		this.categorie = categorie;
		this.sport = sport;
		this.niveaux = niveaux;
		this.heure = heure;
		this.prix = prix;
		this.minEleve = minEleve;
		this.maxEleve = maxEleve;
		this.collectif = collectif;
	}

	
	//-------------------------Méthodes-------------------------//
	
	/**
	 * Vérifie si l'accréditation requise pour le cours est ok.
	 * @param a
	 * @return true si l'accréditation est valable.
	 */
	public boolean checkAccreditation(Accreditation a)
	{
		if ((this.categorie == a.getCat()) && (this.sport == a.getSport())) {
			return true;
		}
		return false;
	}

	/**
	 * Vérifie si les conditions sont remplies et adapte le prix pour un cours particulier.
	 */
	public void initParticulier() {}

	/**
	 * Vérifie si les conditions sont remplies et adapte le prix pour un cours collectif.
	 */
	public void initCollectif() {}

	/**
	 * Adapte le prix en fonction du sport et du niveaux.
	 */
	public void initPrix() {}

	
	//-------------------------Accesseurs-------------------------//
	
	public E_Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(E_Categorie categorie) {
		this.categorie = categorie;
	}

	public E_Sport getSport() {
		return sport;
	}

	public void setSport(E_Sport sport) {
		this.sport = sport;
	}

	public E_Niveaux getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(E_Niveaux niveaux) {
		this.niveaux = niveaux;
	}

	public Time getHeure() {
		return heure;
	}

	public void setHeure(Time heure) {
		this.heure = heure;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getMinEleve() {
		return minEleve;
	}

	public void setMinEleve(int minEleve) {
		this.minEleve = minEleve;
	}

	public int getMaxEleve() {
		return maxEleve;
	}

	public void setMaxEleve(int maxEleve) {
		this.maxEleve = maxEleve;
	}

	public boolean isCollectif() {
		return collectif;
	}

	public void setCollectif(boolean collectif) {
		this.collectif = collectif;
	}

	

}
