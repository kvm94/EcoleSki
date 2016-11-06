package be.marra.ecoleSki.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnexionSQLITE {
    private static Connection snglConnection = null;

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

    public void finalize()
    {
      disconnect();
    }
    
    public static Connection getInstance() {
        if (snglConnection == null) {
            new ConnexionSQLITE();
        }

        return snglConnection;
    }
    
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
