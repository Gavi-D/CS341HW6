package dhariwal;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * Represents the Password Validation App
 * 
 * @author Gavi Dhariwal
 * @version 1.0
 */

public class hoopla {
	
	/**
	 * Launches the application.
	 * 
	 * @param frame				 Framework for the application
	 * @param textFieldPW 	 	 Text field to enter the password from the user
	 * @param textAreaResult 	 Text area to display the validation result
	 * @param btnValidate 		 Button to initiate validation
	 * 
	 */

	private JFrame frame;
	private JTextField textFieldPW;
	private JTextArea textAreaResult;
	private JButton btnValidate;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hoopla window = new hoopla();
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
	public hoopla() {
		initialize();
		validate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter a Valid Password*");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(147, 20, 147, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*What is a valid password?");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(6, 240, 135, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A password between the lengths of 8 and 12 with no spaces");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(6, 256, 298, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textFieldPW = new JTextField();
		textFieldPW.setBounds(118, 67, 212, 26);
		frame.getContentPane().add(textFieldPW);
		textFieldPW.setColumns(10);
		
		textAreaResult = new JTextArea();
		textAreaResult.setBounds(32, 139, 388, 86);
		frame.getContentPane().add(textAreaResult);
		
		btnValidate = new JButton("Validate?");
		btnValidate.setBounds(166, 98, 117, 29);
		frame.getContentPane().add(btnValidate);
	}
	
	private void validate () //original code from Trish Corenez from Assignment 1. I am bad at ActionListener stuff
	{
		btnValidate.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				valid(); //actual method for validating password
			}
		});
	}
	
	/**
	 * The Valid Method
	 * @param pw		String element to get the password that the user has inputted
	 * @param blocks	ArrayList to hold all the blocks in the password
	 * @param block		Integer variable to gather all the blocks in the password
	 * @param temp		Temporary Integer to transfer a block to the ArrayList when found one
	 * @param max		Integer variable that holds the largest block value
	 * @param result	String that holds the result to put in the textAreaResult
	 */
	private void valid ()
	{
		String pw = new String();
		pw = textFieldPW.getText();
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		int block = 0;
		int temp = 0;
		int max = 0;
		String result = "";
		
		for (int x = 0; x < pw.length() - 1; x++) //going through all characters of the inputted password
		{
			if (block != 0 && pw.charAt(x) != pw.charAt(x+1)) //checking for block formED; if yes, set the temp variable as block and append the that number into the ArrayList blocks 
			{
				temp = block;
				blocks.add(temp + 1);
				temp = 0;
				
			}
			
			if (pw.charAt(x) == pw.charAt(x+1)) //checking for block formATION
			{
				block++;
			}
			else
			{
				block = 0;
			}
			
			if (block != 0 && x == pw.length() - 2) //checking for block formED at the end of the password; if yes, set the temp variable as block and append the that number into the ArrayList blocks 
			{
				temp = block;
				blocks.add(temp + 1);
				temp = 0;
			}
			
		}
		
		if (pw.contains(" ")) //checking for spaces
		{
			result = "Error 340: Yo! No spaces :(";
		}
		else if (pw.length() < 8 || pw.length() > 12) //checking for valid password input
		{
			result = "Error 341: Invalid Password Inputted. \nSet a password of length between 8 and 12";
		}
		else
		{
			max = (int) Collections.max(blocks); //taking the largest block from the password
			if (max > 2) //checking if max block is greater than 2; if yes, suggest for a better password
			{
				result = "The largest block in your password is " + max + ". \nThis password can be made stronger by \nreducing the block by " + (max - 2) + " character(s). \n\n Wear a mask and sanitize your hands!";

			}
			else
			{
				result = "The largest block in your password is " + max + ".\nGood job!\nWear a mask and sanitize your hands!";
			}
		}
		
		textAreaResult.setText(result); //setting result string as display text for textAreaResult
		
	}
}
