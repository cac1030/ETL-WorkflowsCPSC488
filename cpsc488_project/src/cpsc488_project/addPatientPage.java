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
	private JTextField nameField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField birthField;
	private JTextField stateField;
	private JTextField zipField;
	private JTextField countryField;
	private JTextField phoneField;
	private JTextField genderField;
	private JTextField ethnicityField;
	private JTextField emailField;
	
	
	
	DefaultTableModel model;
	
	
	public class CmdTest {
		public void test() throws Exception {
			
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "dir");
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
		frame.setBounds(100, 100, 835, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Patient Registration Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(205, 27, 475, 54);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel nameLabel = new JLabel("Patient Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setBounds(68, 115, 123, 22);
		frame.getContentPane().add(nameLabel);
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addressLabel.setBounds(68, 148, 77, 22);
		frame.getContentPane().add(addressLabel);
		
		JLabel cityLabel = new JLabel("City:");
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cityLabel.setBounds(68, 181, 51, 22);
		frame.getContentPane().add(cityLabel);
		
		nameField = new JTextField();
		nameField.setBounds(188, 119, 152, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(144, 152, 196, 20);
		frame.getContentPane().add(addressField);
		
		cityField = new JTextField();
		cityField.setColumns(10);
		cityField.setBounds(112, 185, 143, 20);
		frame.getContentPane().add(cityField);
		
		JLabel birthLabel = new JLabel("Date of Birth:");
		birthLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		birthLabel.setBounds(417, 115, 123, 22);
		frame.getContentPane().add(birthLabel);
		
		JLabel stateLabel = new JLabel("State:");
		stateLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		stateLabel.setBounds(417, 148, 62, 22);
		frame.getContentPane().add(stateLabel);
		
		JLabel zipLabel = new JLabel("Zip:");
		zipLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		zipLabel.setBounds(599, 148, 30, 22);
		frame.getContentPane().add(zipLabel);
		
		birthField = new JTextField();
		birthField.setColumns(10);
		birthField.setBounds(528, 117, 170, 20);
		frame.getContentPane().add(birthField);
		
		stateField = new JTextField();
		stateField.setColumns(10);
		stateField.setBounds(466, 152, 111, 20);
		frame.getContentPane().add(stateField);
		
		zipField = new JTextField();
		zipField.setColumns(10);
		zipField.setBounds(631, 152, 87, 20);
		frame.getContentPane().add(zipField);
		
		JLabel countryLabel = new JLabel("Country:");
		countryLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		countryLabel.setBounds(284, 181, 79, 22);
		frame.getContentPane().add(countryLabel);
		
		countryField = new JTextField();
		countryField.setColumns(10);
		countryField.setBounds(360, 185, 119, 20);
		frame.getContentPane().add(countryField);
		
		JLabel phoneLabel = new JLabel("Phone:");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		phoneLabel.setBounds(509, 181, 62, 22);
		frame.getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(567, 185, 151, 20);
		frame.getContentPane().add(phoneField);
		
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		genderLabel.setBounds(68, 221, 71, 22);
		frame.getContentPane().add(genderLabel);
		
		genderField = new JTextField();
		genderField.setColumns(10);
		genderField.setBounds(135, 225, 97, 20);
		frame.getContentPane().add(genderField);
		
		JLabel ethnicityLabel = new JLabel("Ethnicity:");
		ethnicityLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ethnicityLabel.setBounds(257, 221, 83, 22);
		frame.getContentPane().add(ethnicityLabel);
		
		ethnicityField = new JTextField();
		ethnicityField.setColumns(10);
		ethnicityField.setBounds(339, 225, 119, 20);
		frame.getContentPane().add(ethnicityField);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailLabel.setBounds(485, 223, 51, 22);
		frame.getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(537, 225, 181, 20);
		frame.getContentPane().add(emailField);
		
		
		JLabel errorLabelBottom = new JLabel("");
		errorLabelBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		errorLabelBottom.setBounds(319, 261, 224, 29);
		frame.getContentPane().add(errorLabelBottom);
		
		JLabel errorLabelDate = new JLabel("");
		errorLabelDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelDate.setBounds(697, 115, 30, 21);
		frame.getContentPane().add(errorLabelDate);
		
		JLabel errorLabelPatient = new JLabel("");
		errorLabelPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelPatient.setBounds(350, 118, 30, 21);
		frame.getContentPane().add(errorLabelPatient);
		
		JLabel errorLabelAddress = new JLabel("");
		errorLabelAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelAddress.setBounds(350, 149, 30, 21);
		frame.getContentPane().add(errorLabelAddress);
		
		JLabel errorLabelState = new JLabel("");
		errorLabelState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelState.setBounds(576, 151, 30, 21);
		frame.getContentPane().add(errorLabelState);
		
		JLabel errorLabelZip = new JLabel("");
		errorLabelZip.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelZip.setBounds(717, 149, 30, 21);
		frame.getContentPane().add(errorLabelZip);
		
		
		JLabel errorLabelCity = new JLabel("");
		errorLabelCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelCity.setBounds(257, 184, 30, 21);
		frame.getContentPane().add(errorLabelCity);
		
		JLabel errorLabelCountry = new JLabel("");
		errorLabelCountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelCountry.setBounds(480, 182, 30, 21);
		frame.getContentPane().add(errorLabelCountry);
		
		JLabel errorLabelPhone = new JLabel("");
		errorLabelPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelPhone.setBounds(717, 182, 30, 21);
		frame.getContentPane().add(errorLabelPhone);
		
		JLabel errorLabelGender = new JLabel("");
		errorLabelGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelGender.setBounds(236, 222, 30, 21);
		frame.getContentPane().add(errorLabelGender);
		
		JLabel errorLabelEthnicity = new JLabel("");
		errorLabelEthnicity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelEthnicity.setBounds(463, 222, 30, 21);
		frame.getContentPane().add(errorLabelEthnicity);
		
		JLabel errorLabelEmail = new JLabel("");
		errorLabelEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelEmail.setBounds(717, 222, 30, 21);
		frame.getContentPane().add(errorLabelEmail);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//If user didn't fill out all fields
				if(nameField.getText().trim().isEmpty()) {
					errorLabelBottom.setText("Some Fields are still Missing");
					//* is placed near corresponding text field
					errorLabelPatient.setText("*");
				}
				if(birthField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelDate.setText("*");
				}
				if(addressField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelAddress.setText("*");
				}
				if(stateField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelState.setText("*");
				}
				if(zipField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelZip.setText("*");
				}
				if(cityField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelCity.setText("*");
				}
				if(countryField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelCountry.setText("*");
				}
				if(phoneField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelPhone.setText("*");
				}
				if(genderField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelGender.setText("*");
				}
				if(ethnicityField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelEthnicity.setText("*");
				}
				if(emailField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelEmail.setText("*");
				}
				else {
					//All Fields are filled in
					//Parse Data into Json format
					/*
					String patientJson = "{\n\"Patient Name\"" + ":" + "\"" + nameField.getText() + "\"" + "," + "\n"
					+ "\"Birth\"" + ":" + "\"" + birthField.getText() + "\"" + "," + "\n"
					+"\"Address\"" + ":" + "\"" + addressField.getText() + "\"" + "," + "\n"
					+"\"State\"" + ":" + "\"" + stateField.getText() + "\"" + "," + "\n"
					+"\"Zip\"" + ":" + "\"" + zipField.getText() + "\"" + "," + "\n"
					+"\"City\"" + ":" + "\"" + cityField.getText() + "\"" + "," + "\n"
					+"\"Country\"" + ":" + "\"" + countryField.getText() + "\"" + "," + "\n"
					+"\"Phone\"" + ":" + "\"" + phoneField.getText() + "\"" + "," + "\n"
					+"\"Gender\"" + ":" + "\"" + genderField.getText() + "\"" + "," + "\n"
					+"\"Ethnicity\"" + ":" + "\"" + ethnicityField.getText() + "\"" + "," + "\n"
					+"\"Email\"" + ":" + "\"" + emailField.getText() + "\"" + "\n"
					+ "}";
					*/
					
					String DATA = "{\"first_name\"" + ":" + "\"" + nameField.getText() + "\"" + ","
							+ "\"last_name\"" + ":" + "\"" + birthField.getText() + "\"" + ","
							+"\"date_created\"" + ":" + "\"" + addressField.getText() + "\"" + ","
							+"\"date_modified\"" + ":" + "\"" + stateField.getText() + "\"" + ","
							+"\"height\"" + ":" + "\"" + zipField.getText() + "\"" + ","
							+"\"weight\"" + ":" + "\"" + cityField.getText() + "\"" + ","
							+"\"dob\"" + ":" + "\"" + countryField.getText() + "\"" + ","
							+"\"sex\"" + ":" + "\"" + phoneField.getText() + "\"" + ","
							+"\"ethnicity\"" + ":" + "\"" + genderField.getText() + "\"" + ","
							+ "}";
							
					
				    
					//Run Script
					CmdTest cmd = new CmdTest();
					try {
						cmd.test();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//JOptionPane.showMessageDialog(null, patientJson);
					JOptionPane.showMessageDialog(null, "Patient Added");
				}
			}
		});
		
		btnSubmit.setBounds(706, 267, 89, 23);
		frame.getContentPane().add(btnSubmit);
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(609, 267, 89, 23);
		frame.getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//Clear out all text fields when user clicks reset
				
				nameField.setText("");
				birthField.setText("");
				addressField.setText("");
				stateField.setText("");
				zipField.setText("");
				cityField.setText("");
				countryField.setText("");
				phoneField.setText("");
				genderField.setText("");
				ethnicityField.setText("");
				emailField.setText("");
				errorLabelBottom.setText("");
				errorLabelPatient.setText("");
				errorLabelDate.setText("");
				errorLabelAddress.setText("");
				errorLabelState.setText("");
				errorLabelZip.setText("");
				errorLabelCity.setText("");
				errorLabelCountry.setText("");
				errorLabelPhone.setText("");
				errorLabelGender.setText("");
				errorLabelEthnicity.setText("");
				errorLabelEmail.setText("");
				
			}
		});
	}
}
