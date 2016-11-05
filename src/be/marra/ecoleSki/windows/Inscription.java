package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Client;
import be.marra.ecoleSki.Moniteur;
import be.marra.ecoleSki.Utilisateur;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;

public class Inscription extends JFrame {

	//[start]Attibuts
	
	private static final long serialVersionUID = -4677002750105878565L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrnom;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField textFieldJour;
	private JTextField textFieldMois;
	private JTextField textFieldAnnee;
	private Inscription This = this;
	
	//[end]
	
	//[start]Create the frame
	
	public Inscription(Authentification auth) {
		setResizable(false);
		
		auth.setEnabled(false);
		setTitle("Inscription");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 284, 291);
		
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contents
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(10, 58, 118, 14);
		contentPane.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setBounds(10, 83, 118, 14);
		contentPane.add(lblPrnom);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(10, 108, 118, 14);
		contentPane.add(lblMotDePasse);
		
		JLabel lblConfirmation = new JLabel("Confirmation :");
		lblConfirmation.setBounds(10, 133, 118, 14);
		contentPane.add(lblConfirmation);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance : ");
		lblDateDeNaissance.setBounds(10, 158, 118, 14);
		contentPane.add(lblDateDeNaissance);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(138, 55, 120, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrnom = new JTextField();
		textFieldPrnom.setBounds(138, 80, 120, 20);
		contentPane.add(textFieldPrnom);
		textFieldPrnom.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(138, 105, 120, 20);
		contentPane.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(138, 130, 120, 20);
		contentPane.add(confirmPasswordField);
		
		textFieldJour = new JTextField();
		textFieldJour.setText("JJ");
		textFieldJour.setBounds(138, 155, 28, 20);
		contentPane.add(textFieldJour);
		textFieldJour.setColumns(10);
		
		JLabel label = new JLabel("/");
		label.setBounds(170, 158, 4, 14);
		contentPane.add(label);
		
		textFieldMois = new JTextField();
		textFieldMois.setText("MM");
		textFieldMois.setBounds(178, 155, 28, 20);
		contentPane.add(textFieldMois);
		textFieldMois.setColumns(10);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(210, 158, 4, 14);
		contentPane.add(label_1);
		
		textFieldAnnee = new JTextField();
		textFieldAnnee.setText("AAAA");
		textFieldAnnee.setBounds(217, 155, 41, 20);
		contentPane.add(textFieldAnnee);
		textFieldAnnee.setColumns(10);
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.setBounds(141, 218, 102, 23);
		contentPane.add(btnInscription);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(21, 218, 102, 23);
		contentPane.add(btnAnnuler);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur :");
		lblUtilisateur.setBounds(10, 33, 118, 14);
		contentPane.add(lblUtilisateur);
		
		Choice choice = new Choice();
		choice.setBounds(138, 29, 120, 20);
		contentPane.add(choice);
		choice.add("Client");
		choice.add("Moniteur");
		
		//[end]
		
		//[start]Event
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				auth.setEnabled(true);
			}
		});
		
		btnInscription.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				Utilisateur u;
				
				try{
					//Vérifie que tous les champs sont bien remplies.
					if(!textFieldNom.getText().isEmpty() && !textFieldPrnom.getText().isEmpty() && !passwordField.getText().isEmpty() && !confirmPasswordField.getText().isEmpty() 
							&& !textFieldJour.getText().isEmpty() && !textFieldMois.getText().isEmpty() && !textFieldAnnee.getText().isEmpty()){
						
						//Vérifie que le mot de passe = comfirmation.
						if(passwordField.getText().equals(confirmPasswordField.getText())){
							//Définis le type d'utilisateur.
							if(choice.getSelectedItem() == "Client"){
								u = new Client();
							}
							else{
								u = new Moniteur();		
							}				
							//Récupère les données.
							u.setNom(textFieldNom.getText());
							u.setPrenom(textFieldPrnom.getText());
							u.setPasswd(passwordField.getText());
							
							try{
								int jour, mois, annee;
								jour = Integer.parseInt(textFieldJour.getText());
								mois = Integer.parseInt(textFieldMois.getText());
								annee = Integer.parseInt(textFieldAnnee.getText());
								u.setDateNaissance(LocalDate.of(annee, mois, jour));
							}
							catch(Exception ex){
								throw new Exception("Date invalide!");
							}
							
							//Inscription dans la base de données.
							if(u.inscription()){
								auth.setEnabled(true);
								JOptionPane.showMessageDialog(null, "Inscription réussie!");
								This.dispose();
							}
							else
								throw new Exception("Erreur lors de l'inscription!");
						}
						else
							throw new Exception("Mot et passe différent de confirmation!");
					}
					else
						throw new Exception("Champs manquants");
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}				
			}
		});
		
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auth.setEnabled(true);
				This.dispose();
			}
		});
		
		//[end]
	}
}
