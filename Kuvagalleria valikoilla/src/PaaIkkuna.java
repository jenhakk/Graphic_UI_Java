import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaaIkkuna extends JFrame {

	private JPanel contentPane;
	private JLabel Pic;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;

	
	String[] list = { "Img/jyka1.jpg", //0
			"Img/jyka2.jpg", // 1
			"Img/jyka3.jpg", // 2
			"Img/jyka4.jpg",
	};
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
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
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaaIkkuna() {
		setTitle("Kuvagalleria Jyk\u00E4st\u00E4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 512);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Jyk\u00E41 ja Jyk\u00E42");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Jyk\u00E41");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ImageIcon icon = createImageIcon("Img/jyka1.jpg", "kuvassa angry bird");
				ImageIcon icon = createImageIcon(list[0], "Jykä pelastusliiveissä");
				Pic.setIcon(icon);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Jyk\u00E42");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon(list[1], "Jykä talvella");
				Pic.setIcon(icon);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mnNewMenu_1 = new JMenu("Jyk\u00E43 ja Jyk\u00E44");
		menuBar.add(mnNewMenu_1);
		
		mntmNewMenuItem_2 = new JMenuItem("Jyk\u00E43");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon(list[2], "Jykä mökillä");
				Pic.setIcon(icon);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("Jyk\u00E44");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon(list[3], "Jykä pellolla");
				Pic.setIcon(icon);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
		
		mnNewMenu_3 = new JMenu("Valitse alivalikosta");
		mnNewMenu_2.add(mnNewMenu_3);
		
		mntmNewMenuItem_4 = new JMenuItem("Jyk\u00E4 pelastusliiveiss\u00E4");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon(list[0], "Jykä pelastusliiveissä");
				Pic.setIcon(icon);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_5 = new JMenuItem("Jyk\u00E4 talvella");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = createImageIcon(list[1], "Jykä talvella");
				Pic.setIcon(icon);
			}
			
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Pic = new JLabel("");
		Pic.setBounds(41, 31, 527, 363);
		contentPane.add(Pic);
	} //mainin loppu

	 /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL, description);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(Pic.getWidth(), Pic.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon newImc = new ImageIcon(newImg);
           
            return newImc;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }   
}
