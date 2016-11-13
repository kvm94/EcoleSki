package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Accreditation_MoniteurDAO extends DAO<PAccreditation_Moniteur>{

	public Accreditation_MoniteurDAO(Connection conn){
		super(conn);
	}

	public boolean create(PAccreditation_Moniteur obj) {
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO Accreditation_Moniteur (id_accreditation, id_moniteur) VALUES(?,?)");
			statement.setInt(1,obj.getId_accreditation());
			statement.setInt(2,obj.getId_moniteur());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	public boolean delete(PAccreditation_Moniteur obj) {
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement( 
					"DELETE FROM Accreditation_Moniteur WHERE id_accreditation= " + obj.getId_accreditation() + " and id_moniteur = " + obj.getId_moniteur());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	public boolean update(PAccreditation_Moniteur obj) {
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Accreditation_Moniteur set id_accreditation =? ,id_moniteur = ? WHERE id_accreditation = " + obj.getId_accreditation() + " and id_moniteur = " + obj.getId_moniteur());
			statement.setInt(1,obj.getId_accreditation());
			statement.setInt(2,obj.getId_moniteur());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	public PAccreditation_Moniteur find(int id) {
		PAccreditation_Moniteur accreMon = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Accreditation WHERE id_accreditation = " + id);
			
			while(result.next()){
				accreMon = new PAccreditation_Moniteur(result.getInt("id_accreditation"), result.getInt("id_moniteur"));
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return accreMon;
	}
}
