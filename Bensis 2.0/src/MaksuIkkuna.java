import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class MaksuIkkuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JPasswordField textField_pin;
	public JLabel label_loppusumma;
	private JButton nappi_tyhjenna;

	private KuittiIkkuna kuittiIkkuna = new KuittiIkkuna();
	private AloitusIkkuna aloitusIkkuna = new AloitusIkkuna();
	private AsiakasIkkuna paaIkkuna;

	String filename2 = "src/Resources/kuitti.txt";
	String filename4 = "src/Resources/tankit.txt";
	String filename5 = "src/Resources/tankattu.txt";

	String E95;
	String E98;
	String Diesel;
	String vanha95;
	String vanha98;
	String vanhaD;
	String haettu95 = " ";
	String haettu98 = " ";
	String haettuD = " ";
	String lopullinen95 = " ";
	String lopullinen98 = " ";
	String lopullinenD = " ";

	int Int95;
	int Int98;
	int IntD;
	int tankattu95;
	int tankattu98;
	int tankattuD;
	static int uusi95;
	static int uusi98;
	static int uusiD;

	/**
	 * Create the dialog.
	 */
	public MaksuIkkuna(AsiakasIkkuna paaIkkuna) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MaksuIkkuna.class.getResource("/Resources/gas_station.png")));
		setTitle("Maksutapahtuma");
		this.paaIkkuna = paaIkkuna;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(180, 204, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

// ******************** TEKSTIKENTÄT **********************************************************

		textField_pin = new JPasswordField();
		textField_pin.setBackground(Color.WHITE);
		textField_pin.setEditable(false);
		textField_pin.setBounds(32, 44, 146, 26);
		contentPanel.add(textField_pin);
		textField_pin.setColumns(10);

// ******************** LABELIT **********************************************************

		JLabel lblSytPinkoodi = new JLabel("Sy\u00F6t\u00E4 pin-koodi:");
		lblSytPinkoodi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblSytPinkoodi.setBounds(32, 13, 134, 25);
		contentPanel.add(lblSytPinkoodi);

		label_loppusumma = new JLabel("");
		label_loppusumma.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		label_loppusumma.setHorizontalAlignment(SwingConstants.TRAILING);
		label_loppusumma.setBounds(177, 13, 79, 25);
		contentPanel.add(label_loppusumma);

// ******************** NUMERONAPIT **********************************************************

		JButton nappi1 = new JButton("1");
		nappi1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi1.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi1.setBounds(32, 94, 65, 55);
		contentPanel.add(nappi1);

		JButton nappi2 = new JButton("2");
		nappi2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi2.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi2.setBounds(113, 94, 65, 55);
		contentPanel.add(nappi2);

		JButton nappi3 = new JButton("3");
		nappi3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi3.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi3.setBounds(191, 94, 65, 55);
		contentPanel.add(nappi3);

		JButton nappi4 = new JButton("4");
		nappi4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi4.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi4.setBounds(32, 162, 65, 55);
		contentPanel.add(nappi4);

		JButton nappi5 = new JButton("5");
		nappi5.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi5.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi5.setBounds(113, 162, 65, 55);
		contentPanel.add(nappi5);

		JButton nappi6 = new JButton("6");
		nappi6.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi6.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi6.setBounds(191, 162, 65, 55);
		contentPanel.add(nappi6);

		JButton nappi7 = new JButton("7");
		nappi7.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi7.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi7.setBounds(32, 230, 65, 55);
		contentPanel.add(nappi7);

		JButton nappi8 = new JButton("8");
		nappi8.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi8.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi8.setBounds(113, 230, 65, 55);
		contentPanel.add(nappi8);

		JButton nappi9 = new JButton("9");
		nappi9.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi9.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi9.setBounds(191, 230, 65, 55);
		contentPanel.add(nappi9);

		JButton nappi0 = new JButton("0");
		nappi0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String syotaPin = textField_pin.getText() + nappi0.getText();
				textField_pin.setText(syotaPin);
			}
		});
		nappi0.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi0.setBounds(113, 298, 65, 55);
		contentPanel.add(nappi0);

// ******************** NAPIT **********************************************************

		JButton nappi_maksa = new JButton("");
		nappi_maksa.setIcon(new ImageIcon(MaksuIkkuna.class.getResource("/Resources/checked (1).png")));
		nappi_maksa.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		nappi_maksa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String oikea_pin = crypt("1234");
				String pin_crypted = "";

				try {
					pin_crypted = crypt(textField_pin.getText());

				} catch (Exception e1) {
				}

				if (oikea_pin.equals(pin_crypted)) {
					int kuitti = JOptionPane.showConfirmDialog(null, "Pin-koodi oikein, haluatko tulostaa kuitin?",
							"Maksu hyväksytty", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					Int95 = lueMaara95(filename4);
					Int98 = lueMaara98(filename4);
					IntD = lueMaaraDiesel(filename4);

					tankattu95 = lueMaaraOstettu95(filename5);
					tankattu98 = lueMaaraOstettu98(filename5);
					tankattuD = lueMaaraOstettuD(filename5);

					uusi95 = Int95 - tankattu95;
					uusi98 = Int98 - tankattu98;
					uusiD = IntD - tankattuD;

					try {
						haettu95 = vanhaTankki(vanha95, 0);
						haettu98 = vanhaTankki(vanha98, 1);
						haettuD = vanhaTankki(vanhaD, 2);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					lopullinen95 = "E95=" + (String.valueOf(uusi95));
					lopullinen98 = "E98=" + (String.valueOf(uusi98));
					lopullinenD = "Diesel=" + (String.valueOf(uusiD));

					try {
						korvaaRivit(haettu95, lopullinen95);
						korvaaRivit(haettu98, lopullinen98);
						korvaaRivit(haettuD, lopullinenD);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (kuitti == JOptionPane.OK_OPTION) {

						paaIkkuna.dispose();
						MaksuIkkuna.this.setVisible(false);
						kuittiIkkuna.dispose();
						kuittiIkkuna = new KuittiIkkuna();
						kuittiIkkuna.setModal(true);
						kuittiIkkuna.setVisible(true);

					} else {
						try {
							new PrintWriter(filename2).close();

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						paaIkkuna.dispose();
						MaksuIkkuna.this.dispose();
						aloitusIkkuna.dispose();
						aloitusIkkuna = new AloitusIkkuna();
						aloitusIkkuna.setVisible(true);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Väärä pin-koodi, syötä pin-koodi uudestaan.", "Maksu hylätty",
							JOptionPane.ERROR_MESSAGE);
					textField_pin.setText("");
				}
			}
		});
		nappi_maksa.setBounds(32, 298, 65, 55);
		contentPanel.add(nappi_maksa);

		JButton nappi_peruuta = new JButton("");
		nappi_peruuta.setIcon(new ImageIcon(MaksuIkkuna.class.getResource("/Resources/cancel (1).png")));
		nappi_peruuta.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		nappi_peruuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaksuIkkuna.this.dispose();
			}
		});
		nappi_peruuta.setBounds(191, 298, 65, 55);
		contentPanel.add(nappi_peruuta);

		nappi_tyhjenna = new JButton("");
		nappi_tyhjenna.setIcon(new ImageIcon(MaksuIkkuna.class.getResource("/Resources/delete.png")));
		nappi_tyhjenna.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		nappi_tyhjenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_pin.setText("");
			}
		});
		nappi_tyhjenna.setBounds(190, 44, 65, 26);
		contentPanel.add(nappi_tyhjenna);
	}

	public static String crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}

		MessageDigest digester;
		try {
			digester = MessageDigest.getInstance("MD5");

			digester.update(str.getBytes());
			byte[] hash = digester.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
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

	public int lueMaaraOstettu95(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);

			tankattu95 = Integer.parseInt(E95);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tankattu95;
	}

	public int lueMaaraOstettu98(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);

			tankattu98 = Integer.parseInt(E98);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tankattu98;
	}

	public int lueMaaraOstettuD(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);

			tankattuD = Integer.parseInt(Diesel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tankattuD;
	}

	public static String kasitteleRivi(String txt) {

		String[] temp = txt.split("=");
		return temp[1];
	}

	// Lukee tankit-tiedostosta rivillä ennestään olevan litramäärän
	public String vanhaTankki(String PA, int rivi) throws IOException {

		String bensa = Files.readAllLines(Paths.get(filename4)).get(rivi);
		return bensa;
	}

	// Korvaa tankit-tiedostoon vanhan litratiedon uudella
	public void korvaaRivit(String vanha, String uusi) throws IOException {

		Scanner sc = new Scanner(new File(filename4));
		StringBuffer buffer = new StringBuffer();

		while (sc.hasNextLine()) {

			buffer.append(sc.nextLine() + System.lineSeparator());
		}
		String sisalto = buffer.toString();
		sc.close();
		sisalto = sisalto.replaceAll(vanha, uusi);
		FileWriter writer = new FileWriter(filename4);
		System.out.println("");
		writer.append(sisalto);
		writer.flush();
	}

}
