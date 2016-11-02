package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import be.marra.ecoleSki.Accreditation.E_Categorie;
import be.marra.ecoleSki.Accreditation.E_Sport;

public class AccreditationDAO extends DAO<Accreditation>{

	//-------------------------Constructeur-------------------------//
	
	public AccreditationDAO(Connection conn){
		super(conn);
	}

	//-------------------------Méthodes-------------------------//
	
	/**
	 * Ajoute une accréditation dans la base de données.
	 * @param L'accréditation à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 */
	public boolean create(Accreditation obj){		
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO Accreditation (categorie,sport) VALUES(?,?)");
			statement.setInt(1,obj.getCat().getValue());
			statement.setInt(2,obj.getSport().getValue());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Supprime une accréditation de la base de données.
	 * @param L'accréditation à supprimer.
	 * @return True si l'opération c'est bien déroulée.
	 */
	public boolean delete(Accreditation obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Accreditation WHERE id_accreditation= ?");
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
	 * Met à jour une accréditation de la base de données.
	 * @param L'accréditation à mettre à jour.
	 * @return True si l'opération c'est bien déroulée.
	 */
	public boolean update(Accreditation obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Accreditation set categorie =? ,sport = ? WHERE id_accreditation = " + obj.getId());
			statement.setInt(1,obj.getCat().getValue());
			statement.setInt(2,obj.getSport().getValue());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Cherche une accréditation dans la base de données.
	 * @param L'id de l'accréditation.
	 * @return L'accréditation recherchée.
	 */
	public Accreditation find(int id){
		Accreditation accreditation = new Accreditation();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Accreditation WHERE id_accreditation = " + id);
			
			while(result.next()){
				accreditation.setId(result.getInt("id_accreditation"));
				
				switch(result.getInt("categorie")){
					case 0: accreditation.setCat(E_Categorie.Enfant);
						break;
					case 1: accreditation.setCat(E_Categorie.Adulte);
						break;
				}
				
				switch(result.getInt("sport")){
					case 0: accreditation.setSport(E_Sport.Ski);
						break;
					case 1: accreditation.setSport(E_Sport.Snowboard);
						break;
					case 2: accreditation.setSport(E_Sport.Telemark);
						break;
					case 3: accreditation.setSport(E_Sport.SkiFond);
						break;
				}
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return accreditation;
	}
	
	/**
	 * Récupère l'id d'une accréditation dans la base de données. 
	 * @param L'accréditation. 
	 * @return L'id de l'accréditation.
	 */
	/*public int getId(Accreditation obj){
		int id = 0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_accreditation FROM Accreditation WHERE "
							+ "categorie = " + obj.getCat().getValue()
							+ "and sport = " + obj.getSport().getValue());
			
			while(result.next()){
				id =result.getInt("id_accreditation");
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}*/
	
}
