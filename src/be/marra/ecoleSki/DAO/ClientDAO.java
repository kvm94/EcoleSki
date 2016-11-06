package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.marra.ecoleSki.*;

public class ClientDAO extends DAO<Client>{
	
	//-------------------------Constructeur-------------------------//
	
	public ClientDAO(Connection conn){
		super(conn);
	}

	//-------------------------Méthodes-------------------------//
	
	/**
	 * Ajoute un client dans la base de données.
	 * @param Le client à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 */
	public boolean create(Client obj){		
		boolean check = false;
		ArrayList<Client> c = find(obj.getNom(), obj.getPrenom());
		
		if(c.size() < 1){
			try{
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO Client (mdp,nom, prenom, dateNaissance) VALUES(?,?,?,?)");
				statement.setString(1,obj.getPasswd());
				statement.setString(2,obj.getNom());
				statement.setString(3,obj.getPrenom());
				
				//Conversion de la date en INTEGER pour la base de données SQLite.
				LocalDate date = obj.getDateNaissance();
				long output = date.toEpochDay();
				
				statement.setLong(4, output);
				
				statement.executeUpdate();
				check = true;
			}
			catch (Exception e){
				e.printStackTrace();  
			}
		}
		else
			JOptionPane.showMessageDialog(null, "L'utilisateur existe déjà!");
		
		return check;
	}

	/**
	 * Supprime un client de la base de données.
	 * @param Le clientn à supprimer.
	 * @eturn True si l'opération c'est bien déroulée.
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
	 * Met à jour un client de la base de données.
	 * @param Le client à mettre à jour.
	 * @return True si l'opération c'est bien déroulée.
	 */
	public boolean update(Client obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Client set mdp =? ,nom = ?, prenom= ?, dateNaissance = ? WHERE id_client = " + obj.getId());
			statement.setString(1,obj.getPasswd());
			statement.setString(2,obj.getNom());
			statement.setString(3,obj.getPrenom());

			//Conversion de la date en INTEGER pour la base de données SQLite.
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
	 * Cherche un client dans la base de données.
	 * @param L'id du client.
	 * @return Le client recherchée.
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
	
	/**
	 * Cherche un client dans la base de données.
	 * @param String nom, String prenom
	 * @return La liste des client trouvé.
	 */
	public ArrayList<Client> find(String nom, String prenom){
		ArrayList<Client> clients = new ArrayList<Client>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Client WHERE nom = '" + nom + "'and prenom = '" + prenom + "'");
			
			while(result.next()){
				Client client = new Client();
				client.setId(result.getInt("id_client"));
				client.setNom(result.getString("nom"));
				client.setPrenom(result.getString("prenom"));
				client.setPasswd(result.getString("mdp"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				client.setDateNaissance(output);
				
				clients.add(client);
				
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return clients;
	}
	
	/**
	 * Cherche un client dans la base de données avec son mot de passe.
	 * @param String mdp, String nom, String prenom
	 * @return Les clients trouvé.
	 */
	@Override
	public ArrayList<Client> find(String nom, String prenom, String mdp){
		ArrayList<Client> clients = new ArrayList<Client>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Client WHERE nom = '" + nom + "'and prenom = '" + prenom + "' and mdp ='" + mdp + "'");
			
			while(result.next()){
				Client client = new Client();
				client.setId(result.getInt("id_client"));
				client.setNom(result.getString("nom"));
				client.setPrenom(result.getString("prenom"));
				client.setPasswd(result.getString("mdp"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				client.setDateNaissance(output);
				
				clients.add(client);
				
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return clients;
	}
}
