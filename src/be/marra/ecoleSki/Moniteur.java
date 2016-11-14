package be.marra.ecoleSki;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.Accreditation_MoniteurDAO;
import be.marra.ecoleSki.DAO.MoniteurDAO;
import be.marra.ecoleSki.DAO.PAccreditation_Moniteur;

public class Moniteur extends Utilisateur {

	//[start]Attributs

	private ArrayList<Accreditation> accreditations;
	private int id;

	//Base de données//
	private AbstractDAOFactory adf;
	MoniteurDAO moniteurDAO;
	Accreditation_MoniteurDAO accreMonDAO;

	//[end]

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

	//[end]

	//[start]Méthodes

	/**
	 * Initialise l'accès à la base de données.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		moniteurDAO = (MoniteurDAO)adf.getMoniteurDAO();
		accreMonDAO = (Accreditation_MoniteurDAO)adf.getAccreditation_MoniteurDAO();
	}

	/*public void setIdAcre(int id, int pos){
		listeAccre.get(pos).setId(id);
	}*/

	/**
	 * Inscrit lemoniteur dans la base de données.
	 * @return True si l'inscription c'est effectué.
	 * @throws Exception 
	 */
	@Override
	public boolean inscription() throws Exception{
		if(moniteurDAO.create(this))
			return true;
		else
			return false;
	}

	/**
	 * Récupère les information du moniteur de la base de données.
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

	public void ajoutAccreditation(Accreditation a) throws Exception{
		try{
			PAccreditation_Moniteur temp; 

			a.insertIntoDB();

			temp = new PAccreditation_Moniteur(a.getId(), id);
			accreMonDAO.create(temp);
			accreditations.add(a);
		}
		catch(Exception ex){
			throw ex;
		}
	}

	public void supprimerAccreditation(int index){
		PAccreditation_Moniteur temp = new 	PAccreditation_Moniteur(accreditations.get(index).getId(), id);
		accreMonDAO.delete(temp);

		accreditations.get(index).deleteFromDB();

		accreditations.remove(index);
	}

	public void initAccreditations(){
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
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Erreur lors de l'initialisation des accréditations!");
		}
	}

	//Renvois les reservations que le moniteur peut prendre.
	@SuppressWarnings("deprecation")
	public ArrayList<Reservation> checkReservations(int id){
		this.initAccreditations();
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
		int flag = 0;
		boolean first = true;
		try{
			listeReservation = Reservation.loadByMonitor(id);
			for(int i=0; i< listeReservation.size() ; i++){
				//Vérifie si il y a le nombre minimum de réservation pour ce cours.
				if(Reservation.getNbrReservationByCours(listeReservation.get(i).getCours(), listeReservation.get(i).getSemaine(),
						listeReservation.get(i).getHeure().getHours()) >= listeReservation.get(i).getCours().getMinEleve()){

					if(first){
						for(int j= 0 ; j< this.accreditations.size() ; j++){
							//Vérifie si le moniteur a l'accréditation requise.
							if(listeReservation.get(i).getCours().checkAccreditation(this.getAcre(j))){
								reservations.add(listeReservation.get(i));
								first = false;
							}
						}
					}else if(listeReservation.get(i).getId() != reservations.get(flag).getId()){
						for(int j= 0 ; j< this.accreditations.size() ; j++){
							//Vérifie si le moniteur a l'accréditation requise.
							if(listeReservation.get(i).getCours().checkAccreditation(this.getAcre(j))){
								reservations.add(listeReservation.get(i));
								flag++;
							}
						}
					}
				}
			}
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}


		return reservations;
	}

	//[end]

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

	//[end]
}
