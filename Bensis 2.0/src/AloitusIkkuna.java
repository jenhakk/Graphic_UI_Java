import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class AloitusIkkuna extends JFrame {

	private JPanel contentPane;
	private JButton nappi_asiakas;
	private JButton nappi_yllapito;
	private JLabel label_kayttaja;
	
	private AsiakasIkkuna asiakasIkkuna;
	private KirjautumisIkkuna kirjautumisIkkuna;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AloitusIkkuna frame = new AloitusIkkuna();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AloitusIkkuna() {
		setResizable(false);
		setForeground(Color.BLACK);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AloitusIkkuna.class.getResource("/Resources/gas_station.png")));
		setTitle("Alkuvalikko");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 307);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180, 204, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

// ******************** LABELIT ********************************************************** 

		label_kayttaja = new JLabel("Valitse k\u00E4ytt\u00E4j\u00E4:");
		label_kayttaja.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_kayttaja.setHorizontalAlignment(SwingConstants.CENTER);
		label_kayttaja.setBounds(135, 67, 160, 34);
		contentPane.add(label_kayttaja);

// ******************** NAPIT **********************************************************

		nappi_asiakas = new JButton("Asiakas");
		nappi_asiakas.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_asiakas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				asiakasIkkuna = new AsiakasIkkuna();
				asiakasIkkuna.setVisible(true);
				AloitusIkkuna.this.dispose();
			}
		});
		nappi_asiakas.setBounds(57, 125, 131, 29);
		contentPane.add(nappi_asiakas);

		nappi_yllapito = new JButton("Yll\u00E4pito");
		nappi_yllapito.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_yllapito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kirjautumisIkkuna = new KirjautumisIkkuna();
				kirjautumisIkkuna.setVisible(true);
				AloitusIkkuna.this.dispose();
			}
		});
		nappi_yllapito.setBounds(245, 125, 131, 29);
		contentPane.add(nappi_yllapito);
	}
}
