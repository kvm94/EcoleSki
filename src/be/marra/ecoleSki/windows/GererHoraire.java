package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Cours;
import be.marra.ecoleSki.Moniteur;
import be.marra.ecoleSki.Reservation;
import be.marra.ecoleSki.Semaine;

import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GererHoraire extends JFrame {

	//[start]Attributs
	
	private static final long serialVersionUID = -2631472956541796875L;
	private JPanel contentPane;
	private List listCours;
	private List listDispo;
	private Moniteur m;

	//[end]
	
	public GererHoraire(WMoniteur wMoniteur, Moniteur m) {
		this.m = m;
		setTitle("G\u00E9rer horaire");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contenus
		
		listCours = new List();
		listCours.setBounds(10, 33, 185, 193);
		contentPane.add(listCours);
		
		listDispo = new List();
		listDispo.setBounds(256, 33, 178, 193);
		contentPane.add(listDispo);
		
		JLabel lblHeures = new JLabel("Nombre d'heures : ");
		lblHeures.setBounds(10, 246, 195, 14);
		contentPane.add(lblHeures);
		
		JLabel lblNewLabel = new JLabel("Cours \u00E0 donner :");
		lblNewLabel.setBounds(22, 13, 136, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCoursDisponnible = new JLabel("Cours disponnible : ");
		lblCoursDisponnible.setBounds(259, 13, 185, 14);
		contentPane.add(lblCoursDisponnible);
		
		JButton btnNewButton = new JButton("->");
		btnNewButton.setBounds(205, 103, 45, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<-");
		btnNewButton_1.setBounds(204, 137, 45, 23);
		contentPane.add(btnNewButton_1);
		
		//[end]
		
		initList();
		
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
	
	//[start]Méthodes
	
	private void initList(){
		ArrayList<Semaine> semaines = Semaine.loadSemainesFromDB();
		
		for(int i = 0 ; i< semaines.size(); i++){
			initListDispo(listDispo, semaines.get(i));
		}
	}
	
	private void initListCours(List list, Moniteur m){
		
	}
	
	private void initListDispo(List list, Semaine semaine){
		ArrayList<Cours> listeCours = new ArrayList<Cours>();
		try{
			listeCours = Cours.loadCoursByMonitor(0);
			for(int i=0; i< listeCours.size() ; i++){
				//Vérifie si il y a le nombre minimum de réservation pour ce cours.
				if(Reservation.getNbrReservationByCours(listeCours.get(i), semaine) >= listeCours.get(i).getMinEleve()){
					String s = "";
					s += semaine.getDateDebut().toString() + "  |  ";
					s += listeCours.get(i).getCategorie().toString() + "  |  ";
					s += listeCours.get(i).getSport().toString() + "  |  ";
					this.listDispo.add(s);
				}
			}
		}
		catch(Exception ex){
			if(ex ==null)
				JOptionPane.showMessageDialog(null, "Erreur lors du chargement de la liste des cours!");
			else
				JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	private double calculHeures(){
		return 0;
	}
	
	//[end]
}
