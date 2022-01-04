import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Toolkit;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;

public class Tilaushistoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextArea textArea_historia;

	private String filename = "src/Resources/tilaukset.txt";
	private JButton nappi_tulosta;
	private JButton btnNewButton;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public Tilaushistoria() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Tilaushistoria.class.getResource("/Resources/gas_station.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				String teksti = lueTiedostosta(filename);
				textArea_historia.setText(teksti);
			}
		});
		setTitle("Tilaushistoria");
		setBounds(100, 100, 630, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(180,204,225));
		contentPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
			}
		});
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		nappi_tulosta = new JButton("Tulosta");
		nappi_tulosta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_tulosta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon("Resources/tulostin.png");
				int result = JOptionPane.showConfirmDialog(null, "Tulostetaanko", "Tulosta", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {

					JOptionPane.showMessageDialog(null, "Tilaushistoriaa tulostetaan...", "Tulostus käynnissä",
							JOptionPane.INFORMATION_MESSAGE, icon);

				}

				if (result == JOptionPane.NO_OPTION) {

				}
			}
		});
		nappi_tulosta.setBounds(98, 362, 115, 29);
		contentPanel.add(nappi_tulosta);

		btnNewButton = new JButton("Tyhjenn\u00E4 tilaushistoria");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null,
						"Tyhjennetäänkö tilaushistoria varmasti? Tietoja ei voi palauttaa!", "Tyhjennä",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {

					try {
						tyhjennaHistoria(filename);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String tyhja = lueTiedostosta(filename);
					textArea_historia.setText(tyhja);
				}

				if (result == JOptionPane.NO_OPTION) {

				}

			}
		});
		btnNewButton.setBounds(311, 362, 213, 29);
		contentPanel.add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 38, 545, 308);
		contentPanel.add(scrollPane);

		textArea_historia = new JTextArea();
		scrollPane.setViewportView(textArea_historia);
		textArea_historia.setFont(new Font("Consolas", Font.PLAIN, 15));
		textArea_historia.setEditable(false);
		
	}

	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			ImageIcon icon = new ImageIcon(imgURL, null);
			return icon;

		} else {
			System.err.println("Couldn't find file. " + path);
			return null;
		}
	}
		//Metodi joka tyhjentää tilaushistorian
	public void tyhjennaHistoria(String filename) throws IOException {

		FileWriter writer = new FileWriter(filename);
		writer.write("");
		writer.close();

	}
		//Metodi joka lukee tiedoston tiedot
	public String lueTiedostosta(String filename) {

		String alltext = "";

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);
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
}
