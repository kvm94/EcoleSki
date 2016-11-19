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

	//Base de donn�es//
	private AbstractDAOFactory 	adf;
	private MoniteurDAO 		moniteurDAO;
	//Pour l'�clatement.
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

	//[start]M�thodes

	/**
	 * Initialise l'acc�s � la base de donn�es.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		moniteurDAO = (MoniteurDAO)adf.getMoniteurDAO();
		accreMonDAO = (Accreditation_MoniteurDAO)adf.getAccreditation_MoniteurDAO();
	}
	
	/**
	 * Inscrit le moniteur dans la base de donn�es.
	 * @return True si l'inscription c'est effectu�.
	 * @throws Exception Erreur lors de l'inscription dans la base de donn�es.
	 */
	@Override
	public boolean inscription() throws Exception{
		if(moniteurDAO.create(this))
			return true;
		else
			return false;
	}

	/**
	 * Connecte l'utilisateur et le charger � partir de la base de donn�es.
	 * @return True si la connexion c'est effectu�.
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
	 * D�connecte le moniteur, r�nitialise ses attributs.
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
	 * Ajoute un accr�ditation.
	 * @param a L'accr�ditation � ajouter.
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
	 * Supprime un accr�ditation.
	 * @param index
	 */
	public void supprimerAccreditation(int index){
		PAccreditation_Moniteur temp = new 	PAccreditation_Moniteur(accreditations.get(index).getId(), id);

		//Ne supprimer le accr�ditation que si il n'y a pas d'autre moniteur le concernant.
		if(accreMonDAO.nbrAccreMoniteur(accreditations.get(index)) == 1)
			accreditations.get(index).supprimer();
		
		accreMonDAO.delete(temp);

		accreditations.remove(index);
		
		
	}

	/**
	 * Initialise la liste des accr�ditations.
	 * @throws Exception Erreur lors de l'initialisation des accr�ditations!
	 * @exception Exception  Erreur lors de l'initialisation des accr�ditations!
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
			throw new Exception("Erreur lors de l'initialisation des accr�ditations!");
		}
	}

	/**
	 * G�n�re la liste des r�servations que le moniteur peut prendre.
	 * @param id L'id du moniteur. 0 si on cherche les r�servations qui n'ont pas de moniteur.
	 * @return La liste des r�servations.
	 * @throws Exception Erreur lors de la v�rifications des r�servations possibles pour le moniteur!
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Reservation> checkReservations(int id) throws Exception{
		
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
		int flag = 0;
		boolean first = true;
		
		try{
			this.initAccreditations();
			//Charge la liste compl�te des r�servations.
			listeReservation = Reservation.loadByMonitor(id);
			for(int i=0; i< listeReservation.size() ; i++){
				//V�rifie si il y a le nombre minimum de r�servation pour ce cours.
				if(Reservation.getNbrReservationByCours(listeReservation.get(i).getCours(), listeReservation.get(i).getSemaine(),
						listeReservation.get(i).getHeure().getHours()) >= listeReservation.get(i).getCours().getMinEleve()){

					if(first){

						//V�rifie si le moniteur a l'accr�ditation requise.
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
			throw new Exception("Erreur lors de la v�rifications des r�servations possibles pour le moniteur!");
		}


		return reservations;
	}
	

	//[end]M�thodes

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
