package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Client;
import be.marra.ecoleSki.Reservation;
import be.marra.ecoleSki.Reservation.E_Statut;

import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class WClient extends JFrame {

	//[start]Attributs.
	
	private static final long serialVersionUID = -58198079207033912L;
	private JPanel contentPane;
	private Client c;
	private WClient This = this;
	private ArrayList<Reservation> listRes;
	private JButton btnAfficher;
	
	//[end]
	
	//[start]Création de la fenêtre.
	
	public WClient(Client c) {
		setResizable(false);
		this.c = c; 
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contenus
		
		JButton btnPanier = new JButton("Panier");
		btnPanier.setBounds(10, 11, 89, 23);
		contentPane.add(btnPanier);
		
		JButton btnNouvelleRservation = new JButton("Nouvelle r\u00E9servation");
		btnNouvelleRservation.setBounds(256, 11, 168, 23);
		contentPane.add(btnNouvelleRservation);
		
		List list = new List();
		list.setBounds(10, 58, 414, 168);
		contentPane.add(list);
		
		btnAfficher = new JButton("Afficher");
		btnAfficher.setBounds(300, 237, 89, 23);
		contentPane.add(btnAfficher);
		btnAfficher.setEnabled(false);
		
		//[end]
		
		//[start]Events
		
		//Réinitialise la liste des réservation quand la fenêtre reprend le focus.
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				initList(list);
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		
		//Afficher la fenêtre d'une nouvelle réservation.
		btnNouvelleRservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewReservation newReservation = new NewReservation(This, c);
				newReservation.setVisible(true);
				This.setEnabled(false);
			}
		});
		
		//Affiche la fenêtre du panier.
		btnPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.initPanier();
				if(!c.getPan().isEmpty()){
					WPanier wPanier = new WPanier(This, c);
					wPanier.setVisible(true);
					This.setEnabled(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Le panier est vide!");
			}
		});
		
		//Affiche les détaille d'une réservation.
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WReservation wReservation = new WReservation(This, listRes.get(list.getSelectedIndex()));
				wReservation.setVisible(true);
				This.setEnabled(false);
				btnAfficher.setEnabled(false);
				
			}
		});
		
		//Active le bouton "afficher" lorsqu'un item de la liste est sélèctionné.
		list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btnAfficher.setEnabled(true);
			}
		});
		
		//[end]
		
		initList(list);		
	}
	
	//[end]
	
	//[start]Fonctions
	
	private void initList(List list){
		try{
			this.listRes = new ArrayList<Reservation>();
			this.listRes = Reservation.loadByIdClient(E_Statut.Paye, c.getId());
			Reservation temp;
			
			list.removeAll();
			
			if(!this.listRes.isEmpty()){
				String item = "";
				for(int i=0 ; i< this.listRes.size() ; i++){
					temp = this.listRes.get(i);
					
					item += temp.getSemaine().getDateDebut().toString() + " -> " + temp.getSemaine().getDateFin().toString() + "   |   ";
					item += temp.getHeure().toString() + "   |   ";
					item += temp.getCours().getSport().toString() + "   |   ";
					item += temp.getCours().getNiveaux().toString() + "   |   ";
					
					list.add(item);
					
					item = "";
				}
			}
			
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Erreur lors du chargement des réservation!");
		}
		
	}
	
	//[end]
}
