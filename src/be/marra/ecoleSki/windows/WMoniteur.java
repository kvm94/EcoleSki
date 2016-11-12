package be.marra.ecoleSki.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Moniteur;

public class WMoniteur extends JFrame {

	//[start]Attributs
	
	private static final long serialVersionUID = -8176783639941141116L;
	private JPanel contentPane;

	//[end]

	//[start]Création de la fenêtre.
	
	public WMoniteur(Moniteur m) {
		setTitle("Moniteur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contenus
		
		//[end]
	}
	
	//[end]

}
