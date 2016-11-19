package be.marra.ecoleSki.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import be.marra.ecoleSki.Client;
import be.marra.ecoleSki.Reservation;
import be.marra.ecoleSki.Reservation.E_Statut;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class WClient extends JFrame {

	//[start]Attributs.

	private static final long serialVersionUID = -58198079207033912L;
	private JPanel contentPane;
	private Client c;
	private WClient This = this;
	private ArrayList<Reservation> listRes;
	private JButton btnAfficher;
	private JTable tableau;
	private JScrollPane scrollPane;

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

		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		contentPane.setLayout(null);

		//[start]Contenus

		JButton btnPanier = new JButton("Panier");
		btnPanier.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnPanier.setBounds(6, 12, 89, 27);
		contentPane.add(btnPanier);

		JButton btnNouvelleRservation = new JButton("Nouvelle r\u00E9servation");
		btnNouvelleRservation.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnNouvelleRservation.setBounds(270, 12, 168, 27);
		contentPane.add(btnNouvelleRservation);

		btnAfficher = new JButton("Afficher");
		btnAfficher.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnAfficher.setBounds(335, 238, 89, 27);
		contentPane.add(btnAfficher);
		btnAfficher.setEnabled(false);

		String[] header = new String[] {"Semaine", "Heure", "El\u00E8ve", "Sport", "Niveaux"};
		Object[][] data = initDataTable();

		//Ajout des données dans la JTable.
		tableau = new JTable(data, header);

		//Ajout d'un scrollPane contenant ma JTable.
		scrollPane = new JScrollPane(tableau);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setLocation(6, 46);
		scrollPane.setSize(432, 185);
		this.getContentPane().add(scrollPane);

		JButton btnDconnexion = new JButton("D\u00E9connexion");
		btnDconnexion.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnDconnexion.setBounds(6, 237, 119, 28);
		contentPane.add(btnDconnexion);

		//[end]

		//[start]Events

		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				String[] header = new String[] {"Semaine", "Heure", "El\u00E8ve", "Sport", "Niveaux"};
				Object[][] data = initDataTable();
				tableau.setModel(new DefaultTableModel(data, header){
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int iRowIndex, int iColumnIndex)
					{
						return false;
					}
				});
				//Ajout de règles pour la JTable.
				tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableau.getColumnModel().getColumn(0).setMaxWidth(155);
				tableau.getColumnModel().getColumn(0).setMinWidth(155);
				tableau.getColumnModel().getColumn(1).setMinWidth(45);
				tableau.getColumnModel().getColumn(1).setMaxWidth(45);
				tableau.setRowSelectionAllowed(true);
				btnAfficher.setEnabled(false);
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});

		//Déconnecte le client.
		btnDconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vous déconnecter?");
				if(reply == JOptionPane.YES_OPTION){
					c.deconnexion();
					Authentification auth =  new Authentification();
					auth.setVisible(true);
					This.dispose();
				}
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
				WReservation wReservation = new WReservation(This, listRes.get(tableau.getSelectedRow()));
				wReservation.setVisible(true);
				tableau.clearSelection();
				btnAfficher.setEnabled(false);
				This.setEnabled(false);
			}
		});

		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				btnAfficher.setEnabled(true);
			}
		});
	}

	//[end]

	//[start]Fonctions

	@SuppressWarnings("deprecation")
	private Object[][] initDataTable(){		
		Object[][] data = null;
		try{
			this.listRes = new ArrayList<Reservation>();

			//Charge la liste des réservations d'un client, qui ont été payé.
			this.listRes = Reservation.loadByIdClient(E_Statut.Paye, c.getId());

			//Si la liste n'est pas vide.
			if(!this.listRes.isEmpty()){
				data = new Object[listRes.size()][5];

				//Récupération des données.
				for(int i = 0 ; i <listRes.size() ; i++){
					data[i][0] = listRes.get(i).getSemaine().toString();
					data[i][1] = String.valueOf(listRes.get(i).getHeure().getHours()) + "h00";
					data[i][2] = listRes.get(i).getEleve().getPrenom();
					data[i][3] = listRes.get(i).getCours().getSport().toString();
					data[i][4] = listRes.get(i).getCours().getNiveaux().toString();
				}				
			}
			else {
				data = new Object[0][0];
			}

		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Erreur lors du chargement des réservation!");
		}

		return data;
	}
}
