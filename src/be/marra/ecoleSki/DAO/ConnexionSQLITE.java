package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnexionSQLITE {
    private static Connection snglConnection = null;

    /**
     * Se connecte à la base de données SQLITE du dossier /data.
     */
    private ConnexionSQLITE() {
        try {
            Class.forName("org.sqlite.JDBC");

            snglConnection = DriverManager.getConnection("jdbc:sqlite:data/database.db");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Impossible de trouver le driver pour la base de donnée!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de se connecter à  la base de donnée.");
        }

        if (snglConnection == null) {
            JOptionPane.showMessageDialog(null, "La base de donnée est innaccessible, fermeture du programme.");
            System.exit(0);
        }
    }

    /**
     * Désctructeur qui ferme la connexion à la base de données.
     */
    public void finalize()
    {
      disconnect();
    }
    
    /**
     * Récupère l'instance de la classe pour le Singleton.
     * @return La connexion.
     */
    public static Connection getInstance() {
        if (snglConnection == null) {
            new ConnexionSQLITE();
        }

        return snglConnection;
    }
    
    /**
     * Se déconnecte de la base de données.
     * @return Tur si la base de données à bien été déconnecté.
     */
    static public boolean disconnect()
    {
      try
      {
        if (snglConnection != null) {
          snglConnection.close();
        }
        System.out.println("Disconnection done!");
        return true;
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return false;
    }
}
