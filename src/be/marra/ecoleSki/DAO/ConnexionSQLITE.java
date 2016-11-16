package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnexionSQLITE {
    private static Connection snglConnection = null;

    /**
     * Se connecte � la base de donn�es SQLITE du dossier /data.
     */
    private ConnexionSQLITE() {
        try {
            Class.forName("org.sqlite.JDBC");

            snglConnection = DriverManager.getConnection("jdbc:sqlite:data/database.db");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Impossible de trouver le driver pour la base de donn�e!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Impossible de se connecter �  la base de donn�e.");
        }

        if (snglConnection == null) {
            JOptionPane.showMessageDialog(null, "La base de donn�e est innaccessible, fermeture du programme.");
            System.exit(0);
        }
    }

    /**
     * D�sctructeur qui ferme la connexion � la base de donn�es.
     */
    public void finalize()
    {
      disconnect();
    }
    
    /**
     * R�cup�re l'instance de la classe pour le Singleton.
     * @return La connexion.
     */
    public static Connection getInstance() {
        if (snglConnection == null) {
            new ConnexionSQLITE();
        }

        return snglConnection;
    }
    
    /**
     * Se d�connecte de la base de donn�es.
     * @return Tur si la base de donn�es � bien �t� d�connect�.
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
