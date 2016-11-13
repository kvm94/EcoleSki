package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import be.marra.ecoleSki.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


import be.marra.ecoleSki.Reservation.E_Statut;

public class ReservationDAO extends DAO<Reservation>{
	
	public ReservationDAO(Connection conn){
		super(conn);
	}

	/**
	 * Ajoute une réservation dans la base de données.
	 * @param La réservation à ajouter.
	 * @return True si l'opération c'est bien effectuée.
	 * @throws Exception 
	 */
	@SuppressWarnings("deprecation")
	public boolean create(Reservation obj) throws Exception{		
		boolean check = false;

		if(!find(obj)){
			try{
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO Reservation (id_client, id_semaine, id_eleve, id_cours, heure, min, statut, prix) VALUES(?,?,?,?,?,?,?,?)");
				statement.setInt(1,obj.getClient().getId());
				statement.setInt(2,obj.getSemaine().getId());
				statement.setInt(3,obj.getEleve().getId());
				statement.setInt(4,obj.getCours().getId());
				statement.setInt(5,obj.getHeure().getHours());
				statement.setInt(6,obj.getHeure().getMinutes());
				
				if(obj.getStatut() == E_Statut.Paye)
					statement.setInt(7, 1);
				else
					statement.setInt(7, 0);
				
				statement.setDouble(8,obj.getPrix());

				statement.executeUpdate();
				check = true;
			}
			catch (Exception e){
				e.printStackTrace();  
			}
		}
		else
			throw new Exception("Il y a déjà une réservation pour cette élève! ");
		
		return check;
	}

	/**
	 * Supprime une réservation de la base de données.
	 * @param La réservation à supprimer.
	 * @eturn True si l'opération c'est bien déroulée.
	 */
	public boolean delete(Reservation obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Reservation WHERE id_reservation= ?");
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
	 * Met à jour une réservation de la base de données.
	 * @param La réservation à mettre à jour.
	 * @return True si l'opération c'est bien déroulée.
	 */
	@SuppressWarnings("deprecation")
	public boolean update(Reservation obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Reservation set "
							+ "id_Client = ?,"
							+ "id_semaine= ?,"
							+ "id_eleve =?,"
							+ "id_cours=?,"
							+ "heure=?,"
							+ "min=?,"
							+ "statut=?,"
							+ "prix=? "
							+ "WHERE id_reservation = " + obj.getId());

			statement.setInt(1,obj.getClient().getId());
			statement.setInt(2,obj.getSemaine().getId());
			statement.setInt(3,obj.getEleve().getId());
			statement.setInt(4,obj.getCours().getId());
			statement.setInt(5,obj.getHeure().getHours());
			statement.setInt(6,obj.getHeure().getMinutes());
			
			if(obj.getStatut() == E_Statut.Paye)
				statement.setInt(7, 1);
			else
				statement.setInt(7, 0);
			
			statement.setDouble(8,obj.getPrix());

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Cherche une réservation dans la base de données.
	 * @param L'id de la réservation.
	 * @return La réservation recherchée.
	 */
	@SuppressWarnings("deprecation")
	public Reservation find(int id){
		Reservation reservation = new Reservation();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Reservation WHERE id_reservation = " + id);
			
			while(result.next()){
				reservation.setIdClient(result.getInt("id_client"));
				reservation.setIdSemaine(result.getInt("id_semaine"));
				reservation.setIdEleve(result.getInt("id_eleve"));
				reservation.setIdCours(result.getInt("id_cours"));
				reservation.setId(id);
				Time heure = new Time(0);
				heure.setHours(result.getInt("heure"));
				heure.setMinutes(result.getInt("min"));
				reservation.setHeure(heure);
				if(result.getInt("statut") == 1)
					reservation.setStatut(E_Statut.Paye);
				else
					reservation.setStatut(E_Statut.Reserve);
				reservation.setPrix(result.getDouble("prix"));
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return reservation;
	}
	
	@SuppressWarnings("deprecation")
	public boolean find(Reservation res){
		boolean check=false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Reservation WHERE "
							+ "id_eleve = " + res.getEleve().getId()
							+ " and id_semaine = " + res.getSemaine().getId()
							+ " and heure = " + res.getHeure().getHours());
			
			while(result.next()){
				check = true;
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return check;
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<Reservation> find(int statut, int id_client){

		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Reservation WHERE id_client = " + id_client + " and statut = " + statut);

			while(result.next()){
				
				Reservation reservation = new Reservation();
				reservation.setIdClient(result.getInt("id_client"));
				reservation.setIdSemaine(result.getInt("id_semaine"));
				reservation.setIdEleve(result.getInt("id_eleve"));
				reservation.setIdCours(result.getInt("id_cours"));
				reservation.setId(result.getInt("id_reservation"));
				Time heure = new Time(0);
				heure.setHours(result.getInt("heure"));
				heure.setMinutes(result.getInt("min"));
				reservation.setHeure(heure);
				if(result.getInt("statut") == 1)
					reservation.setStatut(E_Statut.Paye);
				else
					reservation.setStatut(E_Statut.Reserve);
				reservation.setPrix(result.getDouble("prix"));
				
				reservations.add(reservation);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return reservations;
	}
	
	//Retourne le nombre de réservation pour un cours.
	public int nbrResCours(Cours cours){
		int cpt = 0;
		
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Reservation WHERE id_cours = " + cours.getId());

			while(result.next()){
				cpt++;
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return cpt;
	}
}