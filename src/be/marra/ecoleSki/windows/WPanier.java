package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.List;

public class WPanier extends JFrame {

	private static final long serialVersionUID = 2252186507650817925L;
	private JPanel contentPane;

	public WPanier(WClient wClient) {
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
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(32, 237, 100, 23);
		contentPane.add(btnSupprimer);
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.setBounds(142, 237, 100, 23);
		contentPane.add(btnAfficher);
		
		JButton btnPayer = new JButton("Payer");
		btnPayer.setBounds(317, 237, 89, 23);
		contentPane.add(btnPayer);
		
		JLabel lblTotal = new JLabel("Total : ");
		lblTotal.setBounds(256, 202, 46, 14);
		contentPane.add(lblTotal);
		
		JLabel lblPrix = new JLabel("");
		lblPrix.setBounds(338, 202, 46, 14);
		contentPane.add(lblPrix);
		
		List list = new List();
		list.setBounds(32, 10, 374, 186);
		contentPane.add(list);
		
		//[end]
		
		//[start]Events
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				wClient.setEnabled(true);
			}
		});
		
		//[end]
	}
}
