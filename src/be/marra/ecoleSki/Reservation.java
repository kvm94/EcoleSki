package be.marra.ecoleSki;

import java.sql.Time;
import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.ReservationDAO;

public class Reservation {
	//[start]Enumération
	
	public static enum E_Statut
	{
		Reserve(0), Paye(1);
		
		private final int value;
		
		private E_Statut(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	//[end]
	
	//[start]Attributs
	
	private int 		id;
	private E_Statut 	statut;
	private Time 		heure;
	private double 		prix;
	private Eleve 		eleve;
	private Cours 		cours;
	private Semaine 	semaine;
	private Client		client;
	
	//Base de données//
	private AbstractDAOFactory adf;
	private ReservationDAO resDAO;
	
	//[end]
	
	//[start]Constructeurs
	
	public Reservation()
	{
		this.statut = null;
		this.heure = null;
		this.prix = 0;
		this.eleve = new Eleve();
		this.cours = new Cours();
		this.semaine = new Semaine();
		this.client =  new Client();
		
		initDB();
	}
	
	public Reservation(E_Statut statut, Time heure, double prix)
	{
		this.statut = statut;
		this.heure = heure;
		this.prix = prix;
		this.eleve = new Eleve();
		this.cours = new Cours();
		this.semaine = new Semaine();
		this.client =  new Client();
		
		initDB();
	}

	public Reservation(E_Statut statut, Time heure, double prix, Eleve eleve, Cours cours, Semaine semaine, Client client)
	{
		this.statut = statut;
		this.heure = heure;
		this.prix = prix;
		this.eleve = eleve;
		this.cours = cours;
		this.semaine = semaine;
		this.client = client;
		
		initDB();
	}

	//[end]
	
	//[start]Méthodes
	
	public void appliquerReduction() {}

	public void afficher() {}
	
	/**
	 * Initialise l'accès à la base de données.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		resDAO = (ReservationDAO)adf.getReservationDAO();
	}
	
	public void insertIntoDB(){
		cours.insertIntoDB();
		eleve.insertIntoDB();
		resDAO.create(this);
	}
	
	public void deleteFromDB(){
		//Ne supprimer le cours que si il n'y a pas d'autre réservation le concernant.
		if(resDAO.nbrResCours(cours) == 1)
			cours.deleteFromDB();
		eleve.deleteFromDB();
		resDAO.delete(this);
	}
	
	public void updateIntoDB(){
		resDAO.update(this);
	}
	
	/**
	 * Charge la liste des réservations par rapport au statut de payement d'un client.
	 * @param statut Payé ou Réservé.
	 * @param id Id du client.
	 * @return Liste de réservations correspondantes.
	 */
	public static ArrayList<Reservation> loadByIdClient(E_Statut statut , int id){
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ReservationDAO resDAO = (ReservationDAO)adf.getReservationDAO();
		
		list = resDAO.find(statut.getValue(), id);
		
		for(int i =  0 ; i< list.size() ; i++){
			list.get(i).getClient().charger();
			list.get(i).getCours().charger();
			list.get(i).getEleve().charger();
			list.get(i).getSemaine().charger();
		}

		return list;
	}
	
	
	//[end]
	
	//[start]Accesseurs
	
	public void setIdClient(int id){
		client.setId(id);
	}
	
	public void setIdSemaine(int id){
		semaine.setId(id);
	}
	
	public void setIdEleve(int id){
		eleve.setId(id);
	}
	
	public void setIdCours(int id){
		cours.setId(id);
	}
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	//[end]
}
