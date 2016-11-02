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

	//-------------------------M�thodes-------------------------//
	
	/**
	 * Ajoute une accr�ditation dans la base de donn�es.
	 * @param L'accr�ditation � ajouter.
	 * @return True si l'op�ration c'est bien effectu�e.
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
	 * Supprime une accr�ditation de la base de donn�es.
	 * @param L'accr�ditation � supprimer.
	 * @return True si l'op�ration c'est bien d�roul�e.
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
	 * Met � jour une accr�ditation de la base de donn�es.
	 * @param L'accr�ditation � mettre � jour.
	 * @return True si l'op�ration c'est bien d�roul�e.
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
	 * Cherche une accr�ditation dans la base de donn�es.
	 * @param L'id de l'accr�ditation.
	 * @return L'accr�ditation recherch�e.
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
	 * R�cup�re l'id d'une accr�ditation dans la base de donn�es. 
	 * @param L'accr�ditation. 
	 * @return L'id de l'accr�ditation.
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
