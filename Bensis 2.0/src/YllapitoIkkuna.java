import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class YllapitoIkkuna extends JFrame {

	private JPanel contentPane;
	private JButton hinnasto_btn;
	private JButton polttoaine_btn;
	private JLabel ohje;
	private JMenuBar menuBar;
	private JMenuItem kUlos;
	
	public static Bensatankki bensaTankki = new Bensatankki();
	public static HinnatPaivitys hinnatPaivitys = new HinnatPaivitys();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public YllapitoIkkuna() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(YllapitoIkkuna.class.getResource("/Resources/gas_station.png")));
		setTitle("Aloitus");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(aloitus.class.getResource("/Tiedostot/gas_station.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		kUlos = new JMenuItem("Kirjaudu ulos");
		kUlos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		// Mahdollisuus kirjautua ulos, palaa kirjautumissivulle
				
				int result = JOptionPane.showConfirmDialog(null, "Haluatko varmasti kirjautua ulos?", "Lopetetaanko?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if ( result == JOptionPane.OK_OPTION ) {
					KirjautumisIkkuna kirjautumisIkkuna = new KirjautumisIkkuna();
					kirjautumisIkkuna.setVisible(true);
					setVisible(false);
				    dispose();
					System.out.println("Ok");
				}
				if (result == JOptionPane.NO_OPTION ) {
					System.out.println("Ei lopeteta");
				}
				
			}
		});
		menuBar.add(kUlos);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180, 204, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		hinnasto_btn = new JButton("Hinnasto");
		hinnasto_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				// Siirryt‰‰n hintojen p‰ivitykseen, suljetaan aloitusikkuna
				HinnastoEtusivu hinnastoEtusivu = new HinnastoEtusivu();
				hinnastoEtusivu.setVisible(true);
				dispose();
			}
		});
		hinnasto_btn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		hinnasto_btn.setBounds(80, 137, 121, 25);
		contentPane.add(hinnasto_btn);

		polttoaine_btn = new JButton("Polttoaineet");
		polttoaine_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Siirryt‰‰n Jennan ohjelmaan polttoaineiden yll‰pitoon
				
				bensaTankki.setVisible(true);
				dispose(); // jos ohjelma toimii yhten‰isesti, t‰m‰ ikkuna voidaan sulkea jatkettaessa polttoaineisiin
			}
		});
		polttoaine_btn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		polttoaine_btn.setBounds(231, 137, 136, 25);
		contentPane.add(polttoaine_btn);

		ohje = new JLabel("Valitse mit\u00E4 haluat tehd\u00E4");
		ohje.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		ohje.setBounds(115, 62, 201, 34);
		contentPane.add(ohje);
	}
}
