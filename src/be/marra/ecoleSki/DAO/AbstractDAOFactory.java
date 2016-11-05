package be.marra.ecoleSki.DAO;

import be.marra.ecoleSki.*;

public abstract class AbstractDAOFactory {
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;
	
	public abstract DAO<Eleve> getEleveDAO();
	public abstract DAO<Moniteur> getMoniteurDAO();
	public abstract DAO<Client> getClientDAO();
	public abstract DAO<Reservation> getReservationDAO();
	public abstract DAO<Cours> getCoursDAO();
	public abstract DAO<Accreditation> getAccreditationDAO();
	public abstract DAO<Semaine> getSemaineDAO();
	
	public static AbstractDAOFactory getFactory(int type){
		switch(type){
		case DAO_FACTORY:
			return new DAOFactory();
			default:
				return null;
		}
	}
}

