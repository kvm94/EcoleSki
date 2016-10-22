package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClientDAO extends DAO<Client>{
	
	//-------------------------Constructeur-------------------------//
	
	public ClientDAO(Connection conn){
		super(conn);
	}

	//-------------------------M�thodes-------------------------//
	
	/**
	 * Ajoute un client dans la base de donn�es.
	 * @param Le client � ajouter.
	 * @return True si l'op�ration c'est bien effectu�e.
	 */
	public boolean create(Client obj){		
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO Client (mdp,nom, prenom, dateNaissance) VALUES(?,?,?,?)");
			statement.setString(1,obj.getPasswd());
			statement.setString(2,obj.getNom());
			statement.setString(3,obj.getPrenom());
			
			//Conversion de la date en INTEGER pour la base de donn�es SQLite.
			LocalDate date = obj.getDateNaissance();
			long output = date.toEpochDay();
			
			statement.setLong(4, output);
			
			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Supprime un client de la base de donn�es.
	 * @param Le clientn � supprimer.
	 * @eturn True si l'op�ration c'est bien d�roul�e.
	 */
	public boolean delete(Client obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Client WHERE id_client= ?");
			statement.setInt(1,obj.getId());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Met � jour un client de la base de donn�es.
	 * @param Le client � mettre � jour.
	 * @return True si l'op�ration c'est bien d�roul�e.
	 */
	public boolean update(Client obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Client set mdp =? ,nom = ?, prenom= ?, dateNaissance = ? WHERE id_client = " + obj.getId());
			statement.setString(1,obj.getPasswd());
			statement.setString(2,obj.getNom());
			statement.setString(3,obj.getPrenom());

			//Conversion de la date en INTEGER pour la base de donn�es SQLite.
			LocalDate date = obj.getDateNaissance();
			long output = date.toEpochDay();
			
			statement.setLong(4, output);

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Cherche un client dans la base de donn�es.
	 * @param L'id du client.
	 * @return Le client recherch�e.
	 */
	public Client find(int id){
		Client client = new Client();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Client WHERE id_client = " + id);
			
			while(result.next()){
				client.setId(result.getInt("id_client"));
				client.setNom(result.getString("nom"));
				client.setPrenom(result.getString("prenom"));
				client.setPasswd(result.getString("mdp"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				client.setDateNaissance(output);
				
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return client;
	}
}
