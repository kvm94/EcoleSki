package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SemaineDAO extends DAO<Semaine>{
	
	public SemaineDAO(Connection conn){
		super(conn);
	}

	/**
	 * Ajoute une semaine dans la base de données.
	 * @param La semaine à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 */
	public boolean create(Semaine obj){		
		boolean check = false;

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
}