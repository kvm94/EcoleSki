package be.marra.ecoleSki.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Moniteur;
import be.marra.ecoleSki.Reservation;
import be.marra.ecoleSki.Semaine;

import javax.swing.JButton;
import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class WMoniteur extends JFrame {

	//[start]Attributs

	private static final long serialVersionUID = -8176783639941141116L;
	private JPanel contentPane;
	private WMoniteur This = this;
	private JCalendar calendar;
	private Moniteur m;

	//[end]

	//[start]Création de la fenêtre.

	public WMoniteur(Moniteur m) {

		this.m = m;
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
		btnNewButton.setBounds(10, 11, 243, 28);
		contentPane.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("G\u00E9rer horaire");
		btnNewButton_2.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnNewButton_2.setBounds(264, 11, 243, 28);
		contentPane.add(btnNewButton_2);

		calendar = new JCalendar();
		calendar.setBounds(10, 55, 497, 255);
		contentPane.add(calendar);

		JButton btnDconnexion = new JButton("D\u00E9connexion");
		btnDconnexion.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnDconnexion.setBounds(6, 318, 134, 28);
		contentPane.add(btnDconnexion);

		JLabel lblLesCasesGrises = new JLabel("Les cases gris\u00E9es repr\u00E9sentent un jour de travail.");
		lblLesCasesGrises.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblLesCasesGrises.setBounds(175, 324, 274, 16);
		contentPane.add(lblLesCasesGrises);

		JButton btnRefresh = new JButton("");
		btnRefresh.setIcon(new ImageIcon(WMoniteur.class.getResource("/be/marra/ecoleSki/windows/Refresh_icon_s.png")));
		btnRefresh.setBounds(464, 310, 43, 40);
		contentPane.add(btnRefresh);
		btnRefresh.setSelectedIcon(null);

		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

		try {
			colorCalendar();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//[start]Evènements


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEnabled(false);
				WAccreditation wAccreditation =  new WAccreditation(This, m);
				wAccreditation.setVisible(true);
			}
		});

		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					colorCalendar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				GererHoraire gereH = new GererHoraire(This, m);
				gereH.setVisible(true);
			}
		});

		btnDconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vous déconnecter?");
				if(reply == JOptionPane.YES_OPTION){
					m.deconnexion();
					Authentification auth = new Authentification();
					auth.setVisible(true);
					dispose();
				}
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try {
					colorCalendar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		//[end]
	}

	//[start]Méthodes

	private void colorCalendar() throws Exception{
		Semaine semaine;
		Calendar cal = Calendar.getInstance();
		JPanel panel = calendar.getDayChooser().getDayPanel();
		Component[] components  = panel.getComponents();
		ArrayList<Reservation> coursMoniteur = m.checkReservations(m.getId());
		int month = calendar.getMonthChooser().getMonth()+1;
		int year = calendar.getYearChooser().getYear();

		for(int i = 0; i < coursMoniteur.size(); i++)
		{
			semaine = coursMoniteur.get(i).getSemaine();
			for(int j = 0 ; j < 7 ;j++){
				if(month == semaine.getDateDebut().getMonthValue() && year == semaine.getDateDebut().getYear())
				{

					// Calculate the offset of the first day of the month
					cal.set(Calendar.DAY_OF_MONTH,1);
					int offset = cal.get(Calendar.DAY_OF_WEEK) - 1;

					if((semaine.getDateDebut().getDayOfMonth() + j + offset + 7) < components.length){
						components[ semaine.getDateDebut().getDayOfMonth() + j + offset + 7].setBackground(Color.DARK_GRAY); 
						components[ semaine.getDateDebut().getDayOfMonth() + j + offset + 7].setForeground(Color.WHITE);; 
					}

				}
			}

		}
	}
}
