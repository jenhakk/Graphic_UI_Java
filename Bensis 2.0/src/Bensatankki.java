import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JProgressBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import java.awt.Toolkit;

public class Bensatankki extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu valikko;
	private JLabel lblE;
	private JLabel label_98_noedit;
	private JLabel label_diesel_noedit;
	private JLabel lblPolttoainemrtLitraa;
	private JProgressBar progress95;
	private JProgressBar progress98;
	private JProgressBar progressDiesel;

	public Tilauslomake tilausIkkuna = new Tilauslomake();
	private Tilaushistoria tilausHistoria = new Tilaushistoria();
	private KirjautumisIkkuna kirjautumisIkkuna = new KirjautumisIkkuna();
	private YllapitoIkkuna yllapitoIkkuna = new YllapitoIkkuna();

	String filename = "src/Resources/tankit.txt";
	String E95;
	String E98;
	String Diesel;

	static int Int95;
	static int Int98;
	static int IntD;
	int i = 1;

	int uusi95;
	int uusi98;
	int uusiD;

	private JLabel lblNewLabel;
	private JMenuItem menu_tilaus;
	private JMenuItem menu_tarkastele;
	private JButton nappi_paivita;
	private JMenuItem menu_kirjaudu_ulos;
	private JMenuItem menu_palaa;

	/**
	 * Create the frame.
	 */
	public Bensatankki() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Bensatankki.class.getResource("/Resources/gas_station.png")));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				// Jos ikkunaa avatessa polttoaineista jokin on alle 200 litraa, näytä ilmoitus
				if (i == 1) {

					if (Int95 < 200 && Int98 < 200 && IntD < 200) {

						JOptionPane.showMessageDialog(null, "Kaikki polttoaineet ovat loppumassa!",
								"Polttoaine vähissä", JOptionPane.WARNING_MESSAGE);

					} else if (Int98 < 200 && Int95 < 200) {

						JOptionPane.showMessageDialog(null, "E98 ja E95 ovat loppumassa!", "Polttoaine vähissä",
								JOptionPane.WARNING_MESSAGE);

					} else if (IntD < 200 && Int95 < 200) {

						JOptionPane.showMessageDialog(null, "Diesel ja E95 ovat loppumassa!", "Polttoaine vähissä",
								JOptionPane.WARNING_MESSAGE);

					} else if (IntD < 200 && Int98 < 200) {

						JOptionPane.showMessageDialog(null, "Diesel ja E98 ovat loppumassa!", "Polttoaine vähissä",
								JOptionPane.WARNING_MESSAGE);

					} else if (Int95 < 200) {

						JOptionPane.showMessageDialog(null, "E95 on loppumassa!", "Polttoaine vähissä",
								JOptionPane.WARNING_MESSAGE);

					} else if (Int98 < 200) {

						JOptionPane.showMessageDialog(null, "E98 on loppumassa!", "Polttoaine vähissä",
								JOptionPane.WARNING_MESSAGE);

					} else if (IntD < 200) {

						JOptionPane.showMessageDialog(null, "Diesel on loppumassa!", "Polttoaine vähissä",
								JOptionPane.WARNING_MESSAGE);
					}
					i = 2;
				}
			}
		});
		setTitle("Bensatankki");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 497);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		valikko = new JMenu("Valikko");
		valikko.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		menuBar.add(valikko);

		menu_tilaus = new JMenuItem("Tee tilaus");
		menu_tilaus.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		menu_tilaus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tilausIkkuna.setVisible(true);
			}
		});
		valikko.add(menu_tilaus);

		menu_tarkastele = new JMenuItem("Tarkastele tilauksia");
		menu_tarkastele.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		menu_tarkastele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tilausHistoria.dispose();
				tilausHistoria = new Tilaushistoria();
				tilausHistoria.setModal(true);
				tilausHistoria.setVisible(true);
			}
		});
		valikko.add(menu_tarkastele);

		menu_kirjaudu_ulos = new JMenuItem("Kirjaudu ulos");
		menu_kirjaudu_ulos.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		menu_kirjaudu_ulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "Haluatko varmasti kirjautua ulos?", "Kirjaudu ulos",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {

					Bensatankki.this.setVisible(false);
					kirjautumisIkkuna.setVisible(true);

					if (result == JOptionPane.NO_OPTION) {

					}
				}
			}
		});

		menu_palaa = new JMenuItem("Yll\u00E4pitovalikko");
		menu_palaa.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		menu_palaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistua?", "Palaa valikkoon",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {

					Bensatankki.this.setVisible(false);
					yllapitoIkkuna.setVisible(true);
					if (result == JOptionPane.NO_OPTION) {

					}
				}
			}
		});
		valikko.add(menu_palaa);
		valikko.add(menu_kirjaudu_ulos);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180, 204, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblE = new JLabel("E95");
		lblE.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblE.setHorizontalAlignment(SwingConstants.CENTER);
		lblE.setBounds(128, 294, 108, 30);
		contentPane.add(lblE);

		label_98_noedit = new JLabel("E98");
		label_98_noedit.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		label_98_noedit.setHorizontalAlignment(SwingConstants.CENTER);
		label_98_noedit.setBounds(332, 294, 108, 30);
		contentPane.add(label_98_noedit);

		label_diesel_noedit = new JLabel("Diesel");
		label_diesel_noedit.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		label_diesel_noedit.setHorizontalAlignment(SwingConstants.CENTER);
		label_diesel_noedit.setBounds(533, 294, 108, 30);
		contentPane.add(label_diesel_noedit);

		lblPolttoainemrtLitraa = new JLabel("Polttoainem\u00E4\u00E4r\u00E4t / litraa");
		lblPolttoainemrtLitraa.setFont(new Font("Yu Gothic UI", Font.PLAIN, 25));
		lblPolttoainemrtLitraa.setBounds(15, 16, 333, 20);
		contentPane.add(lblPolttoainemrtLitraa);

		progress95 = new JProgressBar();
		progress95.setOrientation(SwingConstants.VERTICAL);
		progress95.setFont(new Font("Tahoma", Font.PLAIN, 18));
		progress95.setBackground(new Color(248, 248, 255));
		progress95.setForeground(new Color(153, 153, 255));
		progress95.setBounds(155, 88, 50, 190);
		contentPane.add(progress95);
		progress95.setMinimum(0);
		progress95.setMaximum(1000);
		progress95.setValue(lueMaara95(filename));
		progress95.setStringPainted(true);
		progress95.setString(E95);

		if (Int95 < 200) {
			progress95.setForeground(Color.RED);
		}

		progress98 = new JProgressBar();
		progress98.setFont(new Font("Tahoma", Font.PLAIN, 18));
		progress98.setBackground(new Color(248, 248, 255));
		progress98.setForeground(new Color(153, 153, 255));
		progress98.setOrientation(SwingConstants.VERTICAL);
		progress98.setBounds(360, 88, 50, 190);
		contentPane.add(progress98);
		progress98.setMinimum(0);
		progress98.setMaximum(1000);
		progress98.setValue(lueMaara98(filename));
		progress98.setStringPainted(true);
		progress98.setString(E98);

		if (Int98 < 200) {
			progress98.setForeground(Color.RED);
		}

		progressDiesel = new JProgressBar();
		progressDiesel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		progressDiesel.setBackground(new Color(248, 248, 255));
		progressDiesel.setForeground(new Color(153, 153, 255));
		progressDiesel.setOrientation(SwingConstants.VERTICAL);
		progressDiesel.setBounds(565, 88, 50, 190);
		contentPane.add(progressDiesel);
		progressDiesel.setMinimum(0);
		progressDiesel.setMaximum(1000);
		progressDiesel.setValue(lueMaaraDiesel(filename));
		progressDiesel.setStringPainted(true);
		progressDiesel.setString(Diesel);

		if (IntD < 200) {
			progressDiesel.setForeground(Color.RED);
		}

		lblNewLabel = new JLabel("max 1000 l");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(15, 52, 101, 20);
		contentPane.add(lblNewLabel);

		nappi_paivita = new JButton("P\u00E4ivit\u00E4 tiedot");
		nappi_paivita.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_paivita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Luetaan uudet määrät tiedostosta ja päivitetään tankit
				uusi95 = lueMaara95(filename);

				System.out.println(uusi95);
				progress95.setValue(uusi95);
				progress95.setString(String.valueOf(uusi95));

				if (uusi95 < 200) {
					progress95.setForeground(Color.RED);
				} else {
					progress95.setForeground(new Color(153, 153, 255));
				}

				uusi98 = lueMaara98(filename);

				System.out.println(uusi98);
				progress98.setValue(uusi98);
				progress98.setString(String.valueOf(uusi98));

				if (uusi98 < 200) {
					progress98.setForeground(Color.RED);
				} else {
					progress98.setForeground(new Color(153, 153, 255));
				}

				uusiD = lueMaaraDiesel(filename);

				System.out.println(uusiD);
				progressDiesel.setValue(uusiD);
				progressDiesel.setString(String.valueOf(uusiD));

				if (uusiD < 200) {
					progressDiesel.setForeground(Color.RED);
				} else {
					progressDiesel.setForeground(new Color(153, 153, 255));
				}
			}
		});
		nappi_paivita.setBounds(309, 344, 154, 29);
		contentPane.add(nappi_paivita);

	}

	public static String kasitteleRivi(String txt) {

		String[] temp = txt.split("=");
		return temp[1];
	}

	// Luetaan 95-määrä tiedostosta ja palautaan luku integerinä
	public int lueMaara95(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);

			Int95 = Integer.parseInt(E95);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Int95;
	}

	// Luetaan 98-määrä tiedostosta ja palautaan luku integerinä
	public int lueMaara98(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);

			Int98 = Integer.parseInt(E98);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Int98;
	}

	// Luetaan Diesel-määrä tiedostosta ja palautaan luku integerinä
	public int lueMaaraDiesel(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);

			IntD = Integer.parseInt(Diesel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IntD;
	}
}
