
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileWriter;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Toolkit;

public class PaaIkkuna extends JFrame {

	private JPanel contentPane;
	private JTextField Luku1;
	private JTextField Luku2;
	private JButton Nappi_plus;
	private JButton Nappi_miinus;
	private JButton Nappi_kerto;
	private JButton Nappi_jako;
	private JTextField Tulos_boksi;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem sulje;
	private JCheckBox Checkbox;

	String filename = "src/Resources/nelilaskin.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaaIkkuna frame = new PaaIkkuna();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Kun ohjelma ajetaan, tiedostoon kirjoitetaan aika ja "Ohjelma käynnistetty"
				try {
					java.util.Date date = new java.util.Date();

					FileWriter fwriter = new FileWriter("src/Resources/nelilaskin.txt", true);
					fwriter.write(date.toString() + " " + "Ohjelma käynnistetty" + "\n");
					fwriter.close();

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public PaaIkkuna() {
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PaaIkkuna.class.getResource("/Resources/calculator (1).png")));
		setBackground(new Color(204, 204, 204));
		addWindowListener(new WindowAdapter() {
			@Override

			// Kun painetaan ruksia, tulee messagebox ja mikäli suljetaan, tiedostoon
			// tulostuu aika ja "Ohjelma lopetettu
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Haluatko varmasti lopettaa?", "Lopetus?",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					System.out.println("Ok painettu");

					tulostaPaivamaara("Ohjelma lopetettu");
					System.exit(0);
				}

				if (result == JOptionPane.NO_OPTION) {
					System.out.println("No painettu");
				}

			}

		});
		setTitle("Nelilaskin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 479);

		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(153, 204, 255));
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Valikko");
		mnNewMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnNewMenu.setBackground(new Color(204, 153, 255));
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Tyhjenn\u00E4");
		mntmNewMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mntmNewMenuItem.setBackground(new Color(153, 204, 255));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Kun valitaan valikosta "Tyhjennä", tekstibokseihin tulostetaan tyhjää
				Luku1.setText("");
				Luku2.setText("");
				Tulos_boksi.setText("");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		sulje = new JMenuItem("Sulje ohjelma");
		sulje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sulje.setBackground(new Color(153, 204, 255));
		sulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Kun valitaan valikosta "Sulje ohjelma", tulee messagebox
				// ja mikäli suljetaan, tiedostoon tulostuu aika ja "Ohjelma lopetettu

				int result = JOptionPane.showConfirmDialog(null, "Haluatko varmasti lopettaa?", "Lopetus?",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					System.out.println("Ok painettu");

					tulostaPaivamaara("Ohjelma lopetettu");
					System.exit(0);
				}

				if (result == JOptionPane.NO_OPTION) {
					System.out.println("No painettu");
				}
			}
		});
		mnNewMenu.add(sulje);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Luku1 = new JTextField();
		Luku1.setHorizontalAlignment(SwingConstants.CENTER);
		Luku1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Luku1.setBounds(35, 68, 96, 51);
		contentPane.add(Luku1);
		Luku1.setColumns(10);

		Luku2 = new JTextField();
		Luku2.setHorizontalAlignment(SwingConstants.CENTER);
		Luku2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Luku2.setColumns(10);
		Luku2.setBounds(35, 180, 96, 51);
		contentPane.add(Luku2);

		// Plus-nappi ja toiminnot
		Nappi_plus = new JButton("+");
		Nappi_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int ekaLuku = Integer.parseInt(Luku1.getText());
					int tokaLuku = Integer.parseInt(Luku2.getText());
					String tulos = String.valueOf(ekaLuku + tokaLuku);
					Tulos_boksi.setText(ekaLuku + " + " + tokaLuku + " = " + tulos);
					// Jos checkbox valittu, tulostetaan laskutoimitus tiedostoon
					if (Checkbox.isSelected()) {
						tulostaTiedostoon("Laskutoimitus: " + ekaLuku + " + " + tokaLuku + " = " + tulos);
					}
					// Jos kirjoitetaan tekstiä, tulee virhe ja tekstiboksit tyhjennetään
				} catch (NumberFormatException e) {
					Tulos_boksi.setText("");
					Luku1.setText("");
					Luku2.setText("");
					JOptionPane.showMessageDialog(null, "Syötä vain numeroita", "Virhe", JOptionPane.WARNING_MESSAGE);
					// e.printStackTrace();
				}

			}
		});
		Nappi_plus.setForeground(new Color(135, 206, 250));
		Nappi_plus.setFont(new Font("Tahoma", Font.BOLD, 45));
		Nappi_plus.setBounds(249, 135, 96, 92);
		contentPane.add(Nappi_plus);

		// Miinus-nappi ja toiminnot
		Nappi_miinus = new JButton("-");
		Nappi_miinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ekaLuku = Integer.parseInt(Luku1.getText());
					int tokaLuku = Integer.parseInt(Luku2.getText());
					String tulos = String.valueOf(ekaLuku - tokaLuku);
					Tulos_boksi.setText(ekaLuku + " - " + tokaLuku + " = " + tulos);

					if (Checkbox.isSelected()) {
						tulostaTiedostoon("Laskutoimitus: " + ekaLuku + " - " + tokaLuku + " = " + tulos);
					}
				} catch (NumberFormatException e1) {
					Tulos_boksi.setText("");
					Luku1.setText("");
					Luku2.setText("");
					JOptionPane.showMessageDialog(null, "Syötä vain numeroita", "Virhe", JOptionPane.WARNING_MESSAGE);
					// e1.printStackTrace();
				}
			}
		});
		Nappi_miinus.setForeground(new Color(135, 206, 250));
		Nappi_miinus.setFont(new Font("Tahoma", Font.BOLD, 45));
		Nappi_miinus.setBounds(380, 135, 96, 92);
		contentPane.add(Nappi_miinus);

		// Kerto-nappi ja toiminnot
		Nappi_kerto = new JButton("*");
		Nappi_kerto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ekaLuku = Integer.parseInt(Luku1.getText());
					int tokaLuku = Integer.parseInt(Luku2.getText());
					String tulos = String.valueOf(ekaLuku * tokaLuku);
					Tulos_boksi.setText(ekaLuku + " * " + tokaLuku + " = " + tulos);

					if (Checkbox.isSelected()) {
						tulostaTiedostoon("Laskutoimitus: " + ekaLuku + " * " + tokaLuku + " = " + tulos);

					}
				} catch (NumberFormatException e1) {
					Tulos_boksi.setText("");
					Luku1.setText("");
					Luku2.setText("");
					JOptionPane.showMessageDialog(null, "Syötä vain numeroita", "Virhe", JOptionPane.WARNING_MESSAGE);
					// e1.printStackTrace();
				}
			}
		});
		Nappi_kerto.setForeground(new Color(135, 206, 250));
		Nappi_kerto.setFont(new Font("Tahoma", Font.BOLD, 45));
		Nappi_kerto.setBounds(249, 247, 96, 92);
		contentPane.add(Nappi_kerto);

		// Jako-nappi ja toiminnot
		Nappi_jako = new JButton("/");
		Nappi_jako.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float ekaLuku = Integer.parseInt(Luku1.getText());
					float tokaLuku = Integer.parseInt(Luku2.getText());
					String tulos = String.valueOf(ekaLuku / tokaLuku);

					// Virheet jos laitetaan "0" jompaankumpaan tekstiruutuun
					if (ekaLuku == 0) {
						JOptionPane.showMessageDialog(null, "Et voi jakaa nollaa!", "Virhe",
								JOptionPane.WARNING_MESSAGE);

					} else if (tokaLuku == 0) {
						JOptionPane.showMessageDialog(null, "Nollalla ei voi jakaa!", "Virhe",
								JOptionPane.WARNING_MESSAGE);
					}

					else {

						Tulos_boksi.setText(ekaLuku + " / " + tokaLuku + " = " + tulos);
					}

					if (Checkbox.isSelected()) {
						tulostaTiedostoon("Laskutoimitus: " + ekaLuku + " / " + tokaLuku + " = " + tulos);

					}
				} catch (NumberFormatException e1) {
					Tulos_boksi.setText("");
					Luku1.setText("");
					Luku2.setText("");
					JOptionPane.showMessageDialog(null, "Syötä vain numeroita", "Virhe", JOptionPane.WARNING_MESSAGE);
					// e1.printStackTrace();
				}
			}

		});
		Nappi_jako.setForeground(new Color(135, 206, 250));
		Nappi_jako.setFont(new Font("Tahoma", Font.PLAIN, 45));
		Nappi_jako.setBounds(380, 247, 96, 92);
		contentPane.add(Nappi_jako);

		Tulos_boksi = new JTextField();
		Tulos_boksi.setBackground(Color.WHITE);
		Tulos_boksi.setHorizontalAlignment(SwingConstants.CENTER);
		Tulos_boksi.setEditable(false);
		Tulos_boksi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Tulos_boksi.setColumns(10);
		Tulos_boksi.setBounds(249, 56, 227, 51);
		contentPane.add(Tulos_boksi);

		lblNewLabel = new JLabel("Tulos");
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(331, 27, 73, 20);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("1. luku");
		lblNewLabel_1.setForeground(new Color(100, 149, 237));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(45, 27, 73, 36);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("2. luku");
		lblNewLabel_2.setForeground(new Color(100, 149, 237));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(45, 141, 93, 36);
		contentPane.add(lblNewLabel_2);

		Checkbox = new JCheckBox("Kirjoitetaan tulos tiedostoon");
		Checkbox.setBackground(new Color(153, 204, 255));
		Checkbox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

			}
		});
		Checkbox.setFont(new Font("Tahoma", Font.BOLD, 16));
		Checkbox.setSelected(true);
		Checkbox.setBounds(11, 351, 284, 29);
		contentPane.add(Checkbox);
	}

	// Metodi joka tulostaa tekstiä tiedostoon
	public void tulostaTiedostoon(String txt) {

		try {

			// Valinta true lopussa aiheuttaa sen ettei ylikirjoiteta vaan jatketaan
			// olemassaolevan listan loppuun

			FileWriter fwriter = new FileWriter(filename, true);
			fwriter.write(txt + "\n");
			fwriter.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Metodi joka tulostaa ajankohdan tiedostoon
	public void tulostaPaivamaara(String txt) {

		try {
			java.util.Date date = new java.util.Date();
			// Valinta true lopussa aiheuttaa sen ettei ylikirjoiteta vaan jatketaan
			// olemassaolevan listan loppuun

			FileWriter fwriter = new FileWriter(filename, true);
			fwriter.write(date.toString() + " " + txt + "\n");
			fwriter.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
