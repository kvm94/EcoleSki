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

public class WClient extends JFrame {

	//[start]Attributs.
	
	private static final long serialVersionUID = -58198079207033912L;
	private JPanel contentPane;
	private Client c;
	private WClient This = this;
	
	//[end]
	
	//[start]Cr�ation de la fen�tre.
	
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
		
		//Centre la fen�tre.
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
		list.setBounds(10, 58, 414, 174);
		contentPane.add(list);
		
		//[end]
		
		//[start]Events
		
		btnNouvelleRservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewReservation newReservation = new NewReservation(This);
				newReservation.setVisible(true);
				This.setEnabled(false);
			}
		});
		
		//[end]
		
		initList(list);
		
	}
	
	//[end]
	
	//[start]Fonctions
	
	private void initList(List list){
		try{
			ArrayList<Reservation> listeRes = new ArrayList<Reservation>();
			listeRes = Reservation.loadByIdClient(E_Statut.Paye, c.getId());
			Reservation temp;
			
			if(!listeRes.isEmpty()){
		
				for(int i=0 ; i< listeRes.size() ; i++){
					temp = listeRes.get(i);
					list.add(temp.toString());
					
				}
			}
			
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Erreur lors du chargement des r�servation!");
		}
		
	}
}
