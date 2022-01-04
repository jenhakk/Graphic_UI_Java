import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HinnatPaivitys extends JFrame {

	private JPanel contentPane;
	private JTextField uusi95;
	private JTextField uusi98;
	private JTextField uusiD;

	String filename = "src/Resources/hinnat.txt";
	private JButton paivita;
	private JMenuBar menuBar;
	private JMenuItem palaa;
	private JLabel teksti95;
	private JLabel teksti98;
	private JLabel tekstiD;

	String E95;
	String E98;
	String Diesel;
	String uusihinta95;
	String uusihinta98;
	String uusihintaD;
	double double95;
	double double98;
	double doubleD;
	private JLabel ohje;
	private JLabel euro;
	private JLabel euro_1;
	private JLabel euro_2;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public HinnatPaivitys() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HinnatPaivitys.class.getResource("/Resources/gas_station.png")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// luetaan vanhat hinnat ikkunan aktivoituessa

				lueHinnat1(filename);

			}
		});
		setTitle("Hintojen p\u00E4ivitys");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 429, 318);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		palaa = new JMenuItem("Palaa takaisin");
		palaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // palataan yll‰pidon etusivuvalikkoon
				HinnastoEtusivu hinnastoEtusivu = new HinnastoEtusivu();
				hinnastoEtusivu.setVisible(true);
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

		uusi95 = new JTextField();
		uusi95.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (uusi95.getText().length() >= 5 ) // max merkkim‰‰r‰ 5 merkki‰
		            e.consume(); 
		    }  
			
		});
		uusi95.setHorizontalAlignment(SwingConstants.CENTER);
		uusi95.setFont(new Font("Source Code Pro", Font.PLAIN, 20));
		uusi95.setBounds(171, 67, 81, 24);
		contentPane.add(uusi95);
		uusi95.setColumns(10);

		uusi98 = new JTextField();
		uusi98.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (uusi98.getText().length() >= 5 ) // max merkkim‰‰r‰ 5 merkki‰
		            e.consume(); 
		    }  
			
		});
		uusi98.setHorizontalAlignment(SwingConstants.CENTER);
		uusi98.setFont(new Font("Source Code Pro", Font.PLAIN, 20));
		uusi98.setColumns(10);
		uusi98.setBounds(171, 102, 81, 24);
		contentPane.add(uusi98);

		uusiD = new JTextField();
		uusiD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (uusiD.getText().length() >= 5 ) // max merkkim‰‰r‰ 5 merkki‰
		            e.consume(); 
		    }  
			
		});
		uusiD.setHorizontalAlignment(SwingConstants.CENTER);
		uusiD.setFont(new Font("Source Code Pro", Font.PLAIN, 20));
		uusiD.setColumns(10);
		uusiD.setBounds(171, 137, 81, 24);
		contentPane.add(uusiD);

		paivita = new JButton("P\u00E4ivit\u00E4 hinnat");
		paivita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// kirjoitetaan uudet hinnat 
				
				boolean success = false;
				try {
					double95 = Double.parseDouble(uusi95.getText());
					double98 = Double.parseDouble(uusi98.getText());
					doubleD = Double.parseDouble(uusiD.getText());		
					
					if (double95 <= 0 || double98 <= 0 || doubleD <= 0) {
						JOptionPane.showMessageDialog(null, "Korjaa hinta!");
						success = true;
					}
					
				} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "V‰‰r‰ syˆte!");
					success = true;
				
				}
				
				if (!success) {
					uusihinta95 = String.valueOf(double95);
					uusihinta98 = String.valueOf(double98);
					uusihintaD = String.valueOf(doubleD);
					kirjoitaTiedostoon(uusi95.getText(), uusi98.getText(), uusiD.getText(), filename);
					JOptionPane.showMessageDialog(null, "Hinnat p‰ivitetty");
				}
									
			}
		});
		paivita.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		paivita.setBounds(95, 185, 232, 25);
		contentPane.add(paivita);
		
		teksti95 = new JLabel("E95");
		teksti95.setHorizontalAlignment(SwingConstants.CENTER);
		teksti95.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		teksti95.setBounds(80, 67, 62, 22);
		contentPane.add(teksti95);
		
		teksti98 = new JLabel("E98");
		teksti98.setHorizontalAlignment(SwingConstants.CENTER);
		teksti98.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		teksti98.setBounds(80, 102, 62, 22);
		contentPane.add(teksti98);
		
		tekstiD = new JLabel("Diesel");
		tekstiD.setHorizontalAlignment(SwingConstants.CENTER);
		tekstiD.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		tekstiD.setBounds(80, 137, 62, 22);
		contentPane.add(tekstiD);
		
		ohje = new JLabel("Sy\u00F6t\u00E4 uusi hinta muodossa 0.000");
		ohje.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		ohje.setBounds(70, 23, 282, 36);
		contentPane.add(ohje);
		
		euro = new JLabel("\u20AC/l");
		euro.setHorizontalAlignment(SwingConstants.LEFT);
		euro.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		euro.setBounds(275, 67, 81, 22);
		contentPane.add(euro);
		
		euro_1 = new JLabel("\u20AC/l");
		euro_1.setHorizontalAlignment(SwingConstants.LEFT);
		euro_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		euro_1.setBounds(275, 102, 81, 22);
		contentPane.add(euro_1);
		
		euro_2 = new JLabel("\u20AC/l");
		euro_2.setHorizontalAlignment(SwingConstants.LEFT);
		euro_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		euro_2.setBounds(275, 136, 81, 22);
		contentPane.add(euro_2);
	}

	public void kirjoitaTiedostoon(String E95txt, String E98txt, String Dieseltxt, String filename) {
		try {
			// ylikirjoitetaan vanhojen hintojen p‰‰lle, sill‰ se ei p‰ivityksen j‰lkeen ole oleellista tietoa
			FileWriter writer = new FileWriter(filename);
			writer.write("E95 = " + E95txt + "\n");
			writer.write("E98 = " + E98txt + "\n");
			writer.write("Diesel = " + Dieseltxt + "\n");
			writer.close();
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lueHinnat1(String filename) {
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			uusi95.setText(E95);

			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			uusi98.setText(E98);

			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);
			uusiD.setText(Diesel);
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String kasitteleRivi(String txt) {
		txt = txt.replace(" ", "");
		String[] temp = txt.split("=");
		return temp[1];
	}
	public String vanhaHinta(String PA, int rivi) throws IOException {

		String hinta = Files.readAllLines(Paths.get(filename)).get(0);
		return hinta;
	}
}
