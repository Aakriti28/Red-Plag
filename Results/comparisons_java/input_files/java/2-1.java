import java.awt.EventQueue;

import javax.swing.JFrame;

import java.math.BigInteger;
import java.io.File;
import java.security.MessageDigest;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JButton;
//import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class GUI {
	
	private File file;

	private JFrame frame;
	private JTable table;
	private JTextField txtTextHere = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}
	
	public static String getMd5(String input) 
	{ 
		try { 

			// Static getInstance method is called with hashing MD5 
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	} 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 417, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnfileselect = new JButton("Select file");
		btnfileselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file_chooser = new JFileChooser();
				if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					file = file_chooser.getSelectedFile();
					txtTextHere.setText("file selected : "+ file.toString());
				}
			}
		});
		btnfileselect.setBounds(54, 42, 117, 25);
		frame.getContentPane().add(btnfileselect);
		
		JButton btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent arg0) {
				String[] cols = {"PLAIN-TEXT","RESULT"};
				Scanner scanner = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setColumnIdentifiers(cols);
				model.addRow(cols);
				try {
					scanner = new Scanner(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(scanner.hasNext()){
					String[] strs = scanner.nextLine().split("\t");
					String[] row = new String[2];
//					System.out.println(strs[0]);
					row[0] = strs[0];
					if(getMd5(row[0]).equals(strs[2])){
						row[1] = "verified";
					}
					else{
						row[1] = "not verified";
					}
					model.addRow(row);
				}
			}
		});
		btnProcess.setBounds(239, 42, 117, 25);
		frame.getContentPane().add(btnProcess);
		
		table = new JTable();
		table.setBounds(54, 119, 302, 222);
		frame.getContentPane().add(table);
		
		
		txtTextHere.setText("file not selected");
		txtTextHere.setBounds(54, 79, 302, 19);
		frame.getContentPane().add(txtTextHere);
		txtTextHere.setColumns(10);
	}
}
