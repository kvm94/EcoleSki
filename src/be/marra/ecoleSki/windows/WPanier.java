package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.marra.ecoleSki.Client;
import be.marra.ecoleSki.Panier;
import be.marra.ecoleSki.Reservation;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class WPanier extends JFrame {

	private static final long serialVersionUID = 2252186507650817925L;
	private JPanel contentPane;
	private Panier p;
	private Client c;
	private JLabel lblNote;
	private JLabel lblTotal;
	private JLabel lblPrix;
	private JButton btnVider;
	private ArrayList<Reservation> listRes;
	private WPanier This = this;
	private JScrollPane scrollPane;
	private JTable tableau;

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
		btnSupprimer.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnSupprimer.setBounds(10, 237, 100, 25);
		contentPane.add(btnSupprimer);
		btnSupprimer.setEnabled(false);
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnAfficher.setBounds(235, 237, 100, 25);
		contentPane.add(btnAfficher);
		btnAfficher.setEnabled(false);
		
		JButton btnPayer = new JButton("Payer");
		btnPayer.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnPayer.setBounds(345, 237, 89, 25);
		contentPane.add(btnPayer);
		
		
		lblTotal = new JLabel("Total : ");
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTotal.setBounds(304, 202, 46, 14);
		contentPane.add(lblTotal);
		
		lblPrix = new JLabel("");
		lblPrix.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPrix.setBounds(360, 202, 46, 14);
		contentPane.add(lblPrix);
		
		lblNote = new JLabel("");
		lblNote.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNote.setBounds(32, 202, 262, 14);
		contentPane.add(lblNote);
		
		btnVider = new JButton("Vider");
		btnVider.setFont(new Font("Segoe UI Black", Font.ITALIC, 12));
		btnVider.setBounds(120, 237, 105, 25);
		contentPane.add(btnVider);
		btnVider.setEnabled(false);
		
		String[] header = new String[] {"Semaine", "Heure", "El\u00E8ve", "Sport", "Niveaux"};
		Object[][] data = initDataTable();
		initDisplay();
		
		//Ajout des données dans la JTable.
		tableau = new JTable(new DefaultTableModel(data, header){
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
		
		scrollPane = new JScrollPane(tableau);
		scrollPane.setBounds(12, 12, 420, 177);
		contentPane.add(scrollPane);
		
		//[end]
		
		//[start]Events
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				wClient.setEnabled(true);
			}
		});
		
		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            btnAfficher.setEnabled(true);
	            btnSupprimer.setEnabled(true);
	        }
	    });
		
		btnPayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous payer?");
				if(reply == JOptionPane.YES_OPTION){
					try{
						c.payerPanier();
						wClient.setEnabled(true);
						
						This.dispose();
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					
				}
			}
		});
		
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WReservation wReservation = new WReservation(This, listRes.get(tableau.getSelectedRow())); 
				wReservation.setVisible(true);
				tableau.clearSelection();
				btnAfficher.setEnabled(false);
				btnSupprimer.setEnabled(false);
				This.setEnabled(false);

			}
		});
		
		btnVider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous vider le panier ?");
				if(reply == JOptionPane.YES_OPTION){
					c.viderPanier();
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
					initDisplay();
					lblPrix.setText("");
					lblNote.setText("");
					btnSupprimer.setEnabled(false);
					btnAfficher.setEnabled(false);
					btnVider.setEnabled(false);
					btnPayer.setEnabled(false);
				}
			}
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette réservation ?");
				if(reply == JOptionPane.YES_OPTION){
					p.supprimerReservation(tableau.getSelectedRow());
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
					initDisplay();
					lblPrix.setText("");
					lblNote.setText("");
					if(data.length == 0)
						btnPayer.setEnabled(false);
					btnSupprimer.setEnabled(false);
					btnAfficher.setEnabled(false);
					btnVider.setEnabled(false);
				}
					
			}
		});
		
		//[end]
	}
	
	@SuppressWarnings("deprecation")
	private Object[][] initDataTable(){		
		Object[][] data = null;
		try{
			c.initPanier();
			
			this.listRes = p.getReservations();
			//Si la liste n'est pas vide.
			if(!listRes.isEmpty()){
				data = new Object[listRes.size()][5];
				
				//Récupération des données.
				for(int i = 0 ; i < listRes.size() ; i++){
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
	
	private void initDisplay(){
		//Affiche la note et le prix du panier.
		this.lblPrix.setText(String.valueOf(p.getTotal() + "€"));
		if(p.isReduction())
			this.lblNote.setText("*Réduction de 15% sur votre total!");
		
		this.btnVider.setEnabled(true);
	}
}
