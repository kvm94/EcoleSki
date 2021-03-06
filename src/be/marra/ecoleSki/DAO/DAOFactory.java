package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import be.marra.ecoleSki.*;

public class DAOFactory extends AbstractDAOFactory{
	protected static final Connection conn = ConnexionSQLITE.getInstance();


	public DAO<Eleve> getEleveDAO(){
		return new EleveDAO(conn);
	}
	
	public DAO<Moniteur> getMoniteurDAO(){
		return new MoniteurDAO(conn);
	}
	
	public DAO<Client> getClientDAO(){
		return new ClientDAO(conn);
	}
	
	public DAO<Reservation> getReservationDAO(){
		return new ReservationDAO(conn);
	}
	
	public DAO<Cours> getCoursDAO(){
		return new CoursDAO(conn);
	}
	
	public DAO<Accreditation> getAccreditationDAO(){
		return new AccreditationDAO(conn);
	}
	
	public DAO<Semaine> getSemaineDAO(){
		return new SemaineDAO(conn);
	}
	
	public DAO<PAccreditation_Moniteur> getAccreditation_MoniteurDAO(){
		return new Accreditation_MoniteurDAO(conn);
	}

}
