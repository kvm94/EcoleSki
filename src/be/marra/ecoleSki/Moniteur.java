package be.marra.ecoleSki;

import java.util.ArrayList;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.MoniteurDAO;

public class Moniteur extends Utilisateur {
	
	//[start]Attributs
	
	private ArrayList<Accreditation> listeAccre;
	private int id;

	//Base de donn�es//
	private AbstractDAOFactory adf;
	MoniteurDAO moniteurDAO;
	
	//[end]
	
	//[start]Constructeurs
	
	public Moniteur() {
		super(null, null, 1, 1, 1);
		this.listeAccre = new ArrayList<Accreditation>();
		
		initDB();
	}
	
	public Moniteur(String nom, String prenom, int jour, int mois, int annee) {
		super(nom, prenom, jour, mois, annee);
		this.listeAccre = new ArrayList<Accreditation>();
		
		initDB();
	}
	
	public Moniteur(String nom, String prenom, int jour, int mois, int annee, int id) {
		super(nom, prenom, jour, mois, annee);
		this.listeAccre = new ArrayList<Accreditation>();
		this.setId(id);
		
		initDB();
	}
	
	//[end]
	
	//[start]M�thodes
	
	/**
	 * Initialise l'acc�s � la base de donn�es.
	 */
	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		moniteurDAO = (MoniteurDAO)adf.getMoniteurDAO();
	}
	
	/*public void setIdAcre(int id, int pos){
		listeAccre.get(pos).setId(id);
	}*/

	/**
	 * Inscrit lemoniteur dans la base de donn�es.
	 * @return True si l'inscription c'est effectu�.
	 */
	@Override
	public boolean inscription(){
		if(moniteurDAO.create(this))
			return true;
		else
			return false;
	}
	
	/**
	 * R�cup�re les information du moniteur de la base de donn�es.
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
	
	//[end]
	
	//[start]Accesseurs
	
	public Accreditation getAcre(int pos)
	{
		return listeAccre.get(pos);
	}

	public void addAccre(Accreditation accre)
	{
		listeAccre.add(accre);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//[end]
}
