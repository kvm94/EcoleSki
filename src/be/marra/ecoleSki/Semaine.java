package be.marra.ecoleSki;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.SemaineDAO;

public class Semaine {

	//-------------------------Attributs-------------------------//
	
	private int 		id;
	private LocalDate 	dateDebut;
	private LocalDate 	dateFin;
	private String 	descriptif;
	private boolean congeScolaire;
	
	//Base de données//
		private AbstractDAOFactory adf;
		SemaineDAO semaineDAO;

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
	
	public void charger(){
		Semaine temp = semaineDAO.find(id);
		
		this.dateDebut = temp.dateDebut;
		this.dateFin = temp.dateFin;
		this.congeScolaire = temp.congeScolaire;
		this.descriptif = temp.descriptif;
	}
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		semaineDAO = (SemaineDAO)adf.getSemaineDAO();
	}
	
	public static void initSemaine(){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		SemaineDAO semaineDAO = (SemaineDAO)adf.getSemaineDAO();
		Semaine sem = new Semaine();
		LocalDate date = LocalDate.of(2016,12,3);
		
		// du samedi 03/12/2016 au dimanche 07/05/2017
		
		/*Les périodes scolaires sont :
			•	Du 24/12/2016 => 07/01/2017 (Noël / Nouvel an)
			•	Du 25/02/2017 au 04/03/2017 (Carnaval)
			•	Du 01/04/2017 au 15/04/2017 (Pâques)
		*/
		
		while(date.compareTo(LocalDate.of(2017, 5, 7)) < 0){
			sem.setDateDebut(date);
			date = date.plusDays(6);
			sem.setDateFin(date);
			
			//System.out.println(sem.getDateDebut() + " => " + sem.getDateFin());
			semaineDAO.create(sem);
			
			date = date.plusDays(1);
		}	

	}
	
	public static void afficherSemaineDB() throws SQLException{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		SemaineDAO semaineDAO = (SemaineDAO)adf.getSemaineDAO();
		ArrayList<Semaine> semaines= semaineDAO.find();
		int cpt=0;
		
		while(cpt < semaines.size()){
			System.out.println(semaines.get(cpt).getDateDebut() + " => " + semaines.get(cpt).getDateFin());
			cpt++;
		}
	}
	
	public static ArrayList<Semaine> loadSemainesFromDB(){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		SemaineDAO semaineDAO = (SemaineDAO)adf.getSemaineDAO();
		ArrayList<Semaine> semaines = new ArrayList<Semaine>();
		semaines= semaineDAO.find();
		int i=0;
		
		//Si la date est passé on ne la prend pas.
		while(i < semaines.size()){
			if(semaines.get(i).getDateFin().compareTo(LocalDate.now()) <=0)
				semaines.remove(i);
			i++;
		}
		
		return semaines;
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
