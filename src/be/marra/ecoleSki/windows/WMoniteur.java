package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Moniteur;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class WMoniteur extends JFrame {

	//[start]Attributs
	
	private static final long serialVersionUID = -8176783639941141116L;
	private JPanel contentPane;
	private WMoniteur This = this;

	//[end]

	//[start]Création de la fenêtre.
	
	public WMoniteur(Moniteur m) {
		setResizable(false);
		setTitle("Moniteur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("G\u00E9rer les accr\u00E9ditations");
		btnNewButton.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnNewButton.setBounds(10, 11, 243, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("G\u00E9rer horaire");
		btnNewButton_2.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnNewButton_2.setBounds(264, 11, 243, 23);
		contentPane.add(btnNewButton_2);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 55, 497, 263);
		contentPane.add(calendar);
		
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Evènements
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEnabled(false);
				WAccreditation wAccreditation =  new WAccreditation(This, m);
				wAccreditation.setVisible(true);
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				GererHoraire gereH = new GererHoraire(This, m);
				gereH.setVisible(true);
			}
		});
		
		//[end]
	}
}
