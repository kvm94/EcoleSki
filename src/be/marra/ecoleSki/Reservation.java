package be.marra.ecoleSki;

import java.sql.Time;
import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.ReservationDAO;

public class Reservation {
	//[start]Enum�ration
	
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
	
	//[end]Enum�ration
	
	//[start]Attributs
	
	private int 		id;
	private E_Statut 	statut;
	private Time 		heure;
	private double 		prix;
	private Eleve 		eleve;
	private Cours 		cours;
	private Semaine 	semaine;
	private Client		client;
	private Moniteur	moniteur;
	
	//Base de donn�es//
	private AbstractDAOFactory adf;
	private ReservationDAO resDAO;
	
	//[end]Attributs
	
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
		this.moniteur = new Moniteur();
		
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
		this.moniteur = new Moniteur();
		
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
		this.moniteur = new Moniteur();
		
		initDB();
	}

	//[end]Constrcteurs
	
	//[start]M�thodes

	/**
	 * Affiche le contenu de la r�servation.
	 * @return String Le text g�n�r� au format html de la r�servation.
	 */
	@SuppressWarnings("deprecation")
	public String afficher() {
		String text = "";
		
		text += "<table>"
				+ "<tr><td><b>NOM : </b></td>" + 				eleve.getNom()					+ "<td</td></tr>"
				+ "<tr><td><b>PRENOM : </b></td>" + 			eleve.getPrenom() 				+ "<td</td></tr>"
				+ "<tr><td><b>DATE DE NAISSANCE : </b></td>" + 	eleve.getDateNaissance() 		+ "<td</td></tr>"
				+ "<tr><td><b>CATEGORIE : </b></td>" + 			cours.getCategorie() 			+ "<td</td></tr>"
				+ "<tr><td><b>SPORT : </b></td>" + 				cours.getSport().toString() 	+ "<td</td></tr>"
				+ "<tr><td><b>NIVEAUX : </b></td>" + 			cours.getNiveaux().toString() 	+ "<td</td></tr>"
				+ "<tr><td><b>DATE : </b></td>" + 				semaine.getDateDebut() + " -> " + semaine.getDateFin() + "<td</td></tr>"
				+ "<tr><td><b>HEURE : </b></td>" + 				heure.getHours() + "h00"		+ "<td</td></tr>"
				+ "<tr><td><b>PRIX : </b></td>" + 				prix + "�"						+ "<td</td></tr>"
				+ "<tr><td><b>STATUS : </b></td>" + 			statut							+ "<td</td></tr>"
				+ "</table>";
		
		return text;
	}
	
	/**
	 * Initialise l'acc�s � la base de donn�es.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		resDAO = (ReservationDAO)adf.getReservationDAO();
	}
	
	/**
	 * Ajoute une r�servation � la base de donn�es.
	 * @throws Exception Ce cours est complet!
	 */
	@SuppressWarnings("deprecation")
	public void ajouter() throws Exception{
		cours.ajouter();
		if(resDAO.nbrResCours(cours, semaine, heure.getHours()) < cours.getMaxEleve()){
			eleve.ajouter();
			resDAO.create(this);
		}
		else
			throw new Exception("Ce cours est complet!");
	}
	
	/**
	 * Supprime une r�servation de la base de donn�es.
	 */
	public void supprimer(){
		//Ne supprimer le cours que si il n'y a pas d'autre r�servation le concernant.
		if(resDAO.nbrResCours(cours) == 1)
			cours.supprimer();
		//Ne supprimer l'�l�ve que si il n'y a pas d'autre r�servation le concernant.
		if(resDAO.nbrResEleve(eleve) == 1)
			eleve.supprimer();
		resDAO.delete(this);
	}
	
	/**
	 *  Met un jour l'id_moniteur de toutes les r�servations � la m�me semaine et heure que celle-ci.
	 * @throws Exception
	 */
	public void update() throws Exception{
		if(!resDAO.update(this))
			throw new Exception("Erreur lors de la mise � jour de la r�servation!");

	}
	
	/**
	 * Met un jour l'id_moniteur de toutes les r�servations � la m�me semaine et heure que celle-ci.
	 */
	public void updateIDMonitor(){
		resDAO.updateIdMonitor(this);
	}
	
	/**
	 * Charge la liste des r�servations par rapport au statut de payement d'un client.
	 * @param statut Pay� ou R�serv�.
	 * @param id Id du client.
	 * @return Liste de r�servations correspondantes.
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
	
	/**
	 * Charge la liste des r�servations par rappot � l'id d'un moniteur.
	 * @param id_moniteur Id du moniteur.
	 * @return Liste de r�servations correspondantes.
	 */
	public static ArrayList<Reservation> loadByMonitor(int id_moniteur){
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ReservationDAO resDAO = (ReservationDAO)adf.getReservationDAO();
		
		list = resDAO.findbyMonitor(id_moniteur);
		
		for(int i =  0 ; i< list.size() ; i++){
			list.get(i).getCours().charger();
			list.get(i).getSemaine().charger();
		}

		return list;
	}
	
	/**
	 * Renvoit le nombre de r�servations correspondante � un cours pr�cis.
	 * @param cours
	 * @param semaine 
	 * @param heure
	 * @return int
	 */
	public static int getNbrReservationByCours(Cours cours, Semaine semaine, int heure){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ReservationDAO resDAO = (ReservationDAO)adf.getReservationDAO();
		
		return resDAO.nbrResCours(cours, semaine, heure);
	}
	
	//[end]M�thodes
	
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
	
	public void setIdMoniteur(int id){
		moniteur.setId(id);
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

	public Moniteur getMoniteur() {
		return moniteur;
	}

	public void setMoniteur(Moniteur moniteur) {
		this.moniteur = moniteur;
	}
	
	//[end]Accesseurs
}
