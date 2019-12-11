import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

public class ApplicationWindow extends JFrame {
	private JTextArea text;
	public void fillTextArea() {
		try {
			// SEE Min 54
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setupMenu() {
		JMenuBar mbar = new JMenuBar();
		
		JMenu mnuFile = new JMenu("File");
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Exit application
				System.exit(0);
			}
		});
		mnuFile.add(miExit);
		
		JMenu mnuHelp = new JMenu("Help");
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display Help window text
				JOptionPane.showMessageDialog(null, "Created by Jeff Krejcik");
			}
		});
		mnuHelp.add(miAbout);
		
		mbar.add(mnuFile);
		mbar.add(mnuHelp);
		setJMenuBar(mbar);		
	}
	
	public ApplicationWindow() {
		
		
		setTitle("Krejcik Web Scraper Application");
		setBounds(100,50,400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel panSouth = new JPanel();
		JPanel panNorth = new JPanel();
		panNorth.setLayout(new FlowLayout());
		panSouth.setLayout(new FlowLayout());
		
		JLabel lblUrl = new JLabel("Enter URL:");
		JTextField jtfUrl = new JTextField("", 20);
		
		text = new JTextArea();
		Font f = new Font("Monospaced", Font.BOLD, 24);
		text.setFont(f);
		text.setText("Enter a URL");
		text.setEditable(false);
		c.add(text, BorderLayout.CENTER);		
		
		
		JButton btnFetch = new JButton("Fetch");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Action
				JOptionPane.showMessageDialog(null, "test"); // REMOVE!
			}
		});
		JButton btnSaveToText = new JButton("Save to Text");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					// TODO CREATE FILE AT THIS LOCATION
					// jfc.getSelectedFile().getPath();
				}
			}
		});
		JButton btnSaveToJSON = new JButton("Save to JSON");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				JFileChooser jfc = new JFileChooser();
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					// TODO CREATE FILE AT THIS LOCATION
					// jfc.getSelectedFile().getPath();
				}
			}
		});

		c.add(panNorth, BorderLayout.NORTH);
		panNorth.add(lblUrl);
		panNorth.add(jtfUrl);
		panNorth.add(btnFetch);
		
		
		panSouth.add(btnSaveToText);
		panSouth.add(btnSaveToJSON);
		c.add(panSouth, BorderLayout.SOUTH);
		
		
		setupMenu();
	}
	
	public static void main(String[] args) {
		ApplicationWindow frm = new ApplicationWindow();
		frm.setVisible(true);
	}
}
