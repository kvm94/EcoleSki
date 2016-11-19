package be.marra.ecoleSki.windows;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Accreditation;
import be.marra.ecoleSki.Accreditation.E_Categorie;
import be.marra.ecoleSki.Accreditation.E_Sport;
import be.marra.ecoleSki.Moniteur;
import be.marra.ecoleSki.Reservation;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.Choice;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class WAccreditation extends JFrame {

	private static final long serialVersionUID = -379075141277905022L;
	private JPanel contentPane;
	private Moniteur m;
	private List list;
	private Choice choiceSport;
	private Choice choiceCategorie;
	private JButton btnSupprimer;
	private ArrayList<Reservation> listeReservation;
	/**
	 * Create the frame.
	 */
	public WAccreditation(WMoniteur wMoniteur, Moniteur m) {
		setTitle("Accr\u00E9ditations");
		setResizable(false);
		
		this.m = m;
		try{
			m.initAccreditations();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 257, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contenus
		
		list = new List();
		list.setBounds(10, 94, 231, 206);
		contentPane.add(list);
		
		choiceSport = new Choice();
		choiceSport.setBounds(10, 41, 231, 20);
		contentPane.add(choiceSport);
		choiceSport.add("Ski");
		choiceSport.add("Snowboard");
		choiceSport.add("Ski de fond");
		choiceSport.add("Télémark");
		
		choiceCategorie = new Choice();
		choiceCategorie.setBounds(10, 10, 231, 20);
		contentPane.add(choiceCategorie);
		choiceCategorie.add("Adulte");
		choiceCategorie.add("Enfant");
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnAjouter.setBounds(134, 67, 107, 25);
		contentPane.add(btnAjouter);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnSupprimer.setBounds(10, 65, 107, 25);
		contentPane.add(btnSupprimer);

		//[end]
		try{
			initList();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		
		//[start]Evènements
		
		//Active le bouton Supprimer si on choisis un item dans la liste.
		list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btnSupprimer.setEnabled(true);
			}
		});
		
		//Fermeture de la fenêtre.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				wMoniteur.setEnabled(true);
			}
		});
		
		//Suppression d'un accréditation séléctionné.
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					listeReservation = m.checkReservations(m.getId());
					m.supprimerAccreditation(list.getSelectedIndex());
					//Supprime les cours de l'horraire qui avaient besoin de l'accreditation supprimé.	
					ArrayList<Accreditation> listeAccre = m.getAccreditations();
					
					for(int i = 0 ; i< listeReservation.size() ; i ++){
						if(!listeReservation.get(i).getCours().checkAccreditation(listeAccre)){
							//listeReservation.get(i).setIdMoniteur(0);
							listeReservation.get(i).updateIDMonitor(0);
							JOptionPane.showMessageDialog(null, "Les cours à l'horaire nécessitant cette accréditation ont été supprimé!");
						}
					}
					initList();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Erreur lors de la suppression!");
				}
			}
		});
		
		//Ajoute une accréditation.
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Accreditation a;
				E_Categorie categorie;
				E_Sport sport;
				
				try{
					switch(choiceCategorie.getSelectedItem()){
					case "Adulte": categorie = E_Categorie.Adulte;break;
					case "Enfant" : categorie = E_Categorie.Enfant;break;
					default: categorie =null; break;
					}
					
					switch(choiceSport.getSelectedItem()){
					case "Ski" : sport = E_Sport.Ski;break;
					case "Snowboard" : sport = E_Sport.Snowboard;break;
					case "Télémark" : sport = E_Sport.Telemark;break;
					case "Ski de fond" : sport = E_Sport.SkiFond;break;
					default : sport = null;
					}
					
					a = new Accreditation(categorie, sport);
					m.ajoutAccreditation(a);
					
					initList();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		//[end]
	}
	
	//[start]Méthodes
	
	
	public void initList(){
		try{
			btnSupprimer.setEnabled(false);
			
			ArrayList<Accreditation> listeA = m.getAccreditations();
			
			list.removeAll();
			
			for(int i=0; i<listeA.size() ; i++){
				list.add(listeA.get(i).getCat().toString() + "   |   " + listeA.get(i).getSport().toString());
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Erreur lors du chargement des accréditations!");
		}
	}
	
	//[end]
}
