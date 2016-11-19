package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Moniteur;
import be.marra.ecoleSki.Reservation;

import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class GererHoraire extends JFrame {

	//[start]Attributs
	
	private static final long serialVersionUID = -2631472956541796875L;
	private JPanel contentPane;
	private List listCours;
	private List listDispo;
	private Moniteur m;
	private ArrayList<Reservation> listeCours;
	private ArrayList<Reservation> listeDispo;

	//[end]
	
	public GererHoraire(WMoniteur wMoniteur, Moniteur m) {
		this.m = m;
		setTitle("G\u00E9rer horaire");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contenus
		
		listCours = new List();
		listCours.setBounds(10, 33, 252, 228);
		contentPane.add(listCours);
		
		listDispo = new List();
		listDispo.setBounds(334, 33, 252, 228);
		contentPane.add(listDispo);
		
		JLabel lblNewLabel = new JLabel("Cours \u00E0 donner :");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(10, 13, 136, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCoursDisponnible = new JLabel("Cours disponnible : ");
		lblCoursDisponnible.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		lblCoursDisponnible.setBounds(335, 13, 185, 14);
		contentPane.add(lblCoursDisponnible);
		
		JButton btnNewButton = new JButton("->");
		btnNewButton.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnNewButton.setBounds(268, 92, 60, 33);
		contentPane.add(btnNewButton);
		btnNewButton.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("<-");
		btnNewButton_1.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnNewButton_1.setBounds(268, 137, 60, 33);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		
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
		
		//Ajoute un cours à l'horaire.
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Reservation temp = listeDispo.get(listDispo.getSelectedIndex());
					if(Reservation.checkAjoutMoniteur(m.getId(), temp.getHeure(), temp.getSemaine().getId())){
						//listeDispo.get(listDispo.getSelectedIndex()).setIdMoniteur(m.getId());
						temp.updateIDMonitor(m.getId());
						initList();
						btnNewButton_1.setEnabled(false);
					}
					else{
						throw new Exception("Vous possèdez déjà un cours pour cette période!");
					}
					
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		//Supprime un cours à l'horraire.
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//listeCours.get(listCours.getSelectedIndex()).setIdMoniteur(0);
					listeCours.get(listCours.getSelectedIndex()).updateIDMonitor(0);
					initList();
					btnNewButton.setEnabled(false);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());		
				}
			}
		});
		
		//Active le bouton d'ajout lors d'une sélection.
		listDispo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btnNewButton_1.setEnabled(true);
			}
		});
		
		//Active le bouton d'annulation lors d'une sélection.
		listCours.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				btnNewButton.setEnabled(true);
			}
		});
		
		//[end]
	}
	
	//[start]Méthodes
	
	private void initList(){
		listDispo.removeAll();
		listCours.removeAll();
		initListDispo(listDispo);
		initListCours(listCours);
	}
	
	@SuppressWarnings("deprecation")
	private void initListCours(List list){
		try{
			listeCours = m.checkReservations(m.getId());
			for(int i = 0 ; i<listeCours.size();i++){
				String s;
				s = listeCours.get(i).getSemaine().getDateDebut().toString() + "  |  ";
				s += listeCours.get(i).getHeure().getHours()+ "h  |  ";
				s += listeCours.get(i).getCours().getCategorie().toString() + "  |  ";
				s += listeCours.get(i).getCours().getSport().toString() + "  |  ";
				this.listCours.add(s);
			}
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void initListDispo(List list){
		try{
			listeDispo = m.checkReservations(0);
			for(int i = 0 ; i<listeDispo.size();i++){
				String s;
				s = listeDispo.get(i).getSemaine().getDateDebut().toString() + "  |  ";
				s += listeDispo.get(i).getHeure().getHours()+ "h  |  ";
				s += listeDispo.get(i).getCours().getCategorie().toString() + "  |  ";
				s += listeDispo.get(i).getCours().getSport().toString() + "  |  ";
				this.listDispo.add(s);
			}
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	//[end]
}
