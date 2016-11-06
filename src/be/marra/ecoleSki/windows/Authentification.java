package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import be.marra.ecoleSki.Client;
import be.marra.ecoleSki.Moniteur;
import be.marra.ecoleSki.Utilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Choice;

public class Authentification extends JFrame {

	//[start] Attributs 
	
	private static final long serialVersionUID = 8056602179250665675L;
	private JPanel contentPane;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private Authentification This = this;
	private JPasswordField passwordField;
	
	//[end]
	
	//[start]Create the frame.
	
	public Authentification() {
		setResizable(false);
		setTitle("Authentification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 324, 211);
		
		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contents
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(50, 48, 89, 14);
		contentPane.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setBounds(50, 73, 89, 14);
		contentPane.add(lblPrnom);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe:");
		lblMotDePasse.setBounds(50, 98, 89, 14);
		contentPane.add(lblMotDePasse);
		
		textField_nom = new JTextField();
		textField_nom.setBounds(166, 45, 100, 20);
		contentPane.add(textField_nom);
		textField_nom.setColumns(10);
		
		textField_prenom = new JTextField();
		textField_prenom.setBounds(166, 70, 100, 20);
		contentPane.add(textField_prenom);
		textField_prenom.setColumns(10);
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.setBounds(50, 133, 103, 23);
		contentPane.add(btnInscription);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(163, 133, 103, 23);
		contentPane.add(btnConnexion);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur :");
		lblUtilisateur.setBounds(50, 23, 89, 14);
		contentPane.add(lblUtilisateur);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(166, 95, 100, 20);
		contentPane.add(passwordField);
		
		Choice choice = new Choice();
		choice.setBounds(166, 19, 100, 20);
		contentPane.add(choice);
		choice.add("Client");
		choice.add("Moniteur");
		
		//[end]
		
		//[start]Events
		
		//Event Bouton Inscription
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inscription windowInscription = new Inscription(This);
				windowInscription.setVisible(true);
			}
		});
		
		//Event bouton Connexion
		btnConnexion.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					Utilisateur u;
					//Vérifie que tous les champs sont remplies.
					if(!textField_nom.getText().isEmpty() && !textField_prenom.getText().isEmpty() && !passwordField.getText().isEmpty()){
						
						//Définis le type d'utilisateur.
						if(choice.getSelectedItem() == "Client")
							u = new Client();
						else
							u= new Moniteur();
						
						//Récupération des données saisies.
						u.setNom(textField_nom.getText());
						u.setPrenom(textField_prenom.getText());
						u.setPasswd(passwordField.getText());
						
						//Vérifie si la connexion c'est bien passée.
						if(u.connexion()){
							//Vérifie si l'utilisateur est un client ou un moniteur.
							if(u.getClass().getName().contains("Client")){ //On ouvre une fenêtre Client.
								WClient wClient = new WClient((Client)u);
								wClient.setVisible(true);							
							}
							else //L'utilisateur est un moniteur, on ouvre une fenêtre moniteur.
							{
								WMoniteur wMoniteur = new WMoniteur((Moniteur)u);
								wMoniteur.setVisible(true);
							}
							This.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Erreur de connexion!");
					}
					else
						JOptionPane.showMessageDialog(null, "Champs manquants!");
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		//[end]
	}
	//[end]
}
