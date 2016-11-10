package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Reservation;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextPane;

public class WReservation extends JFrame {
	
	//[start]Attributs

	private static final long serialVersionUID = -8711660953536509225L;
	private JPanel contentPane;
	private Reservation res;
	private JTextPane textPane;
	
	//[end]
	
	public WReservation(JFrame window, Reservation res) {
		
		setTitle("R\u00E9servation");
		setResizable(false);
		
		this.res =res;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 198, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contenus
		
		textPane = new JTextPane();
		textPane.setBounds(10, 11, 172, 200);
		contentPane.add(textPane);
		
		//[end]
		
		//Contenus du panel.
		initText();
		
		
		//[start]Evènements
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				window.setEnabled(true);
			}
		});
		
		//[end]
	}
	
	private void initText(){
		String text = "";
		
		text += res.getEleve().getNom() + "\n";
		text += res.getEleve().getPrenom() + "\n";
		text += res.getEleve().getDateNaissance() + "\n";
		text += res.getCours().getCategorie() + "\n";
		text += res.getCours().getSport() + "\n";
		text += res.getCours().getNiveaux() + "\n";
		text += res.getSemaine().getDateDebut() + " -> " + res.getSemaine().getDateFin() + "\n";
		text += res.getHeure() + "\n";
		text += res.getPrix() + "\n";
		text += res.getStatut() + "\n";
		
		this.textPane.setText(text);
		this.textPane.setEditable(false);
	}
}
