package be.marra.ecoleSki.windows;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WMoniteur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8176783639941141116L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public WMoniteur() {
		setTitle("Moniteur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
