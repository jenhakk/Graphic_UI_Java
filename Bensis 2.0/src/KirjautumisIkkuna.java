import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class KirjautumisIkkuna extends JFrame {

	private JPanel contentPane;
	private JLabel label_kayttaja_noedit;
	private JTextField textField_kayttaja;
	private JLabel label_salasana_noedit;
	private JPasswordField text_salasana;
	private JButton nappi_kirjaudu;
	
	public static Bensatankki bensaTankki = new Bensatankki();
	private Tilaushistoria tilausHistoria = new Tilaushistoria();
	public static YllapitoIkkuna yllapitoIkkuna = new YllapitoIkkuna(); 
//	public static Siirtymisikkuna1 siirtymisIkkuna1 = new Siirtymisikkuna1();
	public static AloitusIkkuna aloitusIkkuna = new AloitusIkkuna();

	
	String tunnus;
	String salasana;
	
	String filename = "src/Resources/tunnukset.txt";
	private JButton nappi_palaa;


	/**
	 * Create the frame.
	 */
	public KirjautumisIkkuna() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KirjautumisIkkuna.class.getResource("/Resources/gas_station.png")));
		setResizable(false);
		setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		setForeground(new Color(102, 204, 102));
		setBackground(new Color(245, 255, 250));
		setTitle("Kirjaudu sis\u00E4\u00E4n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 436);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(180,204,225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_kayttaja_noedit = new JLabel("Sy\u00F6t\u00E4 k\u00E4ytt\u00E4j\u00E4tunnus");
		label_kayttaja_noedit.setHorizontalAlignment(SwingConstants.CENTER);
		label_kayttaja_noedit.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_kayttaja_noedit.setBounds(199, 60, 176, 29);
		contentPane.add(label_kayttaja_noedit);
		
		textField_kayttaja = new JTextField();
		textField_kayttaja.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField_kayttaja.setHorizontalAlignment(SwingConstants.CENTER);
		textField_kayttaja.setBounds(196, 105, 182, 29);
		contentPane.add(textField_kayttaja);
		textField_kayttaja.setColumns(10);
		
		label_salasana_noedit = new JLabel("Sy\u00F6t\u00E4 salasana");
		label_salasana_noedit.setHorizontalAlignment(SwingConstants.CENTER);
		label_salasana_noedit.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		label_salasana_noedit.setBounds(223, 147, 127, 20);
		contentPane.add(label_salasana_noedit);
		
		text_salasana = new JPasswordField();
		text_salasana.setHorizontalAlignment(SwingConstants.CENTER);
		text_salasana.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					
					//Oikean salasanan kryptaus
					
					String oikea_kayttajatunnus = lueTunnus(filename);
					String oikea_salasana = crypt(lueSalasana(filename));
					
					//System.out.println(oikea_salasana);
					
					String salasana_crypted="";
					
					try {
						salasana_crypted = crypt(text_salasana.getText());
					} catch (Exception e) {
					}
					
					if(textField_kayttaja.getText().equals(oikea_kayttajatunnus) && oikea_salasana.equals(salasana_crypted)) {
						KirjautumisIkkuna.this.setVisible(false);
						KirjautumisIkkuna.this.yllapitoIkkuna.setVisible(true);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "V‰‰r‰ k‰ytt‰j‰tunnus tai salasana!");
						textField_kayttaja.setText("");
						text_salasana.setText("");
					}
				}
				
				
			}
		});
		text_salasana.setBounds(196, 183, 182, 29);
		contentPane.add(text_salasana);
		
		nappi_kirjaudu = new JButton("Kirjaudu");
		nappi_kirjaudu.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_kirjaudu.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				//Oikean salasanan kryptaus
				
			String oikea_kayttajatunnus = lueTunnus(filename);
			String oikea_salasana = crypt(lueSalasana(filename));
			
			//System.out.println(oikea_salasana);
			
			String salasana_crypted="";
			
			try {
				salasana_crypted = crypt(text_salasana.getText());
			} catch (Exception e) {
			}
			
			if(textField_kayttaja.getText().equals(oikea_kayttajatunnus) && oikea_salasana.equals(salasana_crypted)) {
				KirjautumisIkkuna.this.setVisible(false);
				KirjautumisIkkuna.this.yllapitoIkkuna.setVisible(true);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "V‰‰r‰ k‰ytt‰j‰tunnus tai salasana!");
				textField_kayttaja.setText("");
				text_salasana.setText("");
			}
			
			
			}
		});
		nappi_kirjaudu.setBounds(229, 235, 115, 29);
		contentPane.add(nappi_kirjaudu);
		
		nappi_palaa = new JButton("Palaa valikkoon");
		nappi_palaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aloitusIkkuna.setVisible(true);
				KirjautumisIkkuna.this.setVisible(false);
				
			}
		});
		nappi_palaa.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		nappi_palaa.setBounds(188, 335, 197, 29);
		contentPane.add(nappi_palaa);
	}//mainin loppu
	
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

		public String kasitteleRivi(String txt) {
		
		txt = txt.replace(" ", "");
		String[] temp = txt.split("=");
		
		return temp[1];
	}
		public String lueTunnus(String filename)  {
			
			try {
				FileReader freader = new FileReader(filename);
				BufferedReader br = new BufferedReader(freader);

				tunnus = br.readLine();
				tunnus = kasitteleRivi(tunnus);
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return tunnus;
		}
		
public String lueSalasana(String filename)  {
			
			try {
				FileReader freader = new FileReader(filename);
				BufferedReader br = new BufferedReader(freader);

				salasana = br.readLine();
				salasana = kasitteleRivi(salasana);
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return salasana;
		}
}
