package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Client;
import be.marra.ecoleSki.Panier;
import be.marra.ecoleSki.Reservation;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class WPanier extends JFrame {

	private static final long serialVersionUID = 2252186507650817925L;
	private JPanel contentPane;
	private Panier p;
	private Client c;
	private JLabel lblNote;
	private JLabel lblTotal;
	private JLabel lblPrix;
	private JButton btnVider;
	ArrayList<Reservation> listeRes;
	private WPanier This = this;

	public WPanier(WClient wClient, Client c) {
		this.c = c;
		this.p = c.getPan();
		setTitle("Panier");
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
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(10, 237, 100, 23);
		contentPane.add(btnSupprimer);
		btnSupprimer.setEnabled(false);
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.setBounds(235, 237, 100, 23);
		contentPane.add(btnAfficher);
		btnAfficher.setEnabled(false);
		
		JButton btnPayer = new JButton("Payer");
		btnPayer.setBounds(345, 237, 89, 23);
		contentPane.add(btnPayer);
		
		
		lblTotal = new JLabel("Total : ");
		lblTotal.setBounds(304, 202, 46, 14);
		contentPane.add(lblTotal);
		
		lblPrix = new JLabel("");
		lblPrix.setBounds(360, 202, 46, 14);
		contentPane.add(lblPrix);
		
		List list = new List();
		list.setBounds(32, 10, 374, 186);
		contentPane.add(list);
		
		lblNote = new JLabel("");
		lblNote.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNote.setBounds(32, 202, 262, 14);
		contentPane.add(lblNote);
		
		btnVider = new JButton("Vider");
		btnVider.setBounds(120, 237, 105, 23);
		contentPane.add(btnVider);
		btnVider.setEnabled(false);
		
		//[end]
		
		initDisplay(list);		
		
		//[start]Events
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				wClient.setEnabled(true);
			}
		});
		
		btnPayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous payer?");
				if(reply == JOptionPane.YES_OPTION){
					c.payerPanier();
					wClient.setEnabled(true);
					This.dispose();
				}
			}
		});
		
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WReservation wReservation = new WReservation(This, listeRes.get(list.getSelectedIndex())); 
				wReservation.setVisible(true);
				This.setEnabled(false);
			}
		});
		
		btnVider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vider le panier ?");
				if(reply == JOptionPane.YES_OPTION){
					c.viderPanier();
					initDisplay(list);
					btnSupprimer.setEnabled(false);
					btnAfficher.setEnabled(false);
					btnVider.setEnabled(false);
				}
			}
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette réservation ?");
				if(reply == JOptionPane.YES_OPTION){
					p.supprimerReservation(list.getSelectedIndex());
					initDisplay(list);
					btnSupprimer.setEnabled(false);
					btnAfficher.setEnabled(false);
				}
					
			}
		});
		
		list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btnSupprimer.setEnabled(true);
				btnAfficher.setEnabled(true);
			}
		});
		
		//[end]
	}
	
	private void initDisplay(List list){
		try{
			c.initPanier();
			
			this.listeRes = p.getReservations();
			Reservation temp;
			
			list.removeAll();
			
			//Charge la liste
			if(!this.listeRes.isEmpty()){
				String item = "";
				for(int i=0 ; i< this.listeRes.size() ; i++){
					temp = this.listeRes.get(i);
					
					item += temp.getSemaine().getDateDebut().toString() + " -> " + temp.getSemaine().getDateFin().toString() + "   |   ";
					item += temp.getHeure().toString() + "   |   ";
					item += temp.getCours().getSport().toString() + "   |   ";
					item += temp.getCours().getNiveaux().toString() + "   |   ";
					
					list.add(item);
					
					item = "";
					
				}
				
				//Affiche la note et le prix du panier.
				this.lblPrix.setText(String.valueOf(p.getTotal() + "€"));
				if(p.isReduction())
					this.lblNote.setText("*Réduction de 15% sur votre total!");
				
				this.btnVider.setEnabled(true);
			}
			
			
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Erreur lors du chargement des réservation!");
		}
		
	}
}
