package be.marra.ecoleSki;

import java.util.Date;

public class Semaine {

	//-------------------------Attributs-------------------------//
	
	private Date 	dateDebut;
	private Date 	dateFin;
	private String 	descriptif;
	private boolean congeScolaire;

	//-------------------------Constructeurs-------------------------//
	
	public Semaine() {
		this.dateDebut 		= null;
		this.dateFin 		= null;
		this.descriptif 	= null;
		this.congeScolaire 	= false;
	}
	
	public Semaine(Date dateDebut, Date dateFin, String descriptif, boolean congeScolaire) {
		this.dateDebut 		= dateDebut;
		this.dateFin 		= dateFin;
		this.descriptif 	= descriptif;
		this.congeScolaire 	= congeScolaire;
	}

	
	//-------------------------Méthodes-------------------------//
	
	/**
	 * Vérifie si les dates sont valides.
	 */
	public void checkDate(){

	}

	
	//-------------------------Accesseurs-------------------------//
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public boolean isCongeScolaire() {
		return congeScolaire;
	}
	public void setCongeScolaire(boolean congeScolaire) {
		this.congeScolaire = congeScolaire;
	}

}
