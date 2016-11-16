package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import be.marra.ecoleSki.*;

public class EleveDAO extends DAO<Eleve>{
	public EleveDAO(Connection conn){
		super(conn);
	}

	/**
	 * Ajoute un eleve dans la base de données.
	 * @param L'eleve à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 */
	public boolean create(Eleve obj){		
		boolean check = false;

		if(!find(obj)){
			try{
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO Eleve (assurance,nom, prenom, dateNaissance) VALUES(?,?,?,?)");
				if(obj.isAssurance()){
					statement.setInt(1, 1);
				}
				else
					statement.setInt(1, 0);
				
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
		
		return check;
	}

	/**
	 * Supprime un eleve de la base de données.
	 * @param L'eleve à supprimer.
	 * @eturn True si l'opération c'est bien déroulée.
	 */
	public boolean delete(Eleve obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Eleve WHERE id_eleve= ?");
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
	 * Met à jour un eleve de la base de données.
	 * @param L'eleve à mettre à jour.
	 * @return True si l'opération c'est bien déroulée.
	 */
	public boolean update(Eleve obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Eleve set assurance =? ,nom = ?, prenom= ?, dateNaissance = ? WHERE id_eleve = " + obj.getId());
			if(obj.isAssurance()){
				statement.setInt(1, 1);
			}
			else
				statement.setInt(1, 0);
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
	 * Cherche un eleve dans la base de données.
	 * @param L'id de l'eleve.
	 * @return L'eleve recherchée.
	 */
	public Eleve find(int id){
		Eleve eleve = new Eleve();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Eleve WHERE id_eleve = " + id);
			
			while(result.next()){
				eleve.setId(result.getInt("id_eleve"));
				eleve.setNom(result.getString("nom"));
				eleve.setPrenom(result.getString("prenom"));
				
				//Conversion de la date Long en type Date
				long input = result.getLong("dateNaissance");
				LocalDate output = LocalDate.ofEpochDay(input);
				eleve.setDateNaissance(output);
				
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return eleve;
	}
	
	/**
	 * Vérifie si un élève éxiste dans la table Eleve.
	 * @param eleve
	 * @return True si l'élève éxiste.
	 */
	public boolean find(Eleve eleve){
		boolean check = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_eleve FROM ELEVE WHERE nom ='" + eleve.getNom() +"' and prenom = '" + eleve.getPrenom() + "'");
			
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
	 * Cherche l'id d'un élève.
	 * @param eleve
	 * @return l'id de l'élève.
	 */
	public void getId(Eleve eleve){
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_eleve FROM ELEVE WHERE nom ='" + eleve.getNom() +"' and prenom = '" + eleve.getPrenom() + "'");
			
			while(result.next()){
				eleve.setId(result.getInt("id_eleve"));
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}