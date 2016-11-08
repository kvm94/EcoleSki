package be.marra.ecoleSki;

import java.awt.EventQueue;
import java.sql.SQLException;

import be.marra.ecoleSki.windows.*;

public class Program
{
	public static void main(String[] args) throws SQLException
	{
		//Lance la fenêtre de connexion.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification frame = new Authentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
