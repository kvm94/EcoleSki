package be.marra.ecoleSki.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Moniteur;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.List;
import java.awt.Choice;
import javax.swing.JButton;

public class WAccreditation extends JFrame {

	private static final long serialVersionUID = -379075141277905022L;
	private JPanel contentPane;
	private Moniteur m;
	/**
	 * Create the frame.
	 */
	public WAccreditation(WMoniteur wMoniteur, Moniteur m) {
		setTitle("Accr\u00E9ditations");
		setResizable(false);
		
		this.m = m;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 235, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//[start]Contenus
		
		List list = new List();
		list.setBounds(10, 94, 205, 167);
		contentPane.add(list);
		
		Choice choiceSport = new Choice();
		choiceSport.setBounds(10, 41, 205, 20);
		contentPane.add(choiceSport);
		
		Choice choiceCategorie = new Choice();
		choiceCategorie.setBounds(10, 10, 205, 20);
		contentPane.add(choiceCategorie);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(126, 65, 89, 23);
		contentPane.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(10, 65, 89, 23);
		contentPane.add(btnSupprimer);

		//[end]
		
		//[start]Evènements
		
		//Fermeture de la fenêtre.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				wMoniteur.setEnabled(true);
			}
		});
		
		//[end]
	}
}
