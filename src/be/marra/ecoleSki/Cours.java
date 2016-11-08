package be.marra.ecoleSki;

import be.marra.ecoleSki.DAO.AbstractDAOFactory;
import be.marra.ecoleSki.DAO.DAO;
import be.marra.ecoleSki.Accreditation.E_Categorie;
import be.marra.ecoleSki.Accreditation.E_Sport;

public class Cours {

	//-------------------------Enumérations-------------------------//

	public static enum E_Niveaux
	{
		PetitSpirou(0),
		Bronze(1),
		Argent(2),
		Or(3),
		Platine(4),
		Diamant(5), 
		nv1(6),
		nv1_nv4(7),
		nv2_nv4(8),
		Competition(9),
		HorsPiste(10);

		private final int value;

		private E_Niveaux(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}


	//-------------------------Attributs-------------------------//

	private int 		id;
	private E_Categorie categorie;
	private E_Sport 	sport;
	private E_Niveaux 	niveaux;
	private Moniteur	moniteur;
	private int 		heure;
	private double 		prix;
	private int 		minEleve;
	private int 		maxEleve;
	private boolean 	collectif;

	//Base de données//

	private AbstractDAOFactory adf;
	DAO<Cours> CoursDAO;

	//-------------------------Constructeurs-------------------------//

	public Cours() {
		this.categorie = null;
		this.sport = null;
		this.niveaux = null;
		this.heure = 0;
		this.prix = 0;
		this.minEleve = 0;
		this.maxEleve = 0;
		this.collectif = false;
		moniteur = new Moniteur();

		initDB();
	}

	public Cours(E_Categorie categorie, E_Sport sport, E_Niveaux niveaux, Moniteur moniteur, int heure, double prix, int minEleve, int maxEleve, boolean collectif) {
		this.categorie = categorie;
		this.sport = sport;
		this.niveaux = niveaux;
		this.setMoniteur(moniteur);
		this.heure = heure;
		this.prix = prix;
		this.minEleve = minEleve;
		this.maxEleve = maxEleve;
		this.collectif = collectif;

		initDB();
	}


	//-------------------------Méthodes-------------------------//

	private void initDB(){
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		CoursDAO = adf.getCoursDAO();
	}

	public void setIdMoniteur(int id){
		moniteur.setId(id);
	}

	/**
	 * Vérifie si l'accréditation requise pour le cours est ok.
	 * @param a
	 * @return true si l'accréditation est valable.
	 */
	public boolean checkAccreditation(Accreditation a)
	{
		if ((this.categorie == a.getCat()) && (this.sport == a.getSport())) {
			return true;
		}
		return false;
	}

	/**
	 * Vérifie si les conditions sont remplies et adapte le prix pour un cours particulier.
	 */
	private void initParticulier() {
		minEleve = 1;
		maxEleve = 4;
		if(heure == 1)
			prix = 50;
		if(heure == 2)
			prix = 80;
		
	}

	/**
	 * Vérifie si les conditions sont remplies et adapte le prix pour un cours collectif.
	 */
	private void initCollectif() {
		if(categorie.getValue() == 1){
			minEleve = 6;
			maxEleve = 10;
			switch(sport.getValue()){
			case 0 :  //Ski
				switch(niveaux.getValue()){
				case 7: prix = 130; break;  //nv1_nv4
				case 9: 
					prix = 150;
					minEleve = 5;
					maxEleve = 8;
					break;  //Hors-piste
				case 10: prix = 150;
					minEleve = 5;
					maxEleve = 8;
					break; //Compétition
				default: prix = 0; break;
				}
				break;
			case 1: //Snowboard
				minEleve = 5;
				maxEleve = 8;
				switch(niveaux.getValue()){
				case 7: prix = 130; break;	//nv1_nv4
				case 10: prix = 150;break;	//hors-piste
				default: prix = 0; break;
				}
				break;
			case 2 : //Télémark
				prix = 120; //nv1_nv4
				break;
			case 3 : //Ski de fond
				prix = 100; //nv1_nv4
				break;
			default: prix = 0; break;
			}
		}
		else{ //l'élève est mineur.
			minEleve = 5;
			maxEleve = 8;
			switch(sport.getValue()){
			case 0 :  //Ski
				switch(niveaux.getValue()){
				case 0: prix = 100; break; //Petit Spirou
				case 1: prix = 120; break; //Bronze
				case 2: prix = 120; break; //Argent
				case 3: prix = 120; break; //Or
				case 4: prix = 120; break; //Platine
				case 5: prix = 120; break; //Diamant
				case 9: prix = 130; break; // Compétition
				case 10: prix = 130; break; //Hors-piste
				default: prix = 0; break;
				}
				break;
			case 1: //Snowboard
				switch(niveaux.getValue()){
				case 6: prix = 110; break;	//nv1
				case 7: prix = 120; break;	//nv1_nv4
				case 10:prix = 130; break;	//hors-piste
				default: prix = 0; break;
				}
				break;
			case 2 : //Télémark
				prix = 120; //nv1_nv4
				break;
			case 3 : //Ski de fond
				prix = 100; //nv1_nv4
				break;
			default: prix = 0; break;
			}
		}
	}

	/**
	 * Adapte le prix en fonction du sport et du niveaux.
	 */
	public void initPrix() {
		if(collectif)
			initCollectif();
		else
			initParticulier();
	}


	//-------------------------Accesseurs-------------------------//

	public E_Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(E_Categorie categorie) {
		this.categorie = categorie;
	}

	public E_Sport getSport() {
		return sport;
	}

	public void setSport(E_Sport sport) {
		this.sport = sport;
	}

	public E_Niveaux getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(E_Niveaux niveaux) {
		this.niveaux = niveaux;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getMinEleve() {
		return minEleve;
	}

	public void setMinEleve(int minEleve) {
		this.minEleve = minEleve;
	}

	public int getMaxEleve() {
		return maxEleve;
	}

	public void setMaxEleve(int maxEleve) {
		this.maxEleve = maxEleve;
	}

	public boolean isCollectif() {
		return collectif;
	}

	public void setCollectif(boolean collectif) {
		this.collectif = collectif;
	}

	public Moniteur getMoniteur() {
		return moniteur;
	}

	public void setMoniteur(Moniteur moniteur) {
		this.moniteur = moniteur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



}
