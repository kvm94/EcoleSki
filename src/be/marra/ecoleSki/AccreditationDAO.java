package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccreditationDAO extends DAO<Accreditation>{
	
	public AccreditationDAO(Connection conn){
		super(conn);
	}

	public boolean create(Accreditation obj){		
		return false;
	}

	public boolean delete(Accreditation obj){
		return false;
	}

	public boolean update(Accreditation obj){
		return false;
	}

	public Accreditation find(int id){
		Accreditation accreditation = new Accreditation();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Client WHERE numClient = " + id);
			if(result.first()){
				//moniteur.set Eleve(/*id, result.getString("nomEleve"), result.getString("prenomEleve")*/);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return accreditation;
	}
}
