package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoniteurDAO extends DAO<Moniteur>{
	
	public MoniteurDAO(Connection conn){
		super(conn);
	}

	public boolean create(Moniteur obj){		
		return false;
	}

	public boolean delete(Moniteur obj){
		return false;
	}

	public boolean update(Moniteur obj){
		return false;
	}

	public Moniteur find(int id){
		Moniteur moniteur = new Moniteur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Moniteur WHERE numMoniteur = " + id);
			if(result.first()){
				//moniteur.set Eleve(/*id, result.getString("nomEleve"), result.getString("prenomEleve")*/);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return moniteur;
	}
}