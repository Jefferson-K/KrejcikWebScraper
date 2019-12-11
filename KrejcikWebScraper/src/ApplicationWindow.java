import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

/**
 * Main application window for the Lewis Course Catalog Screen Scraper
 * @author Jeff
 *
 */
public class ApplicationWindow extends JFrame {
	private JTextArea text;
	private JTextField jtfUrl = new JTextField("", 30);
	private ArrayList<Course> courses;
	
	/**
	 * Menu bars
	 */
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
		
		
		JMenu mnuPresets = new JMenu("Presets");
		JMenuItem miCPSC = new JMenuItem("Computer Science");
		miCPSC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfUrl.setText("http://lewisu.smartcatalogiq.com/Undergrad-2019-2020/Undergraduate-Catalog/Course-Descriptions/70-Computer-Science");
				text.setText("");
			}
		});
		mnuPresets.add(miCPSC);
		
		JMenuItem miDATA = new JMenuItem("Data Science");
		miDATA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfUrl.setText("http://lewisu.smartcatalogiq.com/Undergrad-2019-2020/Undergraduate-Catalog/Course-Descriptions/DATA-Data-Science");
				text.setText("");
			}
		});
		mnuPresets.add(miDATA);
		
		JMenuItem miFINA = new JMenuItem("Finance");
		miFINA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfUrl.setText("http://lewisu.smartcatalogiq.com/Undergrad-2019-2020/Undergraduate-Catalog/Course-Descriptions/62-Finance");
				text.setText("");
			}
		});
		mnuPresets.add(miFINA);
		
		JMenuItem miTHEO = new JMenuItem("Theology");
		miTHEO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfUrl.setText("http://lewisu.smartcatalogiq.com/Undergrad-2019-2020/Undergraduate-Catalog/Course-Descriptions/19-Theology");
				text.setText("");
			}
		});
		mnuPresets.add(miTHEO);
		
		
		JMenu mnuHelp = new JMenu("Help");
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display Help window text
				JOptionPane.showMessageDialog(null, "Created by Jeff Krejcik");
			}
		});
		mnuHelp.add(miAbout);
			
		mnuFile.add(mnuPresets);
		mnuFile.add(miExit);
		mbar.add(mnuFile);
		mbar.add(mnuHelp);
		setJMenuBar(mbar);		
	}
	
	/**
	 * Main application window configuration and events
	 */
	public ApplicationWindow() {
		setTitle("Krejcik - Lewis Course Catalog Scraper");
		setBounds(100,50,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel panSouth = new JPanel();
		JPanel panNorth = new JPanel();
		panNorth.setLayout(new FlowLayout());
		panSouth.setLayout(new FlowLayout());
		
		JLabel lblUrl = new JLabel("Enter URL:");
		//JTextField jtfUrl = new JTextField("", 30);
		
		text = new JTextArea();
		Font f = new Font("Monospaced", Font.BOLD, 12);
		text.setFont(f);
		text.setEditable(false);
		text.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(text);
		c.add(scroll, BorderLayout.CENTER);		
		
		/**
		 * Button to retrieve HTML data from a URL, create an array list, and display the contents on the screen.
		 */
		JButton btnFetch = new JButton("Fetch");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					courses = HtmlParser.parseCourseData(jtfUrl.getText());
					text.setText(CourseFileWriter.writeCoursesToScreen(courses));
				} catch (Exception e2) {
				}
								
			}
		});
		
		/**
		 * Button to save the current array list to a text file.
		 */
		JButton btnSaveToText = new JButton("Save to Text");
		btnSaveToText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					if (CourseFileWriter.writeCoursesToTextFile(courses, jfc.getSelectedFile().getPath()+".txt")) {
						JOptionPane.showMessageDialog(null, "File saved");
					} else {
						JOptionPane.showMessageDialog(null, "Error saving file. Please try again.");
					}
				}
			}
		});
		
		/**
		 * Button to save the current array list to a JSON file.
		 */
		JButton btnSaveToJSON = new JButton("Save to JSON");
		btnSaveToJSON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					if (CourseFileWriter.writeCoursesToJSON(courses, jfc.getSelectedFile().getPath()+".json")) {
						JOptionPane.showMessageDialog(null, "File saved");
					} else {
						JOptionPane.showMessageDialog(null, "Error saving file. Please try again.");
					}
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
	
	/**
	 * Main function to initialize application window.
	 * 
	 */
	public static void main(String[] args) {
		ApplicationWindow frm = new ApplicationWindow();
		frm.setVisible(true);
	}
}
