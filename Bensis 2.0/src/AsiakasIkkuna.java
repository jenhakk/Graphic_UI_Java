import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Toolkit;

public class AsiakasIkkuna extends JFrame {

	private JPanel contentPane;
	private JLabel lblBensojenHinnat;
	private JLabel label_95;
	private JLabel label_98;
	private JLabel label_diesel;
	public JLabel label_95_hinta;
	public JLabel label_98_hinta;
	public JLabel label_diesel_hinta;
	private JButton nappi_maksamaan;
	public JRadioButton radiobutton_95;
	public JRadioButton radiobutton_98;
	public JRadioButton radiobutton_Diesel;
	public JTextField textField_litra;
	private JLabel lblTankkaus;
	private JLabel label_litra2;
	public JTextField textField_euro;
	private JLabel lblEuroa;
	private JLabel lblOnkoAlennuskoodia;
	public JTextField textField_alennuskoodi;
	private JButton nappi_paivita;
	private JLabel label_loppusumma;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenuBar menuBar;
	private JMenu menu_ostokset;
	private JMenuItem menuItem_ostokset;
	private JButton nappi_valikkoon;

	private MaksuIkkuna maksuIkkuna = new MaksuIkkuna(AsiakasIkkuna.this);
	private PalveluIkkuna palveluIkkuna = new PalveluIkkuna();
	private AloitusIkkuna aloitusIkkuna = new AloitusIkkuna();

	String hintaE95;
	String hintaE98;
	String hintaDiesel;

	String E95;
	String E98;
	String Diesel;

	int Int95;
	int Int98;
	int IntD;

	String filename = "src/Resources/hinnat.txt";
	String filename2 = "src/Resources/kuitti.txt";
	String filename3 = "src/Resources/tankit.txt";
	String filename5 = "src/Resources/tankattu.txt";

	/**
	 * Create the frame.
	 */
	public AsiakasIkkuna() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AsiakasIkkuna.class.getResource("/Resources/gas_station.png")));
		setTitle("Tervetuloa!");

		File f = new File(filename);
		if (!f.exists()) {
			alustaHinnat();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 371);

// ******************** MENUBAR **********************************************************

		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		menu_ostokset = new JMenu("Tee muita ostoksia");
		menu_ostokset.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		menuBar.add(menu_ostokset);

		menuItem_ostokset = new JMenuItem("Muut tuotteet");
		menuItem_ostokset.setBackground(Color.WHITE);
		menuItem_ostokset.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		menuItem_ostokset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				palveluIkkuna.dispose();
				palveluIkkuna.setVisible(true);
			}
		});
		menu_ostokset.add(menuItem_ostokset);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180, 204, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

// ******************** LABELIT **********************************************************

		lblBensojenHinnat = new JLabel("Bensojen hinnat:");
		lblBensojenHinnat.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		lblBensojenHinnat.setBounds(12, 13, 158, 25);
		contentPane.add(lblBensojenHinnat);

		label_95 = new JLabel("95");
		label_95.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_95.setBounds(12, 48, 47, 26);
		contentPane.add(label_95);

		label_98 = new JLabel("98");
		label_98.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_98.setBounds(12, 77, 47, 26);
		contentPane.add(label_98);

		label_diesel = new JLabel("Diesel");
		label_diesel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_diesel.setBounds(12, 106, 47, 26);
		contentPane.add(label_diesel);

		label_95_hinta = new JLabel("");
		label_95_hinta.setHorizontalAlignment(SwingConstants.CENTER);
		label_95_hinta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_95_hinta.setBounds(71, 49, 56, 26);
		contentPane.add(label_95_hinta);

		label_98_hinta = new JLabel("");
		label_98_hinta.setHorizontalAlignment(SwingConstants.CENTER);
		label_98_hinta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_98_hinta.setBounds(71, 78, 56, 26);
		contentPane.add(label_98_hinta);

		label_diesel_hinta = new JLabel("");
		label_diesel_hinta.setHorizontalAlignment(SwingConstants.CENTER);
		label_diesel_hinta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_diesel_hinta.setBounds(71, 106, 56, 26);
		contentPane.add(label_diesel_hinta);

		lblTankkaus = new JLabel("Tankkaus:");
		lblTankkaus.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		lblTankkaus.setBounds(217, 13, 211, 25);
		contentPane.add(lblTankkaus);

		label_litra2 = new JLabel("litraa");
		label_litra2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_litra2.setBounds(317, 70, 66, 16);
		contentPane.add(label_litra2);

		lblEuroa = new JLabel("euroa");
		lblEuroa.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblEuroa.setBounds(317, 184, 66, 16);
		contentPane.add(lblEuroa);

		lblOnkoAlennuskoodia = new JLabel("Sy\u00F6t\u00E4 alennuskoodi:");
		lblOnkoAlennuskoodia.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblOnkoAlennuskoodia.setBounds(217, 95, 166, 25);
		contentPane.add(lblOnkoAlennuskoodia);

		label_loppusumma = new JLabel("Loppusumma:");
		label_loppusumma.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_loppusumma.setBounds(217, 153, 124, 25);
		contentPane.add(label_loppusumma);

// ******************** RADIO BUTTONIT **********************************************************

		radiobutton_95 = new JRadioButton("E95");
		radiobutton_95.setBackground(new Color(180, 204, 225));
		radiobutton_95.setSelected(true);
		buttonGroup.add(radiobutton_95);
		radiobutton_95.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		radiobutton_95.setBounds(217, 37, 63, 25);
		contentPane.add(radiobutton_95);

		radiobutton_98 = new JRadioButton("E98");
		radiobutton_98.setBackground(new Color(180, 204, 225));
		buttonGroup.add(radiobutton_98);
		radiobutton_98.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		radiobutton_98.setBounds(278, 37, 63, 25);
		contentPane.add(radiobutton_98);

		radiobutton_Diesel = new JRadioButton("Diesel");
		radiobutton_Diesel.setBackground(new Color(180, 204, 225));
		buttonGroup.add(radiobutton_Diesel);
		radiobutton_Diesel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		radiobutton_Diesel.setBounds(345, 37, 83, 25);
		contentPane.add(radiobutton_Diesel);

// ******************** TEKSTIKENTƒT **********************************************************

		textField_litra = new JTextField();
		textField_litra.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_litra.setBounds(217, 67, 88, 26);
		contentPane.add(textField_litra);
		textField_litra.setColumns(10);

		textField_euro = new JTextField();
		textField_euro.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_euro.setBackground(Color.WHITE);
		textField_euro.setEditable(false);
		textField_euro.setBounds(217, 182, 88, 26);
		contentPane.add(textField_euro);
		textField_euro.setColumns(10);

		textField_alennuskoodi = new JTextField();
		textField_alennuskoodi.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_alennuskoodi.setBounds(216, 118, 124, 26);
		contentPane.add(textField_alennuskoodi);
		textField_alennuskoodi.setColumns(10);

// ******************** NAPIT **********************************************************

		nappi_maksamaan = new JButton("Etene maksamaan");
		nappi_maksamaan.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_maksamaan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					textField_litra.setForeground(Color.BLACK);
					textField_euro.setForeground(Color.BLACK);

					double maara = Double.parseDouble(textField_litra.getText());
					double maksimi = 1000; // kovakoodattu toistaiseksi, kunnes yhdiste‰‰n

					if (maara > maksimi) {
						JOptionPane.showMessageDialog(null, "Bensaa ei ole haluamaasi m‰‰r‰‰, valitse pienempi m‰‰r‰.",
								"Tankkaus ep‰onnistui.", JOptionPane.ERROR_MESSAGE);
						textField_litra.setText("");
					}

					if ((!textField_litra.getText().equals("") && !textField_litra.getText().equals("0"))
							&& (!textField_euro.getText().equals("") && !textField_euro.getText().equals("0"))
							&& (!palveluIkkuna.textField_kpl1.getText().equals("")
									&& !palveluIkkuna.textField_kpl1.getText().equals("0"))
							&& (!palveluIkkuna.textField_kpl2.getText().equals("")
									&& !palveluIkkuna.textField_kpl2.getText().equals("0"))
							&& (!palveluIkkuna.textField_kpl3.getText().equals("")
									&& !palveluIkkuna.textField_kpl3.getText().equals("0"))) {

						maksuIkkuna.dispose();
						palveluIkkuna.setVisible(false);
						maksuIkkuna = new MaksuIkkuna(AsiakasIkkuna.this);

						maksuIkkuna.label_loppusumma.setText(textField_euro.getText() + " Ä");

						maksuIkkuna.setModal(true);
						maksuIkkuna.setVisible(true);

					} else {
						textField_litra.setForeground(Color.RED);
						textField_euro.setForeground(Color.RED);
						JOptionPane.showMessageDialog(null, "Syˆt‰ tuotteiden m‰‰r‰t oikein ja p‰ivit‰ hinta.",
								"Tankkaus ep‰onnistui.", JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException e) {
					textField_litra.setForeground(Color.RED);
					textField_euro.setForeground(Color.RED);
					JOptionPane.showMessageDialog(null, "Syˆt‰ tuotteiden m‰‰r‰t oikein ja p‰ivit‰ hinta.",
							"Tankkaus ep‰onnistui.", JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e) {
					e.printStackTrace();
				}

			}
		});
		nappi_maksamaan.setBounds(217, 247, 191, 29);
		contentPane.add(nappi_maksamaan);

		nappi_paivita = new JButton("P\u00E4ivit\u00E4 hinta");
		nappi_paivita.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_paivita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String oikea_alennuskoodi = "hiphei";
				double hinta1 = 0;
				double hinta2 = 0;
				double hinta3 = 0;
				double hinta4 = 0;
				double alennus = 0;
				double maara1 = 0;
				double maara2 = 0;
				double maara3 = 0;
				double maara4 = 0;
				double yhteisHinta = 0;
				double yhteisHintaKahvi = 0;
				double yhteisHintaPulla = 0;
				double yhteisHintaLounas = 0;
				String E95;
				String E98;
				String Diesel;

				String bensaTyyppi = "";

				// BENSA: hinnat
				if (radiobutton_95.isSelected()) {
					hinta1 = Double.parseDouble(label_95_hinta.getText());
					bensaTyyppi = "E95";
					E95 = textField_litra.getText();
					kirjoitaTiedostoon2("E95=" + E95 + "\n" + "E98=0\nDiesel=0", filename5);

				} else if (radiobutton_98.isSelected()) {
					hinta1 = Double.parseDouble(label_98_hinta.getText());
					bensaTyyppi = "E98";
					E98 = textField_litra.getText();
					kirjoitaTiedostoon2("E95=0\nE98=" + E98 + "\nDiesel=0", filename5);

				} else {
					hinta1 = Double.parseDouble(label_diesel_hinta.getText());
					bensaTyyppi = "Diesel";
					Diesel = textField_litra.getText();
					kirjoitaTiedostoon2("E95=0\nE98=0\nDiesel=" + Diesel, filename5);

				}

				// BENSA: m‰‰r‰

				try {
					textField_litra.setForeground(Color.BLACK);
					textField_euro.setForeground(Color.BLACK);

					if (!textField_litra.getText().equals("") && !textField_litra.getText().equals("0")) {
						maara1 = Double.parseDouble(textField_litra.getText());

						int tankissa95 = lueMaara95(filename3);
						double tankissadbl95 = Double.valueOf(tankissa95);
						int tankissa98 = lueMaara98(filename3);
						double tankissadbl98 = Double.valueOf(tankissa98);
						int tankissaD = lueMaaraDiesel(filename3);
						double tankissadblD = Double.valueOf(tankissaD);

						if (maara1 < 0) {

							JOptionPane.showMessageDialog(null, "Tarkista hinnat!",
									"M‰‰r‰ ei voi olla miinusmerkkinen!", JOptionPane.ERROR_MESSAGE);
							textField_litra.setText("");

						} else if (maara1 > tankissadbl95) {
							JOptionPane.showMessageDialog(null, "Tankissa ei tarpeeksi polttoainetta!", "Varoitus!",
									JOptionPane.ERROR_MESSAGE);
							textField_litra.setText("");

						} else if (maara1 > tankissadbl98) {
							JOptionPane.showMessageDialog(null, "Tankissa ei tarpeeksi polttoainetta!", "Varoitus!",
									JOptionPane.ERROR_MESSAGE);
							textField_litra.setText("");

						} else if (maara1 > tankissadblD) {
							JOptionPane.showMessageDialog(null, "Tankissa ei tarpeeksi polttoainetta!", "Varoitus!",
									JOptionPane.ERROR_MESSAGE);
							textField_litra.setText("");
						}

					} else {
						textField_litra.setForeground(Color.RED);
					}

				} catch (NumberFormatException e1) {
					textField_litra.setForeground(Color.RED);
					textField_euro.setForeground(Color.RED);
				}

				yhteisHinta = maara1 * hinta1;

				// ALENNUS
				if (textField_alennuskoodi.getText().equals(oikea_alennuskoodi)) {
					alennus = yhteisHinta * 0.1;
					yhteisHinta = yhteisHinta - alennus;
				}

				// MUUT TUOTTEET: hinnat

				try {
					palveluIkkuna.textField_kpl1.setForeground(Color.BLACK);
					palveluIkkuna.textField_kpl2.setForeground(Color.BLACK);
					palveluIkkuna.textField_kpl3.setForeground(Color.BLACK);

					if (palveluIkkuna.checkbox_kahvi.isSelected()) {

						if (palveluIkkuna.textField_kpl1.getText().equals("")
								|| palveluIkkuna.textField_kpl1.getText().equals("0")) {

							palveluIkkuna.textField_kpl1.setForeground(Color.RED);

						} else {
							try {
								hinta2 = Double.parseDouble(palveluIkkuna.label_kahvi_hinta.getText());
								maara2 = Double.parseDouble(palveluIkkuna.textField_kpl1.getText());
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Ei kirjaimia! Tarkista hinnat!",
										"Virhe", JOptionPane.ERROR_MESSAGE);
								palveluIkkuna.textField_kpl1.setText("1");
								palveluIkkuna.checkbox_kahvi.setSelected(false);
							}

							if (maara2 < 0) {

								JOptionPane.showMessageDialog(null, "Tarkista hinnat!",
										"M‰‰r‰ ei voi olla miinusmerkkinen!", JOptionPane.ERROR_MESSAGE);
								palveluIkkuna.textField_kpl1.setText("1");
								palveluIkkuna.checkbox_kahvi.setSelected(false);

							}
						}

					}

					if (palveluIkkuna.checkbox_pulla.isSelected()) {

						if (palveluIkkuna.textField_kpl2.getText().equals("")
								|| palveluIkkuna.textField_kpl2.getText().equals("0")) {

							palveluIkkuna.textField_kpl2.setForeground(Color.RED);

						} else {
							try {
								hinta3 = Double.parseDouble(palveluIkkuna.label_pulla_hinta.getText());
								maara3 = Double.parseDouble(palveluIkkuna.textField_kpl2.getText());
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Ei kirjaimia! Tarkista hinnat!",
										"Virhe", JOptionPane.ERROR_MESSAGE);
								palveluIkkuna.textField_kpl2.setText("1");
								palveluIkkuna.checkbox_pulla.setSelected(false);
							}

							if (maara3 < 0) {

								JOptionPane.showMessageDialog(null, "Tarkista hinnat!",
										"M‰‰r‰ ei voi olla miinusmerkkinen!", JOptionPane.ERROR_MESSAGE);
								palveluIkkuna.textField_kpl2.setText("1");
								palveluIkkuna.checkbox_pulla.setSelected(false);
							}

						}
					}

					if (palveluIkkuna.checkbox_lounas.isSelected()) {

						if (palveluIkkuna.textField_kpl3.getText().equals("")
								|| palveluIkkuna.textField_kpl3.getText().equals("0")) {

							palveluIkkuna.textField_kpl3.setForeground(Color.RED);

						} else {
							try {
								hinta4 = Double.parseDouble(palveluIkkuna.label_lounas_hinta.getText());
								maara4 = Double.parseDouble(palveluIkkuna.textField_kpl3.getText());
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Ei kirjaimia! Tarkista hinnat!",
										"Virhe", JOptionPane.ERROR_MESSAGE);
								palveluIkkuna.textField_kpl3.setText("1");
								palveluIkkuna.checkbox_lounas.setSelected(false);
							}

							if (maara4 < 0) {

								JOptionPane.showMessageDialog(null, "Tarkista hinnat!",
										"M‰‰r‰ ei voi olla miinusmerkkinen!", JOptionPane.ERROR_MESSAGE);
								palveluIkkuna.textField_kpl3.setText("1");
								palveluIkkuna.checkbox_lounas.setSelected(false);
							}

						}
					}

				} catch (NumberFormatException e) {
					palveluIkkuna.textField_kpl1.setForeground(Color.RED);
					palveluIkkuna.textField_kpl2.setForeground(Color.RED);
					palveluIkkuna.textField_kpl3.setForeground(Color.RED);
				}

				yhteisHintaKahvi = maara2 * hinta2;
				yhteisHintaPulla = maara3 * hinta3;
				yhteisHintaLounas = maara4 * hinta4;

				String lopullinenHinta = String.format("%.2f",
						yhteisHinta + yhteisHintaKahvi + yhteisHintaPulla + yhteisHintaLounas);
				textField_euro.setText(lopullinenHinta);

				if (maara1 < 0 || maara2 < 0 || maara3 < 0 || maara4 < 0) {

					textField_euro.setText("");
				}

				rakennaKuitti(bensaTyyppi, yhteisHinta, maara1, hinta1, alennus, yhteisHintaKahvi, hinta2, maara2,
						yhteisHintaPulla, hinta3, maara3, yhteisHintaLounas, hinta4, maara4, lopullinenHinta);

//				vahennaTankeista(bensaTyyppi, maara1);
			}

		});
		nappi_paivita.setBounds(217, 213, 191, 29);
		contentPane.add(nappi_paivita);

		nappi_valikkoon = new JButton("Alkuvalikko");
		nappi_valikkoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				aloitusIkkuna.dispose();
				AsiakasIkkuna.this.dispose();
				aloitusIkkuna = new AloitusIkkuna();
				aloitusIkkuna.setVisible(true);
			}
		});
		nappi_valikkoon.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_valikkoon.setBounds(12, 247, 124, 29);
		contentPane.add(nappi_valikkoon);

		lueHinnat(filename);
	}

	public void lueHinnat(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			hintaE95 = kasitteleRivi(br.readLine());
			hintaE98 = kasitteleRivi(br.readLine());
			hintaDiesel = kasitteleRivi(br.readLine());

			label_95_hinta.setText(hintaE95);
			label_98_hinta.setText(hintaE98);
			label_diesel_hinta.setText(hintaDiesel);

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void kirjoitaTiedostoon(String txt, String filename) {
		try {
			FileWriter fwriter = new FileWriter(filename, true);
			fwriter.write(txt + "\n");
			fwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void kirjoitaTiedostoon2(String txt, String filename) {
		try {
			FileWriter fwriter = new FileWriter(filename);
			fwriter.write(txt + "\n");
			fwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alustaHinnat() {
		String kirjoitettava = "E95 = 1.4\n" + "E98 = 1.5\n" + "Diesel = 1.2 " + "Kahvi = 1.5" + "Pulla = 1.0";

		kirjoitaTiedostoon(kirjoitettava, filename);
	}

	public String kasitteleRivi(String txt) {
		txt = txt.replace(" ", "");
		String[] temp = txt.split("=");

		return temp[1];
	}

	public String kellonAika() {
		java.util.Date date = new java.util.Date();
		return date.toString();
	}

	public void rakennaKuitti(String bensaTyyppi, double yhteisHinta, double maara1, double hinta1, double alennus,
			double yhteisHintaKahvi, double kahvi, double maara2, double yhteisHintaPulla, double pulla, double maara3,
			double yhteisHintaLounas, double lounas, double maara4, String lopullinenHinta) {

		try {
			new PrintWriter(filename2).close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String aika = kellonAika();
		String toivotus = "Kiitos k‰ynnist‰ ja tervetuloa uudelleen!";
		String maksutapa = "Korttitapahtuma";
		String aukioloajat = "Olemme avoinna 24/7.";

		kirjoitaTiedostoon(aika + "\n", filename2);

		kirjoitaTiedostoon(bensaTyyppi + String.format("			%.2f", yhteisHinta) + "Ä", filename2);

		if (alennus != 0) {
			kirjoitaTiedostoon("Ale -10% 		-" + String.format("%.2f", alennus) + "Ä", filename2);
		}

		kirjoitaTiedostoon(String.format("			   %.0f", maara1) + "l" + String.format("     %.3f", hinta1),
				filename2);

		if (kahvi != 0) {
			kirjoitaTiedostoon("Kahvi " + String.format("			%.2f", yhteisHintaKahvi) + "Ä", filename2);
			kirjoitaTiedostoon(String.format("			   %.0f", maara2) + "kpl" + String.format("     %.2f", kahvi),
					filename2);
		}

		if (pulla != 0) {
			kirjoitaTiedostoon("Pulla " + String.format("			%.2f", yhteisHintaPulla) + "Ä", filename2);
			kirjoitaTiedostoon(String.format("			   %.0f", maara3) + "kpl" + String.format("     %.2f", pulla),
					filename2);
		}

		if (lounas != 0) {
			kirjoitaTiedostoon("Lounas " + String.format("			%.2f", yhteisHintaLounas) + "Ä", filename2);
			kirjoitaTiedostoon(String.format("			   %.0f", maara4) + "kpl" + String.format("     %.2f", lounas),
					filename2);
		}

		kirjoitaTiedostoon("\n" + "Yhteens‰ 		" + lopullinenHinta + "Ä", filename2);
		kirjoitaTiedostoon(maksutapa + "\n", filename2);
		kirjoitaTiedostoon("\n" + toivotus, filename2);
		kirjoitaTiedostoon(aukioloajat, filename2);
	}

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

	// Luetaan 98-m‰‰r‰ tiedostosta ja palautaan luku integerin‰
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

	// Luetaan Diesel-m‰‰r‰ tiedostosta ja palautaan luku integerin‰
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
