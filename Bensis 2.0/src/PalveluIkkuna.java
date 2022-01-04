import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class PalveluIkkuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JCheckBox checkbox_kahvi;
	public JCheckBox checkbox_pulla;
	public JCheckBox checkbox_lounas;
	public JLabel label_kahvi_hinta;
	public JLabel label_pulla_hinta;
	public JLabel label_lounas_hinta;
	public JButton nappi_palaa;
	private JLabel label_euroa1;
	private JLabel label_euroa2;
	private JLabel label_euroa3;
	public JTextField textField_kpl1;
	public JTextField textField_kpl2;
	public JTextField textField_kpl3;

	String hintaKahvi;
	String hintaPulla;
	String hintaLounas;

	String filename1 = "src/Resources/muut_hinnat.txt";

	/**
	 * Create the dialog.
	 */
	public PalveluIkkuna() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PalveluIkkuna.class.getResource("/Resources/gas_station.png")));
		setTitle("Muut tuotteet");
		File f = new File(filename1);
		if (!f.exists()) {
			alustaHinnat();
		}

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(180, 204, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

// ******************** CHECKBOXIT **********************************************************

		checkbox_kahvi = new JCheckBox("Kahvi");
		checkbox_kahvi.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		checkbox_kahvi.setBackground(new Color(180, 204, 225));
		checkbox_kahvi.setBounds(12, 48, 83, 26);
		contentPanel.add(checkbox_kahvi);

		checkbox_pulla = new JCheckBox("Pulla");
		checkbox_pulla.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		checkbox_pulla.setBackground(new Color(180, 204, 225));
		checkbox_pulla.setBounds(12, 80, 83, 26);
		contentPanel.add(checkbox_pulla);

		checkbox_lounas = new JCheckBox("Lounas");
		checkbox_lounas.setBackground(new Color(180, 204, 225));
		checkbox_lounas.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		checkbox_lounas.setBounds(12, 110, 83, 26);
		contentPanel.add(checkbox_lounas);

// ******************** NAPIT **********************************************************		

		nappi_palaa = new JButton("Palaa takaisin");
		nappi_palaa.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_palaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PalveluIkkuna.this.setVisible(false);
			}
		});
		nappi_palaa.setBounds(274, 215, 146, 29);
		contentPanel.add(nappi_palaa);

// ******************** LABELIT **********************************************************

		JLabel lblMuutTuotteet = new JLabel("Valitse haluttu tuote ja m\u00E4\u00E4r\u00E4:");
		lblMuutTuotteet.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblMuutTuotteet.setBounds(12, 13, 246, 26);
		contentPanel.add(lblMuutTuotteet);

		label_kahvi_hinta = new JLabel("");
		label_kahvi_hinta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_kahvi_hinta.setBounds(151, 50, 36, 26);
		contentPanel.add(label_kahvi_hinta);

		label_pulla_hinta = new JLabel("");
		label_pulla_hinta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_pulla_hinta.setBounds(151, 80, 36, 26);
		contentPanel.add(label_pulla_hinta);

		label_lounas_hinta = new JLabel("");
		label_lounas_hinta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_lounas_hinta.setBounds(151, 110, 36, 26);
		contentPanel.add(label_lounas_hinta);

		label_euroa1 = new JLabel("\u20AC");
		label_euroa1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_euroa1.setBounds(188, 50, 26, 26);
		contentPanel.add(label_euroa1);

		label_euroa2 = new JLabel("\u20AC");
		label_euroa2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_euroa2.setBounds(188, 80, 26, 26);
		contentPanel.add(label_euroa2);

		label_euroa3 = new JLabel("\u20AC");
		label_euroa3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_euroa3.setBounds(188, 110, 26, 26);
		contentPanel.add(label_euroa3);

// ******************** TEKSTIKENTÄT **********************************************************

		textField_kpl1 = new JTextField();
		textField_kpl1.setText("1");
		textField_kpl1.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_kpl1.setBounds(103, 52, 36, 26);
		contentPanel.add(textField_kpl1);
		textField_kpl1.setColumns(10);

		textField_kpl2 = new JTextField();
		textField_kpl2.setText("1");
		textField_kpl2.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_kpl2.setColumns(10);
		textField_kpl2.setBounds(103, 80, 36, 26);
		contentPanel.add(textField_kpl2);

		textField_kpl3 = new JTextField();
		textField_kpl3.setText("1");
		textField_kpl3.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_kpl3.setColumns(10);
		textField_kpl3.setBounds(103, 110, 36, 26);
		contentPanel.add(textField_kpl3);

		lueHinnat(filename1);
	}

	public void lueHinnat(String filename) {
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			hintaKahvi = kasitteleRivi(br.readLine());
			hintaPulla = kasitteleRivi(br.readLine());
			hintaLounas = kasitteleRivi(br.readLine());

			label_kahvi_hinta.setText(hintaKahvi);
			label_pulla_hinta.setText(hintaPulla);
			label_lounas_hinta.setText(hintaLounas);

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

	public void alustaHinnat() {
		String kirjoitettava = "Kahvi = 1.5" + "Pulla = 2.0" + "Lounas = 8.9";

		kirjoitaTiedostoon(kirjoitettava, filename1);
	}

	public String kasitteleRivi(String txt) {
		txt = txt.replace(" ", "");
		String[] temp = txt.split("=");

		return temp[1];
	}
}