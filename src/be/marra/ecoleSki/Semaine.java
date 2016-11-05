package be.marra.ecoleSki;

import java.time.LocalDate;
import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.DAO;

public class Semaine {

	//-------------------------Attributs-------------------------//
	
	private int 		id;
	private LocalDate 	dateDebut;
	private LocalDate 	dateFin;
	private String 	descriptif;
	private boolean congeScolaire;
	
	//Base de données//
		private AbstractDAOFactory adf;
		DAO<Semaine> semaineDAO;

	//-------------------------Constructeurs-------------------------//
	
	public Semaine() {
		this.setDateDebut(null);
		this.setDateFin(null);
		this.descriptif 	= null;
		this.congeScolaire 	= false;
		
		initDB();
	}
	
	public Semaine(LocalDate dateDebut, LocalDate dateFin, String descriptif, boolean congeScolaire) {
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.descriptif 	= descriptif;
		this.congeScolaire 	= congeScolaire;
		
		initDB();
	}

	
	//-------------------------Méthodes-------------------------//
	
	/**
	 * Vérifie si les dates sont valides.
	 */
	public void checkDate(){

	}
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		semaineDAO = adf.getSemaineDAO();
	}

	
	//-------------------------Accesseurs-------------------------//
	
	
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

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
