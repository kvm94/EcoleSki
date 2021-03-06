package be.marra.ecoleSki.windows;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.marra.ecoleSki.Reservation;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextPane;

public class WReservation extends JFrame {
	
	//[start]Attributs

	private static final long serialVersionUID = -8711660953536509225L;
	private JPanel contentPane;
	private Reservation res;
	private JTextPane textPane;
	
	//[end]
	
	public WReservation(JFrame window, Reservation res) {
		
		setTitle("R\u00E9servation");
		setResizable(false);
		
		this.res =res;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 257, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Centre la fen�tre.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//[start]Contenus
		
		textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setBounds(10, 11, 229, 293);
		contentPane.add(textPane);
		
		//[end]
		
		//Contenus du panel.
		initText();
		
		
		//[start]Ev�nements
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				window.setEnabled(true);
			}
		});
		
		//[end]
	}
	
	private void initText(){		
		this.textPane.setText(res.afficher());
		this.textPane.setEditable(false);
	}
}
