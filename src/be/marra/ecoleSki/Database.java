package be.marra.ecoleSki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
  private String dbName;
  public Connection connection;
  private Statement requete;
  
  public Database(String dbName)
  {
    try
    {
      Class.forName("org.sqlite.JDBC");
    }
    catch (ClassNotFoundException e1)
    {
      System.err.println(e1.getMessage());
    }
    this.dbName = dbName;
    this.connection = null;
  }
  
  public void finalize()
  {
    disconnect();
  }
  
  public boolean connect()
  {
    try
    {
      this.connection = DriverManager.getConnection("jdbc:sqlite:data/" + this.dbName);
      
      this.requete = this.connection.createStatement();
      
      this.requete.executeUpdate("PRAGMA synchronous = OFF;");
      this.requete.setQueryTimeout(30);
      
      return true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean disconnect()
  {
    try
    {
      if (this.connection != null) {
        this.connection.close();
      }
      return true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public ResultSet getResultOf(String requete)
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
  
  public void updateValue(String requete)
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
