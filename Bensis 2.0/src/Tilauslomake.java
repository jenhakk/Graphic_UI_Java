import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Tilauslomake extends JFrame {

	private JPanel contentPane;
	private JTextField textField_95;
	private JTextField textField_98;
	private JTextField textField_Diesel;
	public JCheckBox check_95;
	private JCheckBox check_98;
	private JCheckBox check_diesel;
	public JButton nappi_tilaa;

	public String filename = "src/Resources/tilaukset.txt";
	public String file = "src/Resources/tankit.txt";
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel lblKirjoitaMrKokonaisina;

	int Int95;
	int Int98;
	int IntD;
	String E95 = null;
	String E98 = null;
	String Diesel = null;
	String vanha95 = null;
	String uusi95 = null;
	int iuusi95;
	String vanha98 = null;
	String uusi98 = null;
	int iuusi98;
	String vanhaD = null;
	String uusiD = null;
	int iuusiD;

	/**
	 * Create the frame.
	 */

	{

	}

	public Tilauslomake() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				// Ikkunan auetessa kaikki kent‰t asetetaan tyhjiksi
				textField_95.setText("0");
				textField_98.setText("0");
				textField_Diesel.setText("0");
				check_95.setSelected(false);
				check_98.setSelected(false);
				check_diesel.setSelected(false);
			}
		});
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Tilauslomake.class.getResource("/Resources/gas_station.png")));
		setTitle("Tilauslomake");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 684, 438);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180, 204, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_95 = new JTextField();
		textField_95.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_95.setBounds(322, 119, 146, 26);
		contentPane.add(textField_95);
		textField_95.setColumns(10);
		textField_95.setText("0");

		textField_98 = new JTextField();
		textField_98.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_98.setColumns(10);
		textField_98.setBounds(322, 174, 146, 26);
		contentPane.add(textField_98);
		textField_98.setText("0");

		textField_Diesel = new JTextField();
		textField_Diesel.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_Diesel.setColumns(10);
		textField_Diesel.setBounds(322, 226, 146, 26);
		contentPane.add(textField_Diesel);
		textField_Diesel.setText("0");

		check_95 = new JCheckBox("E95");
		check_95.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		check_95.setBackground(new Color(180, 204, 225));
		check_95.setBounds(172, 116, 139, 29);
		contentPane.add(check_95);

		check_98 = new JCheckBox("E98");
		check_98.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		check_98.setBackground(new Color(180, 204, 225));
		check_98.setBounds(172, 171, 139, 29);
		contentPane.add(check_98);

		check_diesel = new JCheckBox("Diesel");
		check_diesel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		check_diesel.setBackground(new Color(180, 204, 225));
		check_diesel.setBounds(172, 223, 139, 29);
		contentPane.add(check_diesel);

		nappi_tilaa = new JButton("Tee tilaus");
		nappi_tilaa.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_tilaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean success = false;
				// Hakee luvut tekstikentist‰ ja muuttaa ne integereiksi
				// Laskee vanhat ja uudet m‰‰r‰t yhteen ja ilmoittaa jos aiottu tilausm‰‰r‰ on
				// liian suuri
				// ja nollaa kent‰t, jos n‰in on. Jatkuu kunnes ei tule virheilmoitusta.

				try {
					Int95 = Integer.parseInt(textField_95.getText());
					Int98 = Integer.parseInt(textField_98.getText());
					IntD = Integer.parseInt(textField_Diesel.getText());

					E95 = String.valueOf(Int95);
					E98 = String.valueOf(Int98);
					Diesel = String.valueOf(IntD);

					try {
						vanha95 = vanhaTankki(E95, 0);
						uusi95 = ("E95=" + (tankinMaarat(E95, 0)));

						iuusi95 = tankinMaaratInt(E95, 0);

						if (iuusi95 > 1000) {
							JOptionPane.showMessageDialog(null, "Tankkiin E95 ei mahdu! Tilaa v‰hemm‰n!");
							textField_95.setText("0");
							textField_98.setText("0");
							textField_Diesel.setText("0");
							iuusi95 = 0;
							success = true;
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "V‰‰r‰ syˆte");
					}

					try {
						vanha98 = vanhaTankki(E98, 1);
						uusi98 = ("E98=" + (tankinMaarat(E98, 1)));

						iuusi98 = tankinMaaratInt(E98, 1);

						if (iuusi98 > 1000) {
							JOptionPane.showMessageDialog(null, "Tankkiin E98 ei mahdu! Tilaa v‰hemm‰n!");
							textField_95.setText("0");
							textField_98.setText("0");
							textField_Diesel.setText("0");
							iuusi98 = 0;
							success = true;
						}
					} catch (IOException e1) {

						JOptionPane.showMessageDialog(null, "V‰‰r‰ syˆte");
					}

					try {
						vanhaD = vanhaTankki(Diesel, 2);
						uusiD = ("Diesel=" + (tankinMaarat(Diesel, 2)));

						iuusiD = tankinMaaratInt(Diesel, 2);

						if (iuusiD > 1000) {
							JOptionPane.showMessageDialog(null, "Tankkiin Diesel ei mahdu! Tilaa v‰hemm‰n!");
							textField_95.setText("0");
							textField_98.setText("0");
							textField_Diesel.setText("0");
							iuusiD = 0;
							success = true;
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "V‰‰r‰ syˆte");
					}
				}

				catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "V‰‰r‰ syˆte");
					textField_95.setText("0");
					textField_98.setText("0");
					textField_Diesel.setText("0");
					success = true;
					;

				} catch (HeadlessException e2) {
					// TODO Auto-generated catch block
					success = true;
				}
				// Jos success on false, kysyt‰‰n tilataanko varmasti. Jos "Yes", tulostetaan
				// tiedostoon p‰iv‰m‰‰r‰
				// Jos checkbox valittu, kirjoitetaan tilaukset-tiedostoon uusi tilaus edellisen
				// rivin per‰‰n
				// Korvataan tankit-tiedostoon uusi tieto vanhan kohdalle
				// Lopuksi asetetaan kaikki kohdat j‰lleen tyhjiksi

				if (!success) {

					int result = JOptionPane.showConfirmDialog(null, "Tilataanko varmasti?", "Tilaa",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {

						try {

							tulostaPaivamaara("Tilattu: ");

							if (check_95.isSelected()) {
								kirjoitaTilaukseen("E95: " + E95 + " litraa");
								korvaaRivit(vanha95, uusi95);

							}
							if (check_98.isSelected() && Int98 > 0) {
								kirjoitaTilaukseen("E98: " + E98 + " litraa");
								korvaaRivit(vanha98, uusi98);

							}
							if (check_diesel.isSelected() && IntD > 0) {
								kirjoitaTilaukseen("Diesel: " + Diesel + " litraa");
								korvaaRivit(vanhaD, uusiD);

							}

							textField_95.setText("0");
							textField_98.setText("0");
							textField_Diesel.setText("0");
							check_95.setSelected(false);
							check_98.setSelected(false);
							check_diesel.setSelected(false);

							Tilauslomake.this.setVisible(false);

						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					if (result == JOptionPane.NO_OPTION) {

					}
				}

			}
		});
		nappi_tilaa.setBounds(273, 297, 115, 29);
		contentPane.add(nappi_tilaa);

		lblNewLabel = new JLabel("Valitse polttoaine, jota haluat tilata.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(170, 16, 322, 50);
		contentPane.add(lblNewLabel);

		label = new JLabel("");
		label.setBounds(471, 280, 69, 20);
		contentPane.add(label);

		lblKirjoitaMrKokonaisina = new JLabel("Kirjoita m\u00E4\u00E4r\u00E4 kokonaisina litroina esim. \"100\"");
		lblKirjoitaMrKokonaisina.setHorizontalAlignment(SwingConstants.CENTER);
		lblKirjoitaMrKokonaisina.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblKirjoitaMrKokonaisina.setBounds(126, 46, 409, 50);
		contentPane.add(lblKirjoitaMrKokonaisina);

	}

	// Tulostaa tilauksen tilaukset-tiedostoon
	public void kirjoitaTilaukseen(String txt) {

		try {

			FileWriter fwriter = new FileWriter(filename, true);
			fwriter.write(txt + "\n");
			fwriter.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Lukee tankit-tiedostosta vanhan litram‰‰r‰n, laskee yhteen vanhan ja uuden
	// sek‰
	// palauttaa luvun Stringin‰
	public String tankinMaarat(String PA, int rivi) throws IOException {

		String bensa = Files.readAllLines(Paths.get(file)).get(rivi);
		String split = (bensa.split("=")[1]);
		int IntTankkiin = Integer.valueOf(split) + Integer.valueOf(PA);
		String Tankkiin = String.valueOf(IntTankkiin);
		return Tankkiin;
	}

	// Lukee tankit-tiedostosta vanhan litram‰‰r‰n, laskee yhteen vanhan ja uuden ja
	// palauttaa luvun integerin‰
	public int tankinMaaratInt(String PA, int rivi) throws IOException {

		String bensa = Files.readAllLines(Paths.get(file)).get(rivi);
		String split = (bensa.split("=")[1]);
		int IntTankkiin = Integer.valueOf(split) + Integer.valueOf(PA);
		return IntTankkiin;
	}

	// Lukee tankit-tiedostosta rivill‰ ennest‰‰n olevan litram‰‰r‰n
	public String vanhaTankki(String PA, int rivi) throws IOException {

		String bensa = Files.readAllLines(Paths.get(file)).get(rivi);
		return bensa;
	}

//
	// Korvaa tankit-tiedostoon vanhan litratiedon uudella
	public void korvaaRivit(String vanha, String uusi) throws IOException {

		Scanner sc = new Scanner(new File(file));
		StringBuffer buffer = new StringBuffer();

		while (sc.hasNextLine()) {

			buffer.append(sc.nextLine() + System.lineSeparator());
		}
		String sisalto = buffer.toString();
		System.out.println("Sis‰lto on :" + sisalto);
		sc.close();
		sisalto = sisalto.replaceAll(vanha, uusi);
		FileWriter writer = new FileWriter(file);
		System.out.println("");
		System.out.println("uusi sis‰ltˆ on:" + sisalto);
		writer.append(sisalto);
		writer.flush();
	}

	// Tulostaa tiedostoon p‰iv‰m‰‰r‰n jne
	public void tulostaPaivamaara(String txt) {

		try {
			java.util.Date date = new java.util.Date();
			FileWriter fwriter = new FileWriter(filename, true);
			fwriter.write(date.toString() + " " + txt + "\n");
			fwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
