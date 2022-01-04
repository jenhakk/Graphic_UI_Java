import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class KuittiIkkuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea_kuitti;

	private AloitusIkkuna aloitusIkkuna = new AloitusIkkuna();

	String filename2 = "src/Resources/kuitti.txt";

	/**
	 * Create the dialog.
	 */
	public KuittiIkkuna() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(KuittiIkkuna.class.getResource("/Resources/gas_station.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					new PrintWriter(filename2).close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				KuittiIkkuna.this.dispose();
				aloitusIkkuna.dispose();
				aloitusIkkuna = new AloitusIkkuna();
				aloitusIkkuna.setVisible(true);
			}
		});
		setTitle("Kuitti");
		setBounds(100, 100, 455, 431);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textArea_kuitti = new JTextArea();
		textArea_kuitti.setFont(new Font("Consolas", Font.PLAIN, 16));
		textArea_kuitti.setBackground(Color.WHITE);
		textArea_kuitti.setEditable(false);
		textArea_kuitti.setBounds(22, 0, 403, 371);
		contentPanel.add(textArea_kuitti);

		textArea_kuitti.setText(lueTiedostosta(filename2));
	}

	public String lueTiedostosta(String filename) {
		String alltext = "";

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);
			String line;

			while ((line = br.readLine()) != null) {
				alltext += line + "\n";
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
			alltext = "";
		}

		return alltext;
	}
}