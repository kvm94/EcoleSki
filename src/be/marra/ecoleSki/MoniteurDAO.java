package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MoniteurDAO extends DAO<Moniteur>{
	
	public MoniteurDAO(Connection conn){
		super(conn);
	}

	/**
	 * Ajoute un moniteur dans la base de données.
	 * @param Le moniteur à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 */
	public boolean create(Moniteur obj){		
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO Moniteur (id_accreditation, mdp,nom, prenom, dateNaissance) VALUES(?,?,?,?,?)");
			statement.setInt(1,obj.getAcre().getId());
			statement.setString(2,obj.getPasswd());
			statement.setString(3,obj.getNom());
			statement.setString(4,obj.getPrenom());
			
			//Conversion de la date en INTEGER pour la base de données SQLite.
			LocalDate date = obj.getDateNaissance();
			long output = date.toEpochDay();
			
			statement.setLong(5, output);
			
			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Supprime un moniteur de la base de données.
	 * @param Le moniteur à supprimer.
	 * @eturn True si l'opération c'est bien déroulée.
	 */
	public boolean delete(Moniteur obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Moniteur WHERE id_moniteur= ?");
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
	 * Met à jour un moniteur de la base de données.
	 * @param Le moniteur à mettre à jour.
	 * @return True si l'opération c'est bien déroulée.
	 */
	public boolean update(Moniteur obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Moniteur set mdp =? ,nom = ?, prenom= ?, dateNaissance = ?, id_accreditation =?  WHERE id_moniteur = " + obj.getId());
			statement.setString(1,obj.getPasswd());
			statement.setString(2,obj.getNom());
			statement.setString(3,obj.getPrenom());

			//Conversion de la date en INTEGER pour la base de données SQLite.
			LocalDate date = obj.getDateNaissance();
			long output = date.toEpochDay();
			
			statement.setLong(4, output);
			statement.setInt(5, obj.getAcre().getId());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Cherche un moniteur dans la base de données.
	 * @param L'id du moniteur.
	 * @return Le moniteur recherchée.
	 */
	public Moniteur find(int id){
		Moniteur moniteur = new Moniteur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Moniteur WHERE id_moniteur = " + id);
			
			while(result.next()){
				moniteur.setId(result.getInt("id_moniteur"));
				moniteur.setNom(result.getString("nom"));
				moniteur.setPrenom(result.getString("prenom"));
				moniteur.setPasswd(result.getString("mdp"));
				moniteur.setIdAcre(result.getInt("id_accreditation"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				moniteur.setDateNaissance(output);
				
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return moniteur;
	}
	
	/**
	 * Cherche un moniteur dans la base de données.
	 * @param String nom, String prenom, String mdp
	 * @return Le moniteur recherchée.
	 */
	public Moniteur find(String nom, String prenom, String mdp){
		Moniteur moniteur = new Moniteur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Moniteur WHERE nom = " + nom + ", prenom = " + prenom + ", mdp = " + mdp);
			
			while(result.next()){
				moniteur.setId(result.getInt("id_moniteur"));
				moniteur.setNom(result.getString("nom"));
				moniteur.setPrenom(result.getString("prenom"));
				moniteur.setPasswd(result.getString("mdp"));
				moniteur.setIdAcre(result.getInt("id_accreditation"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				moniteur.setDateNaissance(output);
				
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return moniteur;
	}
}