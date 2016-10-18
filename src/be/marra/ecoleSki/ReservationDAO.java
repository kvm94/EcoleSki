package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO extends DAO<Reservation>{
	
	public ReservationDAO(Connection conn){
		super(conn);
	}

	public boolean create(Reservation obj){		
		return false;
	}

	public boolean delete(Reservation obj){
		return false;
	}

	public boolean update(Reservation obj){
		return false;
	}

	public Reservation find(int id){
		Reservation reservation = new Reservation();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Reservation WHERE numReservation = " + id);
			if(result.first()){
				//moniteur.set Eleve(/*id, result.getString("nomEleve"), result.getString("prenomEleve")*/);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return reservation;
	}
}