package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import be.marra.ecoleSki.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SemaineDAO extends DAO<Semaine>{
	
	//[start]Constructeur
	
	public SemaineDAO(Connection conn){
		super(conn);
	}

	//[end]Constructeur
	
	/**
	 * Ajoute une semaine dans la base de données.
	 * @param La semaine à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 */
	public boolean create(Semaine obj){		
		boolean check = false;

		if(!find(obj)){
			try{
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO Semaine (dateDebut,dateFin, descriptif, conge) VALUES(?,?,?,?)");
				
				//Conversion de la date en INTEGER pour la base de données SQLite.
				LocalDate date = obj.getDateDebut();
				long output = date.toEpochDay();
				
				statement.setLong(1, output);
				
				date = obj.getDateFin();
				output = date.toEpochDay();
				
				statement.setLong(2, output);
				
				statement.setString(3,obj.getDescriptif());
				
				if(obj.isCongeScolaire())
					statement.setInt(4, 1);
				else
					statement.setInt(4, 0);
				
				statement.executeUpdate();
				check = true;
			}
			catch (Exception e){
				e.printStackTrace();  
			}
			
		}
		return check;
	}

	/**
	 * Supprime une semaine de la base de données.
	 * @param La semaine à supprimer.
	 * @eturn True si l'opération c'est bien déroulée.
	 */
	public boolean delete(Semaine obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Semaine WHERE id_semaine= ?");
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
	 * Met à jour une semaine de la base de données.
	 * @param La semaine à mettre à jour.
	 * @return True si l'opération c'est bien déroulée.
	 */
	public boolean update(Semaine obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Semaine set dateDebut = ?, dateFin = ?, descriptif =?, conge = ? WHERE id_semaine = " + obj.getId());
			
			//Conversion de la date en INTEGER pour la base de données SQLite.
			LocalDate date = obj.getDateDebut();
			long output = date.toEpochDay();
			
			statement.setLong(1, output);
			
			date = obj.getDateFin();
			output = date.toEpochDay();
			
			statement.setLong(2, output);
			
			statement.setString(3,obj.getDescriptif());
			
			if(obj.isCongeScolaire())
				statement.setInt(4, 1);
			else
				statement.setInt(4, 0);

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Cherche une semaine dans la base de données.
	 * @param L'id de la semaine.
	 * @return La semaine recherchée.
	 */
	public Semaine find(int id){
		Semaine semaine = new Semaine();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Semaine WHERE id_semaine = " + id);
			
			while(result.next()){
				semaine.setId(result.getInt("id_semaine"));
								
				//Conversion de la date Long en type Date
				long input = result.getLong("dateDebut");
				LocalDate output = LocalDate.ofEpochDay(input);
				semaine.setDateDebut(output);
				
				//Conversion de la date Long en type Date
				input = result.getLong("dateFin");
				output = LocalDate.ofEpochDay(input);
				semaine.setDateFin(output);
				
				semaine.setDescriptif(result.getString("descriptif"));
				
				if(result.getInt("conge") == 1)
					semaine.setCongeScolaire(true);
				else
					semaine.setCongeScolaire(false);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return semaine;
	}
	
	/**
	 * Vérifie si une semaine est présente dans la table Semaine
	 * @param semaine
	 * @return
	 */
	public boolean find(Semaine semaine){
		boolean check = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Semaine WHERE dateDebut = " + semaine.getDateDebut().toEpochDay());
			
			while(result.next()){
				check = true;
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return check;
	}
	
	/**
	 * Chercher toutes les semaines présente dans la table Semaine
	 * @return
	 */
	public ArrayList<Semaine> find(){
		ArrayList<Semaine> semaines = new ArrayList<Semaine>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Semaine order by dateDebut");
			
			
			
			while(result.next()){
				Semaine semaine = new Semaine();
				semaine.setId(result.getInt("id_semaine"));
								
				//Conversion de la date Long en type Date
				long input = result.getLong("dateDebut");
				LocalDate output = LocalDate.ofEpochDay(input);
				semaine.setDateDebut(output);
				
				//Conversion de la date Long en type Date
				input = result.getLong("dateFin");
				output = LocalDate.ofEpochDay(input);
				semaine.setDateFin(output);
				
				semaine.setDescriptif(result.getString("descriptif"));
				
				if(result.getInt("conge") == 1)
					semaine.setCongeScolaire(true);
				else
					semaine.setCongeScolaire(false);
				
				semaines.add(semaine);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return semaines;
	}
	
	/**
	 * Calcul le nombre de semaines dans la base de données.
	 * @return
	 * @throws SQLException
	 */
	public int count() throws SQLException{
		ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*) AS COUNT FROM Semaine");

		result.next();
		
		return result.getInt(1);
		 
	}
}