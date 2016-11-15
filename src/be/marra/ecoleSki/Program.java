package be.marra.ecoleSki;

import java.awt.EventQueue;
import java.sql.SQLException;

import be.marra.ecoleSki.windows.*;

public class Program
{
	/**
	 * @author Kevin Marra
	 * @version 1.0.1-stable
	 */
	public static void main(String[] args) throws SQLException
	{
		//Lance la fen�tre de connexion.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Ne le lance qu'une seul fois sinon �a ralentit le d�marrage du programme.
					//Semaine.initSemaine();
					//Semaine.initConge();
					Authentification frame = new Authentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
