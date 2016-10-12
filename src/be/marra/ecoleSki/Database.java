package be.marra.ecoleSki;
import java.sql.*;
/**
 * Cette classe fait l'interface avec la base de donn�es.
 */
public class Database

{
	private String      dbName;
	public  Connection  connection;
	private Statement   requete;

	/**
	 * Constructeur de la classe Database
	 * @param dbName Le nom de la base de donn�es
	 */
	public Database (String dbName)
	{
		// Charge le driver sqlite JDBC en utilisant le class loader actuel
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e1)
		{
			System.err.println(e1.getMessage());
		}

		this.dbName     = dbName;
		this.connection = null;
	}
	
	public void finalize(){
		disconnect();
	}

	/**
	 * Ouvre la base de donn�es sp�cifi�e
	 * @return True si la connection � �t� r�ussie. False sinon.
	 */
	public boolean connect ()
	{
		try
		{
			// Etabli la connection
			connection = DriverManager.getConnection("jdbc:sqlite:"+this.dbName);
			// D�clare l'objet qui permet de faire les requ�tes
			requete = connection.createStatement();

			// Le PRAGMA synchronous de SQLite permet de v�rifier chaque �criture
			// avant d'en faire une nouvelle.
			// Le PRAGMA count_changes de SQLite permet de compter le nombre de
			// changements fait sur la base
			// R�sultats de mes tests :
			// synchronous OFF, une insertion est 20 fois plus rapide.
			// La diff�rences avec le count_changes est de l'ordre de la �s.
			// Les autres PRAGMA : http://www.sqlite.org/pragma.html

			requete.executeUpdate("PRAGMA synchronous = OFF;");
			requete.setQueryTimeout(30);

			return true;
		}
		catch(SQLException e)
		{
			// message = "out of memory" souvent le resultat de la BDD pas trouv�e
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Ferme la connection � la base de donn�es
	 * @return True si la connection a bien �t� ferm�e. False sinon.
	 */
	public boolean disconnect ()
	{
		try
		{
			if(connection != null)
				connection.close();

			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permet de faire une requ�te SQL
	 * @param requete La requ�te SQL (avec un ";" � la fin)
	 * @return Un ResultSet contenant le r�sultat de la requ�te
	 */
	public ResultSet getResultOf (String requete)
	{
		try
		{
			return this.requete.executeQuery(requete);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Permet de modifier une entr�e de la base de donn�es.
	 * @param requete La requete SQL de modification
	 */
	public void updateValue (String requete)
	{
		try
		{
			this.requete.executeUpdate(requete);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}