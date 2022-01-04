import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;

public class MainosYllapito extends JFrame {

	private JPanel contentPane;
	private JTextField mainosteksti;
	private JButton luemainos;
	String filename = "src/Resources/mainos.txt";
	private JTextField txtLuoPivnMainos;
	private JTextField textField_1;
	private JMenuBar menuBar;
	private JMenuItem palaa;
	private JLabel pvMainos;
	private JLabel max;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainosYllapito() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainosYllapito.class.getResource("/Resources/gas_station.png")));
		setResizable(false);
		setTitle("Luo mainos");
		getContentPane().setLayout(null);
		txtLuoPivnMainos = new JTextField();
		txtLuoPivnMainos.setText("Luo p\u00E4iv\u00E4n mainos");
		txtLuoPivnMainos.setHorizontalAlignment(SwingConstants.CENTER);
		txtLuoPivnMainos.setEditable(false);
		txtLuoPivnMainos.setBounds(50, 26, 260, 31);
		getContentPane().add(txtLuoPivnMainos);
		txtLuoPivnMainos.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(31, 85, 306, 88);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 382, 410);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		palaa = new JMenuItem("Palaa takaisin");
		palaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HinnastoEtusivu hinnastoEtusivu = new HinnastoEtusivu();
				hinnastoEtusivu.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		menuBar.add(palaa);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180, 204, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainosteksti = new JTextField();
		mainosteksti.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			        if (mainosteksti.getText().length() >= 25 ) // max merkkim‰‰r‰ 25 merkki‰
			            e.consume(); 
			    }  
			});
		mainosteksti.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		mainosteksti.setHorizontalAlignment(SwingConstants.CENTER);
		mainosteksti.setBounds(12, 99, 340, 66);
		contentPane.add(mainosteksti);
		mainosteksti.setColumns(10);

		luemainos = new JButton("P\u00E4ivit\u00E4 mainos");
		luemainos.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		luemainos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				kirjoitaTiedostoon(mainosteksti.getText(), filename);
				JOptionPane.showMessageDialog(null, "Mainos p‰ivitetty");

			}
		});
		luemainos.setBounds(88, 242, 188, 45);
		contentPane.add(luemainos);

		pvMainos = new JLabel("Luo mainos");
		pvMainos.setFont(new Font("Yu Gothic UI", Font.PLAIN, 25));
		pvMainos.setHorizontalAlignment(SwingConstants.CENTER);
		pvMainos.setBounds(88, 13, 188, 45);
		contentPane.add(pvMainos);
		
		max = new JLabel("max 25 merkki\u00E4");
		max.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		max.setHorizontalAlignment(SwingConstants.CENTER);
		max.setBounds(103, 169, 170, 25);
		contentPane.add(max);
	}

	public void kirjoitaTiedostoon(String mainostxt, String filename) {
		try {
			FileWriter writer2 = new FileWriter(filename, false);
			writer2.write(mainostxt + "\n");
			writer2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String kasitteleRivi(String txt) {
		txt = txt.replace(" ", "");
		String[] temp = txt.split("=");
		return temp[1];
	}

}
