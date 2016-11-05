package be.marra.ecoleSki;

import java.sql.Time;
import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.DAO;

public class Reservation {
	//-------------------------Enumération-------------------------//
	
	public static enum E_Statut
	{
		Paye,  Reserve;
	}
	
	//-------------------------Attributs-------------------------//
	
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
	DAO<Reservation> resDAO;
	
	//-------------------------Constructeurs-------------------------//
	
	public Reservation()
	{
		this.statut = null;
		this.heure = null;
		this.prix = 0;
		this.eleve = null;
		this.cours = null;
		this.semaine = null;
		this.client = null;
		
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

	
	//-------------------------Méthodes-------------------------//
	
	public void appliquerReduction() {}

	public void afficher() {}
	
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		resDAO = adf.getReservationDAO();
	}
	
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
}
