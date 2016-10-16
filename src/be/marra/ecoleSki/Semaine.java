package be.marra.ecoleSki;

import java.util.Date;

public class Semaine {
	
	private Date dateDebut;
	private Date dateFin;
	private String descriptif;
	private boolean congeScolaire;
	
	public Semaine(Date dateDebut, Date dateFin, String descriptif, boolean congeScolaire) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.descriptif = descriptif;
		this.congeScolaire = congeScolaire;
	}
	
	public void checkDate(){
		
	}
	
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
