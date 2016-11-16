package be.marra.ecoleSki;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import be.marra.ecoleSki.windows.*;

public class Program
{
	/**
	 * @author Kevin Marra
	 * @version 1.0.1-stable
	 */
	public static void main(String[] args) throws SQLException
	{
		//Lance la fenêtre de connexion.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					//Applique le thème.
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}

					//Ne le lance qu'une seul fois sinon ça ralentit le démarrage du programme.
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
