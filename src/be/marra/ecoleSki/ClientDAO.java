package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends DAO<Client>{
	
	public ClientDAO(Connection conn){
		super(conn);
	}

	public boolean create(Client obj){		
		return false;
	}

	public boolean delete(Client obj){
		return false;
	}

	public boolean update(Client obj){
		return false;
	}

	public Client find(int id){
		Client client = new Client();
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
		return client;
	}
}
