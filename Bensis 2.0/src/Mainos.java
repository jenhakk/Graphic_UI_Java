import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JMenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JMenu;

public class Mainos extends JFrame {

	private JPanel contentPane;
	private JTextField lue98;
	private JTextField lue95;
	private JTextField lueD;

	String filename1 = "src/Resources/mainos.txt";
	String filename2 = "src/Resources/hinnat.txt";
	String E95;
	String E98;
	String Diesel;
	private JLabel tarjous;
	private JLabel ysiviis;
	private JLabel ysikasi;
	private JLabel diesel;
	private JLabel lb_avoinna;
	private JLabel mainostxt;
	private JMenuItem mntmNewMenuItem;
	private JMenuBar menuBar;
	private JMenuItem teema2;
	private JMenuItem teema1;
	
	String [] list = { "Resources/tk2.jpg", // 0
					   "Resources/tk3.jpg", // 1
					   "Resources/tk1.jpg", // 2
					   "Resources/tk4.jpg" // 3
	};
	private JMenuItem teema3;
	private JLabel tk2;
	private JMenuBar menuBar_1;
	private JMenu menubar;
	private JMenuItem teema4;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Mainos() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				// ikkunan aktivoituessa luetaan mainos ja hintatiedot paikoilleen
				String mainos = lueMainos(filename1);
				mainostxt.setText(mainos);

				lueHinnat(filename2);
				E95 = mainostxt.getText();
				E98 = lue98.getText();
				Diesel = lueD.getText();

			}
		});
		setTitle("Mainoskyltti");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mainos.class.getResource("/Resources/gas_station.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 510, 620);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menubar = new JMenu("Valitse teema");
		menuBar.add(menubar);
		
		teema2 = new JMenuItem("Kes\u00E4");
		menubar.add(teema2);
		teema2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon icon = createImageIcon(list[1], "");
				tk2.setIcon(icon);
			}
		});
		
		teema1 = new JMenuItem("Talvi");
		menubar.add(teema1);
		
		teema3 = new JMenuItem("Syksy");
		menubar.add(teema3);
		
		teema4 = new JMenuItem("Oletus");
		teema4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon(list[3], "");
				tk2.setIcon(icon);
			}
		});
		menubar.add(teema4);
		teema3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon icon = createImageIcon(list[2], "");
				tk2.setIcon(icon);
			}
		});
		teema1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon(list[0], "");
				tk2.setIcon(icon);
			}
		});
		
		menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(180,204,225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lue98 = new JTextField();
		lue98.setBorder(null);
		lue98.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		lue98.setHorizontalAlignment(SwingConstants.CENTER);
		lue98.setEditable(false);
		lue98.setBounds(201, 426, 101, 54);
		contentPane.add(lue98);
		lue98.setColumns(10);

		lue95 = new JTextField();
		lue95.setBorder(null);
		lue95.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		lue95.setHorizontalAlignment(SwingConstants.CENTER);
		lue95.setEditable(false);
		lue95.setColumns(10);
		lue95.setBounds(24, 426, 101, 54);
		contentPane.add(lue95);

		lueD = new JTextField();
		lueD.setBorder(null);
		lueD.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		lueD.setHorizontalAlignment(SwingConstants.CENTER);
		lueD.setEditable(false);
		lueD.setColumns(10);
		lueD.setBounds(365, 426, 101, 54);
		contentPane.add(lueD);

		tarjous = new JLabel("P\u00C4IV\u00C4N TARJOUS");
		tarjous.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 32));
		tarjous.setHorizontalAlignment(SwingConstants.CENTER);
		tarjous.setBounds(27, 165, 454, 52);
		contentPane.add(tarjous);

		ysiviis = new JLabel("E95");
		ysiviis.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		ysiviis.setHorizontalAlignment(SwingConstants.CENTER);
		ysiviis.setBounds(24, 357, 101, 56);
		contentPane.add(ysiviis);

		ysikasi = new JLabel("E98");
		ysikasi.setHorizontalAlignment(SwingConstants.CENTER);
		ysikasi.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		ysikasi.setBounds(201, 357, 101, 56);
		contentPane.add(ysikasi);

		diesel = new JLabel("DIESEL");
		diesel.setHorizontalAlignment(SwingConstants.CENTER);
		diesel.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		diesel.setBounds(365, 357, 101, 56);
		contentPane.add(diesel);

		lb_avoinna = new JLabel("avoinna 24 h");
		lb_avoinna.setHorizontalAlignment(SwingConstants.CENTER);
		lb_avoinna.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 30));
		lb_avoinna.setBounds(123, 493, 230, 52);
		contentPane.add(lb_avoinna);

		mainostxt = new JLabel("");
		mainostxt.setHorizontalAlignment(SwingConstants.CENTER);
		mainostxt.setFont(new Font("Source Serif Pro Black", Font.PLAIN, 25));
		mainostxt.setBounds(37, 217, 426, 92);
		contentPane.add(mainostxt);
		
		tk2 = new JLabel("");
		tk2.setIcon(new ImageIcon(Mainos.class.getResource("/Resources/tk4.jpg")));
		tk2.setBounds(0, 0, 514, 559);
		contentPane.add(tk2);
	}

	public String lueMainos(String filename1) {
		// luetaan päivitetty mainos mainos.txt
		String alltext = "";
		try {
			FileReader reader = new FileReader(filename1);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				alltext = alltext + line + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
			alltext = "";
		}
		return alltext;

	}

	public void lueHinnat(String filename2) {
		// luetaan päivitetyt hinnat hinnat.txt
		try {
			FileReader freader = new FileReader(filename2);
			BufferedReader br = new BufferedReader(freader);
			E95 = br.readLine();
			E95 = kasitteleRivi(E95);
			lue95.setText(E95);

			E98 = br.readLine();
			E98 = kasitteleRivi(E98);
			lue98.setText(E98);

			Diesel = br.readLine();
			Diesel = kasitteleRivi(Diesel);
			lueD.setText(Diesel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String kasitteleRivi(String txt) {
		// haetaan pelkkä hinta-arvo
		txt = txt.replace(" ", "");
		String[] temp = txt.split("=");

		return temp[1];

	}
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			ImageIcon icon = new ImageIcon(imgURL, description);
			Image img = icon.getImage();
			Image newImg = img.getScaledInstance(tk2.getWidth(), tk2.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon newImc = new ImageIcon(newImg);
			
			return newImc;
		} else {
			System.err.println("Tiedostoa ei löytynyt" + path);
			return null;
					
		}
	}
	}
