package be.marra.ecoleSki.DAO;

public class PAccreditation_Moniteur {

	private int id_accreditation;
	private int id_moniteur;
	
	public PAccreditation_Moniteur(int id_accreditation, int id_moniteur){
		this.setId_accreditation(id_accreditation);
		this.setId_moniteur(id_moniteur);
	}

	public int getId_accreditation() {
		return id_accreditation;
	}

	public void setId_accreditation(int id_accreditation) {
		this.id_accreditation = id_accreditation;
	}

	public int getId_moniteur() {
		return id_moniteur;
	}

	public void setId_moniteur(int id_moniteur) {
		this.id_moniteur = id_moniteur;
	}
}
