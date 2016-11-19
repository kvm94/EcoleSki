package be.marra.ecoleSki;

import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.Accreditation_MoniteurDAO;
import be.marra.ecoleSki.DAO.MoniteurDAO;
import be.marra.ecoleSki.DAO.PAccreditation_Moniteur;

public class Moniteur extends Utilisateur {

	//[start]Attributs

	private ArrayList<Accreditation>	accreditations;
	private int 						id;

	//Base de données//
	private AbstractDAOFactory 	adf;
	private MoniteurDAO 		moniteurDAO;
	//Pour l'éclatement.
	private Accreditation_MoniteurDAO accreMonDAO;

	//[end]Attributs

	//[start]Constructeurs

	public Moniteur() {
		super(null, null, 1, 1, 1);
		this.accreditations = new ArrayList<Accreditation>();

		initDB();
	}

	public Moniteur(String nom, String prenom, int jour, int mois, int annee) {
		super(nom, prenom, jour, mois, annee);
		this.accreditations = new ArrayList<Accreditation>();

		initDB();
	}

	public Moniteur(String nom, String prenom, int jour, int mois, int annee, int id) {
		super(nom, prenom, jour, mois, annee);
		this.accreditations = new ArrayList<Accreditation>();
		this.setId(id);

		initDB();
	}

	//[end]Constructeurs

	//[start]Méthodes

	/**
	 * Initialise l'accès à la base de données.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		moniteurDAO = (MoniteurDAO)adf.getMoniteurDAO();
		accreMonDAO = (Accreditation_MoniteurDAO)adf.getAccreditation_MoniteurDAO();
	}
	
	/**
	 * Inscrit le moniteur dans la base de données.
	 * @return True si l'inscription c'est effectué.
	 * @throws Exception Erreur lors de l'inscription dans la base de données.
	 */
	@Override
	public boolean inscription() throws Exception{
		if(moniteurDAO.create(this))
			return true;
		else
			return false;
	}

	/**
	 * Connecte l'utilisateur et le charger à partir de la base de données.
	 * @return True si la connexion c'est effectué.
	 */
	@Override
	public boolean connexion(){
		boolean check = false;
		ArrayList<Moniteur> temp;

		temp = moniteurDAO.find(nom, prenom, passwd);

		if(temp.size() > 0){
			setNom(nom);
			setPrenom(prenom);
			setPasswd(passwd);
			setDateNaissance(temp.get(0).getDateNaissance());
			setId(temp.get(0).getId());
			check = true;
		}
		else
			check = false;

		return check;
	}
	
	/**
	 * Déconnecte le moniteur, rénitialise ses attributs.
	 */
	@Override
	public void deconnexion(){
		setNom("");
		accreditations = null;
		setPrenom("");
		setPasswd("");
		setDateNaissance(null);
		setId(0);		
	}

	/**
	 * Ajoute un accréditation.
	 * @param a L'accréditation à ajouter.
	 * @throws Exception 
	 */
	public void ajoutAccreditation(Accreditation a) throws Exception{
		try{
			PAccreditation_Moniteur temp; 

			a.ajouter();

			temp = new PAccreditation_Moniteur(a.getId(), id);
			accreMonDAO.create(temp);
			accreditations.add(a);
		}
		catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * Supprime un accréditation.
	 * @param index
	 */
	public void supprimerAccreditation(int index){
		PAccreditation_Moniteur temp = new 	PAccreditation_Moniteur(accreditations.get(index).getId(), id);

		//Ne supprimer le accréditation que si il n'y a pas d'autre moniteur le concernant.
		if(accreMonDAO.nbrAccreMoniteur(accreditations.get(index)) == 1)
			accreditations.get(index).supprimer();
		
		accreMonDAO.delete(temp);

		accreditations.remove(index);
		
		
	}

	/**
	 * Initialise la liste des accréditations.
	 * @throws Exception Erreur lors de l'initialisation des accréditations!
	 * @exception Exception  Erreur lors de l'initialisation des accréditations!
	 */
	public void initAccreditations() throws Exception{
		try{
			ArrayList<Integer> tabId = accreMonDAO.findIdAccreditation(id);
			Accreditation a;

			accreditations.clear();

			for(int i=0; i< tabId.size() ; i++){
				a = new Accreditation();
				a.charger(tabId.get(i));
				accreditations.add(a);
			}
		}
		catch(Exception ex){
			throw new Exception("Erreur lors de l'initialisation des accréditations!");
		}
	}

	/**
	 * Génére la liste des réservations que le moniteur peut prendre.
	 * @param id L'id du moniteur. 0 si on cherche les réservations qui n'ont pas de moniteur.
	 * @return La liste des réservations.
	 * @throws Exception Erreur lors de la vérifications des réservations possibles pour le moniteur!
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Reservation> checkReservations(int id) throws Exception{
		
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
		int flag = 0;
		boolean first = true;
		
		try{
			this.initAccreditations();
			//Charge la liste complète des réservations.
			listeReservation = Reservation.loadByMonitor(id);
			for(int i=0; i< listeReservation.size() ; i++){
				//Vérifie si il y a le nombre minimum de réservation pour ce cours.
				if(Reservation.getNbrReservationByCours(listeReservation.get(i).getCours(), listeReservation.get(i).getSemaine(),
						listeReservation.get(i).getHeure().getHours()) >= listeReservation.get(i).getCours().getMinEleve()){

					if(first){

						//Vérifie si le moniteur a l'accréditation requise.
						if(listeReservation.get(i).getCours().checkAccreditation(this.getAccreditations())){
							reservations.add(listeReservation.get(i));
							first = false;
						}

					}else if(listeReservation.get(i).getCours().getId() != reservations.get(flag).getCours().getId()){

						if(listeReservation.get(i).getCours().checkAccreditation(this.getAccreditations())){
							reservations.add(listeReservation.get(i));
							flag++;

						}
					}
				}
			}
		}
		catch(Exception ex){
			throw new Exception("Erreur lors de la vérifications des réservations possibles pour le moniteur!");
		}


		return reservations;
	}
	

	//[end]Méthodes

	//[start]Accesseurs

	public Accreditation getAcre(int pos)
	{
		return accreditations.get(pos);
	}

	public void addAccre(Accreditation accre)
	{
		accreditations.add(accre);
	}

	public ArrayList<Accreditation> getAccreditations(){
		return accreditations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//[end]Accesseurs
}
