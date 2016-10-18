package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoursDAO extends DAO<Cours>{
	
	public CoursDAO(Connection conn){
		super(conn);
	}

	public boolean create(Cours obj){		
		return false;
	}

	public boolean delete(Cours obj){
		return false;
	}

	public boolean update(Cours obj){
		return false;
	}

	public Cours find(int id){
		Cours cours = new Cours();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Cours WHERE numCours = " + id);
			if(result.first()){
				//moniteur.set Eleve(/*id, result.getString("nomEleve"), result.getString("prenomEleve")*/);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cours;
	}
}
