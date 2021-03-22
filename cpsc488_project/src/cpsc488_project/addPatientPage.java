package cpsc488_project;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.io.*;


public class addPatientPage {

	JFrame frame = new JFrame();

	//TextFields
	private JTextField fnameField;
	private JTextField dateCreatedField;
	private JTextField weightField;
	private JTextField lnameField;
	private JTextField dateModifiedField;
	private JTextField heightField;
	private JTextField dobField;
	private JTextField sexField;
	private JTextField mnameField;
	private JTextField ethnicityField;
	
	
	
	DefaultTableModel model;
	
	//Resource https://stackoverflow.com/questions/15464111/run-cmd-commands-through-java
	public class Cmd {
		public void test(String DATA) throws Exception {
			
		//Navigate into Clint folder and run python script to add patient
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "cd.. && cd Client/ && python3 irods_patient_add.py " + DATA);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        	}
		}
	}
	
	
	
	addPatientPage() {

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 791, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Patient Registration Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(205, 27, 475, 54);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel fnameLabel = new JLabel("First Name:");
		fnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fnameLabel.setBounds(68, 115, 102, 22);
		frame.getContentPane().add(fnameLabel);
		
		JLabel dateCreatedLabel = new JLabel("Date Created:");
		dateCreatedLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateCreatedLabel.setBounds(68, 148, 110, 22);
		frame.getContentPane().add(dateCreatedLabel);
		
		JLabel weightLabel = new JLabel("Weight:");
		weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		weightLabel.setBounds(274, 181, 66, 22);
		frame.getContentPane().add(weightLabel);
		
		fnameField = new JTextField();
		fnameField.setBounds(163, 119, 142, 20);
		frame.getContentPane().add(fnameField);
		fnameField.setColumns(10);
		
		dateCreatedField = new JTextField();
		dateCreatedField.setColumns(10);
		dateCreatedField.setBounds(188, 150, 152, 20);
		frame.getContentPane().add(dateCreatedField);
		
		weightField = new JTextField();
		weightField.setColumns(10);
		weightField.setBounds(338, 183, 97, 20);
		frame.getContentPane().add(weightField);
		
		JLabel lnameLabel = new JLabel("Last Name:");
		lnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lnameLabel.setBounds(338, 115, 93, 22);
		frame.getContentPane().add(lnameLabel);
		
		JLabel dateModfiedLabel = new JLabel("Date Modified:");
		dateModfiedLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateModfiedLabel.setBounds(380, 148, 130, 22);
		frame.getContentPane().add(dateModfiedLabel);
		
		JLabel heightLabel = new JLabel("Height:");
		heightLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		heightLabel.setBounds(68, 181, 77, 22);
		frame.getContentPane().add(heightLabel);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(430, 119, 152, 20);
		frame.getContentPane().add(lnameField);
		
		dateModifiedField = new JTextField();
		dateModifiedField.setColumns(10);
		dateModifiedField.setBounds(500, 150, 203, 20);
		frame.getContentPane().add(dateModifiedField);
		
		heightField = new JTextField();
		heightField.setColumns(10);
		heightField.setBounds(126, 183, 87, 20);
		frame.getContentPane().add(heightField);
		
		JLabel dobLabel = new JLabel("DOB:");
		dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dobLabel.setBounds(510, 181, 51, 22);
		frame.getContentPane().add(dobLabel);
		
		dobField = new JTextField();
		dobField.setColumns(10);
		dobField.setBounds(561, 183, 162, 20);
		frame.getContentPane().add(dobField);
		
		JLabel sexLabel = new JLabel("Sex:");
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexLabel.setBounds(68, 214, 43, 22);
		frame.getContentPane().add(sexLabel);
		
		sexField = new JTextField();
		sexField.setColumns(10);
		sexField.setBounds(106, 217, 151, 20);
		frame.getContentPane().add(sexField);
		
		JLabel ethnicityLabel = new JLabel("Ethnicity:");
		ethnicityLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ethnicityLabel.setBounds(293, 214, 77, 22);
		frame.getContentPane().add(ethnicityLabel);
		
		ethnicityField = new JTextField();
		ethnicityField.setColumns(10);
		ethnicityField.setBounds(370, 217, 120, 20);
		frame.getContentPane().add(ethnicityField);
		
		
		JLabel errorLabelBottom = new JLabel("");
		errorLabelBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		errorLabelBottom.setBounds(303, 247, 224, 29);
		frame.getContentPane().add(errorLabelBottom);
		
		JLabel errorLabellName = new JLabel("");
		errorLabellName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabellName.setBounds(586, 116, 30, 21);
		frame.getContentPane().add(errorLabellName);
		
		JLabel errorLabelfName = new JLabel("");
		errorLabelfName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelfName.setBounds(310, 116, 30, 21);
		frame.getContentPane().add(errorLabelfName);
		
		JLabel errorLabelDateC = new JLabel("");
		errorLabelDateC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelDateC.setBounds(350, 149, 30, 21);
		frame.getContentPane().add(errorLabelDateC);
		
		JLabel errorLabeldateM = new JLabel("");
		errorLabeldateM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabeldateM.setBounds(708, 148, 30, 21);
		frame.getContentPane().add(errorLabeldateM);
		
		JLabel errorLabelHeight = new JLabel("");
		errorLabelHeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelHeight.setBounds(235, 183, 30, 21);
		frame.getContentPane().add(errorLabelHeight);
		
		
		JLabel errorLabelWeight = new JLabel("");
		errorLabelWeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelWeight.setBounds(474, 182, 30, 21);
		frame.getContentPane().add(errorLabelWeight);
		
		JLabel errorLabeldob = new JLabel("");
		errorLabeldob.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabeldob.setBounds(708, 182, 30, 21);
		frame.getContentPane().add(errorLabeldob);
		
		JLabel errorLabelSex = new JLabel("");
		errorLabelSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelSex.setBounds(259, 215, 30, 21);
		frame.getContentPane().add(errorLabelSex);
		
		JLabel errorLabelEthnicity = new JLabel("");
		errorLabelEthnicity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelEthnicity.setBounds(500, 215, 30, 21);
		frame.getContentPane().add(errorLabelEthnicity);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(664, 268, 89, 23);
		frame.getContentPane().add(btnSubmit);
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(565, 268, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JLabel ftLabel = new JLabel("FT");
		ftLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ftLabel.setBounds(215, 186, 30, 14);
		frame.getContentPane().add(ftLabel);
		
		JLabel lbsLabel = new JLabel("LBS");
		lbsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbsLabel.setBounds(438, 186, 46, 14);
		frame.getContentPane().add(lbsLabel);
		
		JLabel mnameLabel = new JLabel("M Initial:");
		mnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mnameLabel.setBounds(615, 115, 77, 22);
		frame.getContentPane().add(mnameLabel);
		
		mnameField = new JTextField();
		mnameField.setColumns(10);
		mnameField.setBounds(687, 119, 36, 20);
		frame.getContentPane().add(mnameField);
		
		JLabel errorLabelmName = new JLabel("");
		errorLabelmName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelmName.setBounds(735, 118, 30, 21);
		frame.getContentPane().add(errorLabelmName);
		
		
		btnSubmit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//If user didn't fill out all fields
				if(fnameField.getText().trim().isEmpty()) {
					errorLabelBottom.setText("Some Fields are still Missing");
					//* is placed near corresponding text field
					errorLabelfName.setText("*");
				}
				if(lnameField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabellName.setText("*");
				}
				if(mnameField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelmName.setText("*");
				}
				if(dateCreatedField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelDateC.setText("*");
				}
				if(dateModifiedField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabeldateM.setText("*");
				}
				if(heightField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelHeight.setText("*");
				}
				if(weightField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelWeight.setText("*");
				}
				if(dobField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabeldob.setText("*");
				}
				if(sexField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelSex.setText("*");
				}
				if(ethnicityField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelEthnicity.setText("*");
				
				}
				else {
					//All Fields are filled in
					
					//Parse Data into Json format
					String DATA = "\""+ "{\\\"\"first_name\\\"" + ":" + "\\\"" + fnameField.getText() + "\\\"" + ","
							+ "\\\"last_name\\\"" + ":" + "\\\"" + lnameField.getText() + "\\\"" + ","
							+"\\\"date_created\\\"" + ":" + "\\\"" + dateCreatedField.getText() + "\\\"" + ","
							+"\\\"date_modified\\\"" + ":" + "\\\"" + dateModifiedField.getText() + "\\\"" + ","
							+"\\\"height\\\"" + ":" + "\\\"" + heightField.getText() + "\\\"" + ","
							+"\\\"weight\\\"" + ":" + "\\\"" + weightField.getText() + "\\\"" + ","
							+"\\\"dob\\\"" + ":" + "\\\"" + dobField.getText() + "\\\"" + ","
							+"\\\"sex\\\"" + ":" + "\\\"" + sexField.getText() + "\\\"" + ","
							+"\\\"middle_name\\\"" + ":" + "\\\"" + mnameField.getText() + "\\\"" + ","
							+"\\\"ethnicity\\\"" + ":" + "\\\"" + ethnicityField.getText() + "\\\"" +
							"}"+ "\"";
							
					
					//Run Script through CMD
					Cmd cmd = new Cmd();
					try {
						cmd.test(DATA);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//JOptionPane.showMessageDialog(null, patientJson);
					JOptionPane.showMessageDialog(null, "Patient Added");
				}
			}
		});
		
		
		btnReset.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//Clear out all text fields when user clicks reset
				
				fnameField.setText("");
				lnameField.setText("");
				mnameField.setText("");
				dateCreatedField.setText("");
				dateModifiedField.setText("");
				heightField.setText("");
				weightField.setText("");
				dobField.setText("");
				sexField.setText("");
				ethnicityField.setText("");
				errorLabelBottom.setText("");
				errorLabelfName.setText("");
				errorLabellName.setText("");
				errorLabelDateC.setText("");
				errorLabeldateM.setText("");
				errorLabelHeight.setText("");
				errorLabelWeight.setText("");
				errorLabeldob.setText("");
				errorLabelSex.setText("");
				errorLabelmName.setText("");
				errorLabelEthnicity.setText("");
			}
		});
	}
}
