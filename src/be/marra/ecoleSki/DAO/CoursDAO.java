package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.marra.ecoleSki.*;
import be.marra.ecoleSki.Accreditation.E_Categorie;
import be.marra.ecoleSki.Accreditation.E_Sport;
import be.marra.ecoleSki.Cours.E_Niveaux;

public class CoursDAO extends DAO<Cours>{

	public CoursDAO(Connection conn){
		super(conn);
	}

	/**
	 * Ajoute un cours dans la base de donn�es.
	 * @param Le cours � ajouter.
	 * @return True si l'op�ration c'est bien effectu�e.
	 */

	public boolean create(Cours obj){		
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO Cours (id_moniteur, categorie, sport, niveaux, heure, prix, minEleve, maxEleve, collectif) VALUES(?,?,?,?,?,?,?,?,?)");
			statement.setInt(1,obj.getMoniteur().getId());
			statement.setInt(2,obj.getCategorie().getValue());
			statement.setInt(3,obj.getSport().getValue());
			statement.setInt(4,obj.getNiveaux().getValue());
			statement.setInt(5,obj.getHeure());
			statement.setDouble(6,obj.getPrix());
			statement.setInt(7,obj.getMinEleve());
			statement.setInt(8,obj.getMaxEleve());

			if(obj.isCollectif()){
				statement.setInt(9, 1);
			}
			else
				statement.setInt(9, 0);

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Supprime un cours de la base de donn�es.
	 * @param Le cours � supprimer.
	 * @eturn True si l'op�ration c'est bien d�roul�e.
	 */
	public boolean delete(Cours obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Cours WHERE id_cours= ?");
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
	 * Met � jour un cours de la base de donn�es.
	 * @param Le cours � mettre � jour.
	 * @return True si l'op�ration c'est bien d�roul�e.
	 */

	public boolean update(Cours obj){
		boolean check = false;

		try{
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Cours set "
							+ "id_moniteur = ?,"
							+ "categorie= ?,"
							+ "sport =?,"
							+ "niveaux=?,"
							+ "heure=?,"
							+ "prix=?,"
							+ "minEleve=?,"
							+ "maxEleve=?,"
							+ "collectif=? "
							+ "WHERE id_cours = " + obj.getId());

			statement.setInt(1,obj.getMoniteur().getId());
			statement.setInt(2,obj.getCategorie().getValue());
			statement.setInt(3,obj.getSport().getValue());
			statement.setInt(4,obj.getNiveaux().getValue());
			statement.setInt(5,obj.getHeure());
			statement.setDouble(6,obj.getPrix());
			statement.setInt(7,obj.getMinEleve());
			statement.setInt(8,obj.getMaxEleve());

			if(obj.isCollectif()){
				statement.setInt(9, 1);
			}
			else
				statement.setInt(9, 0);

			statement.executeUpdate();
			check = true;
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	/**
	 * Cherche un cours dans la base de donn�es.
	 * @param L'id du cours.
	 * @return Le cours recherch�e.
	 */

	public Cours find(int id){
		Cours cours = new Cours();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Cours WHERE id_cours = " + id);

			while(result.next()){
				cours.setId(result.getInt("id_cours"));
				cours.setIdMoniteur(result.getInt("id_moniteur"));	
				
				switch(result.getInt("categorie")){
					case 0: cours.setCategorie(E_Categorie.Enfant);
					break;
					case 1: cours.setCategorie(E_Categorie.Adulte);
					break;
				}

				switch(result.getInt("sport")){
					case 0: cours.setSport(E_Sport.Ski);
					break;
					case 1: cours.setSport(E_Sport.Snowboard);
					break;
					case 2: cours.setSport(E_Sport.Telemark);
					break;
					case 3: cours.setSport(E_Sport.SkiFond);
					break;
				}
				
				switch(result.getInt("niveaux")){
					case 0: cours.setNiveaux(E_Niveaux.PetitSpirou);
					break;
					case 1: cours.setNiveaux(E_Niveaux.Bronze);
					break;
					case 2: cours.setNiveaux(E_Niveaux.Argent);
					break;
					case 3: cours.setNiveaux(E_Niveaux.Or);
					break;
					case 4: cours.setNiveaux(E_Niveaux.Platine);
					break;
					case 5: cours.setNiveaux(E_Niveaux.Diamant);
					break;
					case 6: cours.setNiveaux(E_Niveaux.nv1);
					break;
					case 7: cours.setNiveaux(E_Niveaux.nv1_nv4);
					break;
					case 8: cours.setNiveaux(E_Niveaux.nv2_nv4);
					break;
					case 9: cours.setNiveaux(E_Niveaux.Competition);
					break;
					case 10: cours.setNiveaux(E_Niveaux.HorsPiste);
					break;
				}
				
				cours.setHeure(result.getInt("heure"));
				cours.setPrix(result.getDouble("prix"));
				cours.setMinEleve(result.getInt("minEleve"));
				cours.setMaxEleve(result.getInt("maxEleve"));
				
				if(result.getInt("collectif") == 1)
					cours.setCollectif(true);
				else
					cours.setCollectif(false);
			}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cours;
	}
}
