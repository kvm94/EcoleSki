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
	 */
	@Override
	public boolean inscription(){
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
	
	public void ajoutAccreditation(Accreditation a){
		PAccreditation_Moniteur temp; 
		
		accreditations.add(a);
		a.insertIntoDB();
		
		temp = new PAccreditation_Moniteur(a.getId(), id);
		accreMonDAO.create(temp);
		
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
