package be.marra.ecoleSki;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.SemaineDAO;

public class Semaine {

	//[start]Attributs
	
	private int 		id;
	private LocalDate 	dateDebut;
	private LocalDate 	dateFin;
	private String 		descriptif;
	private boolean 	congeScolaire;
	
	//Base de données//
	private AbstractDAOFactory 	adf;
	private SemaineDAO 			semaineDAO;

	//[end]Attributs
		
	//[start]Constructeurs
	
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

	//[end]Constructeurs
	
	//[start]Méthodes
	
	/**
	 * Initialise les congées scolaire.
	 */
	private static void initConge(){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		SemaineDAO semaineDAO = (SemaineDAO)adf.getSemaineDAO();
		
		ArrayList<Semaine> semaines = loadSemainesFromDB();
		long tempDebut;
		long tempFin;
		long dateDebut;
		long dateFin;
		
		for(int i = 0 ; i< semaines.size() ; i++){
			dateDebut = semaines.get(i).getDateDebut().toEpochDay();
			dateFin = semaines.get(i).getDateFin().toEpochDay();
			//Noël 2016
			tempFin = LocalDate.of(2017, 1, 8).toEpochDay();
			tempDebut = LocalDate.of(2016, 12, 24).toEpochDay();
			if(dateDebut >= tempDebut && dateFin < tempFin){
				semaines.get(i).setCongeScolaire(true);
				semaines.get(i).setDescriptif("Noël");
				semaineDAO.update(semaines.get(i));
			}
			else{
				//Carnaval 2017
				tempFin = LocalDate.of(2017, 3, 5).toEpochDay();
				tempDebut = LocalDate.of(2017, 2, 25).toEpochDay();
				if(dateDebut >= tempDebut && dateFin < tempFin){
					semaines.get(i).setCongeScolaire(true);
					semaines.get(i).setDescriptif("Carnaval");
					semaineDAO.update(semaines.get(i));
				}
				else{
					//Paquer 2017
					tempDebut = LocalDate.of(2017, 4, 1).toEpochDay();
					tempFin = LocalDate.of(2017, 4, 17).toEpochDay();
					if(dateDebut >= tempDebut && dateFin < tempFin){
						semaines.get(i).setCongeScolaire(true);
						semaines.get(i).setDescriptif("Pâques");
						semaineDAO.update(semaines.get(i));
					}
					else{
						//Paquer 2017
						tempDebut = LocalDate.of(2017, 7, 1).toEpochDay();
						tempFin = LocalDate.of(2017, 8, 31).toEpochDay();
						if(dateDebut >= tempDebut && dateFin < tempFin){
							semaines.get(i).setCongeScolaire(true);
							semaines.get(i).setDescriptif("Grandes vacances");
							semaineDAO.update(semaines.get(i));
						}
						else{
							semaines.get(i).setCongeScolaire(false);
							semaines.get(i).setDescriptif("");
							semaineDAO.update(semaines.get(i));
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * Charge une semaine de la base de données.
	 */
	public void charger(){
		Semaine temp = semaineDAO.find(id);
		
		this.dateDebut = temp.dateDebut;
		this.dateFin = temp.dateFin;
		this.congeScolaire = temp.congeScolaire;
		this.descriptif = temp.descriptif;
	}
	
	/**
	 * Vérifie si la date données correspond pour une réservation en fonction des congées.
	 * @return True si la date est valide.
	 */
	public boolean checkDate(){
		boolean check = false;
		//1 Semaine en long;
		long semaine = (LocalDate.of(2016, 1, 7).toEpochDay())-(LocalDate.of(2016, 1, 1).toEpochDay());
		
		if(isCongeScolaire()){
			if((dateDebut.toEpochDay() - LocalDate.now().toEpochDay()) <= semaine)
				check = true;
		}
		else{
			if((dateDebut.toEpochDay() - LocalDate.now().toEpochDay()) <= 4*semaine)
				check = true;
		}
		return check;
	}
	
	/**
	 * Initialise l'accès à la base de données.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		semaineDAO = (SemaineDAO)adf.getSemaineDAO();
	}
	
	/**
	 * Crée toutes les semaine du samedi 03/12/2016 au dimanche 07/05/2017 dans la base de données.
	 */
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
			
			semaineDAO.create(sem);
			
			date = date.plusDays(1);
		}	
		//Initialise les congé et les descriptifs.
		initConge();

	}
	
	/**
	 * Affiche les semaines dans la base de données.
	 * @throws SQLException
	 */
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
	
	/**
	 * Génére une liste de semaine à partir de la base de données.
	 * @return
	 */
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
	
	/**
	 * Surcharge de toString
	 * @return String
	 */
	@Override
	public String toString(){
		String s = "";
		
		s += dateDebut.toString() + " -> " + dateFin.toString(); 
		
		return s;
	}

	//[end]Méthodes
	
	//[start]Accesseurs
	
	
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

	//[end]Accesseurs
}
