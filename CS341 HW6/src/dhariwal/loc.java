package dhariwal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class loc {

	private JFrame frame;
	private JFileChooser fileChooser;
	private BufferedReader br;
	private File file;
	int returnVal;
	String currentLine;
	private JTextArea textResult;
	private JButton fileBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loc window = new loc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loc() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		fileBtn = new JButton("I call upon the File Gods");
		fileBtn.setBounds(139, 35, 179, 29);
		frame.getContentPane().add(fileBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 90, 214, 97);
		frame.getContentPane().add(scrollPane);
		
		textResult = new JTextArea();
		scrollPane.setViewportView(textResult);
	}
	
	private void createEvents()
	{ //method when Add Button is clicked
		fileBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				filereading(); //actual method for adding the Big Numbers
			}
		});
	}
	private void filereading() {
		int counter = 0; int trash = 0;
		int forloops = 0; int fortrash = 0;
		int whileloops = 0; int whiletrash = 0;
		int ifs = 0; int iftrash = 0;
		
		
		String line = "";
		fileChooser = new JFileChooser("/users");
		returnVal = fileChooser.showOpenDialog(null);			//THANK YOU GOOGLE for the fileChooser code!
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file  = fileChooser.getSelectedFile();
			try {
				br = new BufferedReader(new FileReader(file));	//bufferedReader that reads from the selected file
				while ((currentLine = br.readLine()) != null) {
					counter++;
					
					if(currentLine.isEmpty()) {
						counter--;
					}
					
					if (currentLine.substring(0, 1) == "//" && !(currentLine.contains("\'") && (currentLine.contains("\"")))) { //counts comments (not in a string)
						trash++;
					}
					
					if (currentLine.contains("/*") && !(currentLine.contains("\'") && (currentLine.contains("\"")))) {	//counts comment block (not in a string) and if there are false 'for' 'while' 'if' words
						while ((currentLine = br.readLine()) != null && !(currentLine.endsWith("*/")) ) {
							
							if (currentLine.contains("if")) {
								iftrash++;
							}
							
							if (currentLine.contains("for")) {
								fortrash++;
							}
							
							if (currentLine.contains("while")) {
								whiletrash++;
							}
							trash++;
						}
					}
					if(currentLine.contains("for")) {	//counts for loops
						forloops++;
					}
					if (currentLine.contains("while")) {//counts while loops
						whileloops++;
					}
					if (currentLine.contains("if")) {	//counts if statements
						ifs++;
					}
				}
	
			}catch(Exception error) {
				error.printStackTrace();
			}
		}
		
		textResult.setText("This file contains " + (counter - trash) + " line(s) of code \n" + 
		"This file contains " + (ifs - iftrash) + " if statement(s) \n" +
		"This file contains " + (forloops - fortrash) + " for loop(s) \n" + 
		"This file contains " + (whileloops - whiletrash) + " while loop(s)");
	 }
}
