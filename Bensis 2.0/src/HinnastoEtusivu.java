import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;

public class HinnastoEtusivu extends JFrame {

	private JPanel contentPane;
	
	private JButton hinnat1;
	private JButton mainos1;
	private JButton mainos2;
	
	private HinnatPaivitys hinnatPaivitys = new HinnatPaivitys();
	private MainosYllapito mainosYllapito = new MainosYllapito();
	private YllapitoIkkuna yllapitoIkkuna = new YllapitoIkkuna();

	private Mainos mainos = new Mainos ();
	private JMenuBar menuBar;
	private JMenuItem palaa;

	/**
	 * Launch the application.
	 */
		
		/**
	 * Create the frame.
	 */
	public HinnastoEtusivu() {
			setIconImage(Toolkit.getDefaultToolkit().getImage(HinnastoEtusivu.class.getResource("/Resources/gas_station.png")));
			setResizable(false);
		setTitle("Etusivu");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 430, 320);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		palaa = new JMenuItem("Palaa takaisin");
		palaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mahdollisuus palata takaisin Ylläpitoikkunaan
				
					yllapitoIkkuna = new YllapitoIkkuna();
					yllapitoIkkuna.setVisible(true);
					setVisible(false);
				    dispose();
					
			}
		});
		menuBar.add(palaa);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180,204,225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		hinnat1 = new JButton("P\u00E4ivit\u00E4 hinnat");
		hinnat1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinnatPaivitys = new HinnatPaivitys();
				hinnatPaivitys.setVisible(true);
				dispose();
			}
		});
		hinnat1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		hinnat1.setBounds(111, 57, 189, 25);
		contentPane.add(hinnat1);
		
		mainos1 = new JButton("P\u00E4ivit\u00E4 mainos");
		mainos1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainosYllapito = new MainosYllapito();
				mainosYllapito.setVisible(true);
				dispose();
			}
		});
		mainos1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		mainos1.setBounds(111, 111, 189, 25);
		contentPane.add(mainos1);
		
		mainos2 = new JButton("N\u00E4yt\u00E4 mainos");
		mainos2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainos = new Mainos();
				mainos.setVisible(true);				
			}
		});
		mainos2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		mainos2.setBounds(111, 163, 189, 25);
		contentPane.add(mainos2);
	}
}
