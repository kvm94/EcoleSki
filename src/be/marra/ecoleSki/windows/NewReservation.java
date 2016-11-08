package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Accreditation.E_Categorie;
import be.marra.ecoleSki.Accreditation.E_Sport;
import be.marra.ecoleSki.Cours;
import be.marra.ecoleSki.Cours.E_Niveaux;
import be.marra.ecoleSki.Eleve;
import be.marra.ecoleSki.Semaine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NewReservation extends JFrame {

	//[start]Attributs 

	private static final long serialVersionUID = 8739718088290315678L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField txtJj;
	private JTextField txtMm;
	private JTextField txtAaaa;
	private ArrayList<Semaine> semaines;
	private Eleve eleve;
	private Cours cours;
	private Choice choiceSport;
	private Choice choiceNiveaux;
	private Choice choiceSemaine;
	private Choice choiceHeure;
	private boolean confirmed;

	//[end]

	public NewReservation(WClient wClient) {
		this.confirmed = false;
		setResizable(false);
		setTitle("Nouvelle r\u00E9servation");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 342, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Centre la fenêtre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

		//[start]Contenus

		initComboBox();

		JLabel lblNewLabel = new JLabel("Nom \u00E9l\u00E8ve :");
		lblNewLabel.setBounds(10, 11, 151, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPrnomlve = new JLabel("Pr\u00E9nom \u00E9l\u00E8ve : ");
		lblPrnomlve.setBounds(10, 36, 151, 14);
		contentPane.add(lblPrnomlve);

		JLabel lblDateDeNaissance = new JLabel("Date de naissance :");
		lblDateDeNaissance.setBounds(10, 61, 151, 14);
		contentPane.add(lblDateDeNaissance);

		JLabel lblAssurance = new JLabel("Assurance ?");
		lblAssurance.setBounds(10, 86, 151, 14);
		contentPane.add(lblAssurance);

		JLabel lblsemaine = new JLabel("15\u20AC/semaine");
		lblsemaine.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblsemaine.setBounds(10, 100, 151, 14);
		contentPane.add(lblsemaine);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 159, 414, 2);
		contentPane.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Semaine : ");
		lblNewLabel_1.setBounds(10, 180, 103, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblHeure = new JLabel("Heure : ");
		lblHeure.setBounds(10, 205, 67, 14);
		contentPane.add(lblHeure);

		JLabel lblSport = new JLabel("Sport : ");
		lblSport.setBounds(10, 230, 98, 14);
		contentPane.add(lblSport);

		JLabel lblNiveaux = new JLabel("Niveaux : ");
		lblNiveaux.setBounds(10, 255, 98, 14);
		contentPane.add(lblNiveaux);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 298, 414, 2);
		contentPane.add(separator_1);

		JLabel lblPrix = new JLabel("Prix : ");
		lblPrix.setBounds(10, 315, 46, 14);
		contentPane.add(lblPrix);

		JButton btnCalculPrix = new JButton("Calcul prix");
		btnCalculPrix.setBounds(213, 311, 109, 23);
		contentPane.add(btnCalculPrix);
		btnCalculPrix.setEnabled(false);

		JButton btnRserver = new JButton("R\u00E9server");
		btnRserver.setBounds(175, 356, 89, 23);
		contentPane.add(btnRserver);
		btnRserver.setEnabled(false);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(63, 356, 89, 23);
		contentPane.add(btnAnnuler);

		JLabel lblCalculPrix = new JLabel("");
		lblCalculPrix.setBounds(63, 315, 98, 14);
		contentPane.add(lblCalculPrix);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(171, 8, 151, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(171, 33, 151, 20);
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		txtJj = new JTextField();
		txtJj.setText("JJ");
		txtJj.setBounds(171, 58, 29, 20);
		contentPane.add(txtJj);
		txtJj.setColumns(10);

		JLabel label = new JLabel("/");
		label.setBounds(208, 61, 46, 14);
		contentPane.add(label);

		txtMm = new JTextField();
		txtMm.setText("MM");
		txtMm.setBounds(218, 58, 29, 20);
		contentPane.add(txtMm);
		txtMm.setColumns(10);

		JLabel label_1 = new JLabel("/");
		label_1.setBounds(251, 61, 46, 14);
		contentPane.add(label_1);

		txtAaaa = new JTextField();
		txtAaaa.setText("AAAA");
		txtAaaa.setBounds(264, 58, 58, 20);
		contentPane.add(txtAaaa);
		txtAaaa.setColumns(10);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(167, 91, 97, 23);
		contentPane.add(checkBox);

		JButton btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setBounds(213, 125, 109, 23);
		contentPane.add(btnConfirmer);


		//[end]

		//[start]Events

		//Réactive la fenêtre précédente lors de la fermeture.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				wClient.setEnabled(true);
			}
		});

		//Calcul le prix de la réservation, initialise l'objet Cours
		btnCalculPrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					double prixCalcul;
					cours = new Cours();
					E_Sport sport;
					E_Niveaux niveaux = null;
					E_Categorie categorie;
					int heure;
					boolean collectif;

					if(eleve.age() >= 18)
						categorie = E_Categorie.Adulte;
					else
						categorie = E_Categorie.Enfant;

					if(categorie == E_Categorie.Adulte){
						switch(choiceSport.getSelectedItem().toString()){
						case "Ski" : 
							sport = E_Sport.Ski;
							switch(choiceNiveaux.getSelectedItem().toString()){
							case "Niveaux 1 à 4":  niveaux = E_Niveaux.nv1_nv4; break;
							case "Hors-piste": niveaux = E_Niveaux.HorsPiste ; break;  
							case "Compétition": niveaux = E_Niveaux.Competition; break; 
							default: niveaux = null; break;
							}
							break;
						case "Snowboard": 
							sport = E_Sport.Snowboard;
							switch(choiceNiveaux.getSelectedItem().toString()){
							case "Niveaux 1 à 4": niveaux = E_Niveaux.nv1_nv4; break;	
							case "Hors-piste": niveaux = E_Niveaux.HorsPiste; break;
							default:niveaux =null; break;
							}
							break;
						case "Télémark" :
							sport = E_Sport.Telemark;
							niveaux = E_Niveaux.nv1_nv4;
							break;
						case "Ski de fond" : 
							sport = E_Sport.SkiFond;
							niveaux = E_Niveaux.nv1_nv4;
							break;
						default: sport =null; break;
						}
					}
					else{ //l'élève est mineur.
						switch(choiceSport.getSelectedItem().toString()){
						case "Ski" :  
							sport = E_Sport.Ski;
							switch(choiceNiveaux.getSelectedItem().toString()){
							case "Petit Spirou": niveaux = E_Niveaux.PetitSpirou; break;
							case "Bronze": niveaux = E_Niveaux.Bronze; break;
							case "Argent": niveaux = E_Niveaux.Argent; break; 
							case "Or": niveaux = E_Niveaux.Or; break; 
							case "Platine": niveaux = E_Niveaux.Platine; break; 
							case "Diamant": niveaux = E_Niveaux.Diamant; break; 
							case "Compétition": niveaux = E_Niveaux.Competition; break;
							case "Hors-piste": niveaux = E_Niveaux.HorsPiste; break; 
							default: niveaux = null; break;
							}
							break;
						case "Snowboard": 
							sport = E_Sport.Snowboard;
							switch(choiceNiveaux.getSelectedItem().toString()){
							case "Niveaux 1": niveaux = E_Niveaux.nv1; break;	
							case "Niveaux 1 à 4": niveaux = E_Niveaux.nv1_nv4; break;	
							case "Hors-piste": niveaux = E_Niveaux.HorsPiste; break;	
							default: niveaux  = null; break;
							}
							break;
						case "Télémark" :
							sport = E_Sport.Telemark;
							niveaux = E_Niveaux.nv1_nv4;
							break;
						case "Ski de fond" : 
							sport = E_Sport.SkiFond;
							niveaux = E_Niveaux.nv1_nv4;
							break;
						default: sport =null; break;
						}

					}
				
					switch(choiceHeure.getSelectedItem().toString()){
					case "9h00 -> 12h00": 
						heure = 3;
						collectif = true;
						break;
					case "12h00 -> 13h00 (Cours particulier)": 
						heure=1;
						collectif = false;
						break;
					case "12h00 -> 14h00 (Cours particulier)": 
						heure = 2 ; 
						collectif = false;
						break;
					case "14h00 -> 17h00" : 
						heure = 3;
						collectif = true;
						break;
					default: 
						collectif = false;
						heure=0;
						break;
					}
					
					cours.setCategorie(categorie);
					cours.setNiveaux(niveaux);
					cours.setSport(sport);
					cours.setCollectif(collectif);
					cours.setHeure(heure);
					
					cours.initPrix();
					
					prixCalcul = cours.getPrix();
					if(checkBox.isSelected())
						prixCalcul += 15;
						
						
					lblCalculPrix.setText(String.valueOf(prixCalcul) + "€");
					
					btnRserver.setEnabled(true);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Erreur lors du calcul du prix!");
				}
			}
		});

		//Valide la réservation.
		btnRserver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Creer dans la base de données ELEVE, COOURS et RESERVATION
				//Gere les cours disponible lors de la validation (min eleve, max eleve)
			}
		});

		//Annule la réservation.
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wClient.setEnabled(true);
				dispose();
			}
		});

		//Valide l'élève.
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(!textFieldNom.getText().isEmpty() && !textFieldPrenom.getText().isEmpty() && !txtJj.getText().isEmpty() && !txtMm.getText().isEmpty() && !txtAaaa.getText().isEmpty()){
						eleve = new Eleve();
						LocalDate date = LocalDate.of(Integer.parseInt(txtAaaa.getText()), Integer.parseInt(txtMm.getText()), Integer.parseInt(txtJj.getText()));

						eleve.setNom(textFieldNom.getText());
						eleve.setPrenom(textFieldPrenom.getText());
						eleve.setDateNaissance(date);

						if(checkBox.isSelected())
							eleve.setAssurance(true);
						else
							eleve.setAssurance(false);

						checkBox.setEnabled(false);
						textFieldNom.setEnabled(false);
						textFieldPrenom.setEnabled(false);;
						txtJj.setEnabled(false);
						txtMm.setEnabled(false);
						txtAaaa.setEnabled(false);

						if(eleve.age() < 18){
							initChoiceNiveaux(0);
						}
						else
							initChoiceNiveaux(1);

						confirmed=true;
						btnCalculPrix.setEnabled(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Champs manquants!");
					}
				}
				catch(Exception ex){
					//JOptionPane.showMessageDialog(null, "Date invalide!");
					System.out.println(ex.getMessage());
				}

			}
		});

		//Charge la combobox des niveaux au changement de sport.
		choiceSport.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(confirmed){
					if(eleve.age() < 18)
						initChoiceNiveaux(0);
					else
						initChoiceNiveaux(1);
				}
			}
		});

		//[end]

	}

	//Initialise les comboBox.
	private void initComboBox(){

		int cpt;
		String content;
		semaines = Semaine.loadSemainesFromDB();

		this.choiceSemaine = new Choice();
		choiceSemaine.setBounds(121, 180, 201, 20);
		contentPane.add(choiceSemaine);

		cpt=0;

		while(cpt < semaines.size()){
			content = semaines.get(cpt).getDateDebut().toString() + " -> " + semaines.get(cpt).getDateFin().toString();
			choiceSemaine.add(content);
			cpt++;
		}


		this.choiceHeure = new Choice();
		choiceHeure.setBounds(121, 205, 201, 20);
		contentPane.add(choiceHeure);
		choiceHeure.add("9h00 -> 12h00");
		choiceHeure.add("12h00 -> 13h00 (Cours particulier)");
		choiceHeure.add("12h00 -> 14h00 (Cours particulier)");
		choiceHeure.add("14h00 -> 17h00");


		this.choiceSport = new Choice();
		choiceSport.setBounds(121, 230, 201, 20);
		contentPane.add(choiceSport);
		choiceSport.add("Ski");
		choiceSport.add("Snowboard");
		choiceSport.add("Télémark");
		choiceSport.add("Ski de fond");


		this.choiceNiveaux = new Choice();
		choiceNiveaux.setEnabled(false);
		choiceNiveaux.setBounds(121, 255, 201, 20);
		contentPane.add(choiceNiveaux);

	}

	/**
	 * Initialise la combobox niveaux en fonctions de l'age de l'élève et du sport seléctioné.
	 * @param flag 1 si l'élève est majeur.
	 */
	private void initChoiceNiveaux(int flag){

		this.choiceNiveaux.setEnabled(true);
		this.choiceNiveaux.removeAll();

		if(flag == 1){
			switch(this.choiceSport.getSelectedItem().toString()){
			case "Ski" :  
				this.choiceNiveaux.add("Niveaux 1 à 4");
				this.choiceNiveaux.add("Hors-piste");
				this.choiceNiveaux.add("Compétition");
				break;
			case "Snowboard": 
				this.choiceNiveaux.add("Niveaux 1 à 4");
				this.choiceNiveaux.add("Hors-piste");
				break;
			case "Télémark" : 
				this.choiceNiveaux.add("Niveaux 1 à 4");
				break;
			case "Ski de fond" : 
				this.choiceNiveaux.add("Niveaux 1 à 4");
				break;
			}
		}
		else{ //l'élève est mineur.
			switch(this.choiceSport.getSelectedItem().toString()){
			case "Ski" : 
				this.choiceNiveaux.add("Petit Spirou");
				this.choiceNiveaux.add("Bronze");
				this.choiceNiveaux.add("Argent");
				this.choiceNiveaux.add("Or");
				this.choiceNiveaux.add("Platine");
				this.choiceNiveaux.add("Diamant");
				this.choiceNiveaux.add("Compétition");
				this.choiceNiveaux.add("Hors-piste");
				break;
			case "Snowboard":
				this.choiceNiveaux.add("Niveaux 1");
				this.choiceNiveaux.add("Niveaux 2 à 4");
				this.choiceNiveaux.add("Hors-piste");
				break;
			case "Télémark" : 
				this.choiceNiveaux.add("Niveaux 1 à 4");
				break;
			case "Ski de fond" : 
				this.choiceNiveaux.add("Niveaux 1 à 4");
				break;
			}
		}

	}
}


