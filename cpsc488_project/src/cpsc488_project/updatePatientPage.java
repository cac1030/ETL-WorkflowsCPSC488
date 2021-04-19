package cpsc488_project;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cpsc488_project.patientDirectory.CmdPatients;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class updatePatientPage {

	JFrame frame = new JFrame();

	//TextFields
	private JTextField fnameField;
	private JLabel dateCreatedField;
	private JTextField weightField;
	private JTextField lnameField;
	private JLabel dateModifiedField;
	private JTextField dobField;
	private JTextField mnameField;
	private JTextField ethnicityField;
	private JComboBox<String> comboBoxHeight;
	
	
	
	DefaultTableModel model;
	
	//Run Edit Script
	//Resource https://stackoverflow.com/questions/15464111/run-cmd-commands-through-java
	public class Cmd {
		public void test(String DATA) throws Exception {
			
		//Navigate into Client folder and run Python3 script to Edit patient
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "cd.. && cd Client/ && python3 irods_patient.py " + "-e" + DATA);
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
	
	//Update Patient Directory
	public class CmdPatients {
		public void patientName() throws Exception {
			
		//Navigate into Client folder and run Python3 script to fetch patients
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "cd.. && cd Client/ && python3 irods_patient.py " + "-f");
      
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
	
	
	updatePatientPage() {

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 791, 343);
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox<String> comboBoxSex = new JComboBox<String>();
		comboBoxSex.setForeground(Color.BLACK);
		comboBoxSex.setBackground(Color.WHITE);
		comboBoxSex.setBounds(108, 216, 97, 23);
		comboBoxSex.addItem("");
		comboBoxSex.addItem("MALE");
		comboBoxSex.addItem("FEMALE");
		comboBoxSex.addItem("OTHER");
		frame.getContentPane().add(comboBoxSex);
		
		JLabel nameLabel = new JLabel("New label");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		nameLabel.setBounds(380, 35, 274, 41);
		nameLabel.setText(patientDirectory.nameSelected);
		frame.getContentPane().add(nameLabel);
		
		JLabel lblNewLabel = new JLabel("Updating :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(205, 27, 175, 54);
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
		
		dateCreatedField = new JLabel();
		dateCreatedField.setBounds(188, 150, 152, 20);
		frame.getContentPane().add(dateCreatedField);
		
		weightField = new JTextField();
		weightField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isDigit(c) || Character.isISOControl(c)) {
					weightField.setEditable(true);
				}
				else {
					weightField.setEditable(false);
				}
			
			}
		});
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
		
		dateModifiedField = new JLabel();
		dateModifiedField.setBounds(500, 150, 203, 20);
		frame.getContentPane().add(dateModifiedField);
		
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
		
		JLabel ethnicityLabel = new JLabel("Ethnicity:");
		ethnicityLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ethnicityLabel.setBounds(293, 214, 77, 22);
		frame.getContentPane().add(ethnicityLabel);
		
		ethnicityField = new JTextField();
		ethnicityField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) || Character.isISOControl(c)) {
					ethnicityField.setEditable(true);
				}
				else {
					ethnicityField.setEditable(false);
				}
			
			}
		});
		ethnicityField.setColumns(10);
		ethnicityField.setBounds(370, 217, 120, 20);
		frame.getContentPane().add(ethnicityField);
		
		
		comboBoxHeight = new JComboBox<String>();
		comboBoxHeight.setForeground(Color.BLACK);
		comboBoxHeight.setBackground(Color.WHITE);
		comboBoxHeight.setBounds(132, 183, 77, 23);
		comboBoxHeight.addItem("");
		comboBoxHeight.addItem("5'11");
		comboBoxHeight.addItem("5'11");
		comboBoxHeight.addItem("5'10");
		comboBoxHeight.addItem("5'9");
		comboBoxHeight.addItem("5'8");
		comboBoxHeight.addItem("5'7");
		comboBoxHeight.addItem("5'6");
		comboBoxHeight.addItem("5'5");
		comboBoxHeight.addItem("5'4");
		comboBoxHeight.addItem("5'3");
		comboBoxHeight.addItem("5'2");
		comboBoxHeight.addItem("5'1");
		comboBoxHeight.addItem("5");
		comboBoxHeight.addItem("4'11");
		comboBoxHeight.addItem("4'10");
		comboBoxHeight.addItem("4'9");
		comboBoxHeight.addItem("4'8");
		comboBoxHeight.addItem("4'7");
		comboBoxHeight.addItem("4'6");
		comboBoxHeight.addItem("4'5");
		comboBoxHeight.addItem("4'4");
		comboBoxHeight.addItem("4'3");
		comboBoxHeight.addItem("4'2");
		comboBoxHeight.addItem("4'1");
		comboBoxHeight.addItem("4");
		frame.getContentPane().add(comboBoxHeight);
		
		
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
		errorLabeldob.setBounds(733, 182, 30, 21);
		frame.getContentPane().add(errorLabeldob);
		
		JLabel errorLabelSex = new JLabel("");
		errorLabelSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLabelSex.setBounds(215, 216, 30, 21);
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
		
		JLabel backgroundPic3 = new JLabel("");
		backgroundPic3.setIcon(new ImageIcon(addPatientPage.class.getResource("/cpsc488_project/bluebackground.jpg")));
		backgroundPic3.setBounds(0, 0, 785, 314);
		frame.getContentPane().add(backgroundPic3);
		
		
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dateModifiedField.setText(df.format(date));
		
		fnameField.setText(patientDirectory.firstName.toUpperCase());
		lnameField.setText(patientDirectory.lastName.toUpperCase());
		mnameField.setText(patientDirectory.middleName.toUpperCase());
		//IMPLEMENT HEIGHT FIELD SET COMBOBOX
		
		
		comboBoxHeight.setSelectedItem(patientDirectory.newHieght);
		
		comboBoxSex.setSelectedItem(patientDirectory.sexSelected.toUpperCase());
				
		
		weightField.setText(patientDirectory.weightSelected);
		ethnicityField.setText(patientDirectory.ethnicitySelected.toUpperCase());
		dobField.setText(patientDirectory.dobSelected);
		
		dateCreatedField.setText(patientDirectory.dateCreatedSelected);
		
		
		
		
		btnSubmit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//If user didn't fill out all fields
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
				
				if(comboBoxHeight.getSelectedIndex() == 0){
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
				if(comboBoxSex.getSelectedIndex() == 0){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelSex.setText("*");
				}
				if(ethnicityField.getText().trim().isEmpty()){
					errorLabelBottom.setText("Some Fields are still Missing");
					errorLabelEthnicity.setText("*");
				
				}
				else {
					//All Fields are filled in
					
					int secs = (int) ((new Date().getTime())/1000);
					
					String selectedInch= (String) comboBoxHeight.getSelectedItem();
					
					char feet = selectedInch.charAt(0);
					char inches = selectedInch.charAt(2);
					int height = Character.getNumericValue(feet);
					int b = Character.getNumericValue(inches);
					height= height*12 + b;
					
					//Parse Data into JSON format
					String DATA = "\""+ "{\\\"\"first_name\\\"" + ":" + "\\\"" + fnameField.getText() + "\\\"" + ","
							+ "\\\"last_name\\\"" + ":" + "\\\"" + lnameField.getText() + "\\\"" + ","
							+"\\\"date_created\\\"" + ":" + "\\\"" + secs + "\\\"" + "," //Needs Fixed
							+"\\\"date_modified\\\"" + ":" + "\\\"" + secs + "\\\"" + ","
							+"\\\"height\\\"" + ":" + "\\\"" + height + "\\\"" + ","
							+"\\\"weight\\\"" + ":" + "\\\"" + weightField.getText() + "\\\"" + ","
							+"\\\"dob\\\"" + ":" + "\\\"" + dobField.getText() + "\\\"" + ","
							+"\\\"sex\\\"" + ":" + "\\\"" + comboBoxSex.getSelectedItem() + "\\\"" + ","
							+"\\\"middle_name\\\"" + ":" + "\\\"" + mnameField.getText() + "\\\"" + ","
							+"\\\"ethnicity\\\"" + ":" + "\\\"" + ethnicityField.getText() + "\\\"" +
							"}"+ "\"";
							
					
					//Run Update Script
					Cmd cmd = new Cmd();
					try {
						cmd.test(DATA);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//JOptionPane.showMessageDialog(null, patientJson);
					JOptionPane.showMessageDialog(null, "Patient Updated");
					
					//Rerun patient Directory script to update
					CmdPatients cmd2 = new CmdPatients();
					try {
						//Run Command Prompt
						cmd2.patientName();
						patientDirectory.DLM.clear();
						patientDirectory.bindData();
						
						
						 //System.out.println();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		
		btnReset.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//Clear out all text fields when user clicks reset
				
				fnameField.setText("");
				lnameField.setText("");
				mnameField.setText("");
				comboBoxHeight.setSelectedItem("");
				weightField.setText("");
				dobField.setText("");
				comboBoxSex.setSelectedItem("");
				ethnicityField.setText("");
				errorLabelBottom.setText("");
				errorLabelfName.setText("");
				errorLabellName.setText("");
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
