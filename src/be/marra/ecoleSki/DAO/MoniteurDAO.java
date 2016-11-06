package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.marra.ecoleSki.*;

public class MoniteurDAO extends DAO<Moniteur>{
	
	public MoniteurDAO(Connection conn){
		super(conn);
	}

	/**
	 * Ajoute un moniteur dans la base de données.
	 * @param Le moniteur à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 * @throws Exception L'utilisateur éxiste déjà.
	 */
	public boolean create(Moniteur obj){		
		boolean check = false;

		ArrayList<Moniteur> m = find(obj.getNom(), obj.getPrenom());
		if(m.size() < 1){
			try{
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO Moniteur (mdp,nom, prenom, dateNaissance) VALUES(?,?,?,?)");
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
		else{
			JOptionPane.showMessageDialog(null, "L'utilisateur existe déjà!");
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
			//statement.setInt(5, obj.getAcre().getId());

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
				//moniteur.setIdAcre(result.getInt("id_accreditation"));
				
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
	 * @return La liste des moniteurs trouvées.
	 */
	public ArrayList<Moniteur> find(String nom, String prenom){
		ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Moniteur WHERE nom = '" + nom + "' and prenom = '" + prenom + "'");
			
			while(result.next()){
				Moniteur moniteur = new Moniteur();
				
				moniteur.setId(result.getInt("id_moniteur"));
				moniteur.setNom(result.getString("nom"));
				moniteur.setPrenom(result.getString("prenom"));
				moniteur.setPasswd(result.getString("mdp"));
				//moniteur.setIdAcre(result.getInt("id_accreditation"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				moniteur.setDateNaissance(output);
				
				moniteurs.add(moniteur);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return moniteurs;
	}
	
	public ArrayList<Moniteur> find(String nom, String prenom, String mdp){
		ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Moniteur WHERE nom = '" + nom + "' and prenom = '" + prenom + "' and mdp = '"+ mdp +"'");
			
			while(result.next()){
				Moniteur moniteur = new Moniteur();
				
				moniteur.setId(result.getInt("id_moniteur"));
				moniteur.setNom(result.getString("nom"));
				moniteur.setPrenom(result.getString("prenom"));
				moniteur.setPasswd(result.getString("mdp"));
				//moniteur.setIdAcre(result.getInt("id_accreditation"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				moniteur.setDateNaissance(output);
				
				moniteurs.add(moniteur);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return moniteurs;
	}
}