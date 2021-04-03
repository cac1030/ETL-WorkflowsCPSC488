package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import cpsc488_project.patientDirectory.CmdPatients;

import javax.swing.JButton;
import javax.swing.JList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class viewFilePage {

	JFrame frame = new JFrame();
	private JTextField searchFilesField;
	public JSONArray b;
	DefaultListModel<Object> DLMFiles = new DefaultListModel<Object>();
	private final JList<Object> listFiles = new JList<Object>();
	
	
	public class CmdFiles {
		public void fileNames() throws Exception {
			
		//Navigate into Client folder and run Python3 script to fetch patients
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "cd.. && cd Client/ && python3 irods_files_info.py " + "-a "+ "0 " + patientDirectory.lastName.toUpperCase() + "_" + patientDirectory.firstName.toUpperCase()); 
      
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
	
	private void bindData() throws IOException, org.json.simple.parser.ParseException {
		getNames().stream().forEach((name) -> {
			DLMFiles.addElement(name);
		});
		
	}
	
	//Search Box to filter through strings
		private void searchFilterFiles(String searchTerm) throws IOException, org.json.simple.parser.ParseException
		{
			DefaultListModel<Object> filterItems = new DefaultListModel<Object>();
			ArrayList<String> names=getNames();
			
			names.stream().forEach((name) -> {
				String filteredName=name.toString().toLowerCase();
				if (filteredName.contains(searchTerm.toLowerCase())) {
					filterItems.addElement(name);
				}
			});
			DLMFiles=filterItems;
			listFiles.setModel(DLMFiles);
			
		}
	
	
	public viewFilePage() throws org.json.simple.parser.ParseException, IOException {
		
		
		CmdFiles cmd = new CmdFiles();
		try {
			//Run Command Prompt
			cmd.fileNames();
			 //System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///////////////////////////////////////////
		this.bindData();
		///////////////////////////////////////////////////////////////
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel view_patient_title = new JLabel("Patient:");
		view_patient_title.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 30));
		view_patient_title.setBounds(117, 0, 312, 68);
		frame.getContentPane().add(view_patient_title);
		
		JLabel view_patient_label = new JLabel("");
		view_patient_label.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 30));
		view_patient_label.setBounds(117, 33, 312, 68);
		view_patient_label.setText(patientDirectory.nameSelected);
		frame.getContentPane().add(view_patient_label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 90, 429, 463);
		frame.getContentPane().add(tabbedPane);
		
		Panel panelInfo = new Panel();
		tabbedPane.addTab("Patient Info", null, panelInfo, null);
		panelInfo.setLayout(null);
		
		JLabel weightLablelV = new JLabel("Weight:");
		weightLablelV.setFont(new Font("Rockwell", Font.BOLD, 15));
		weightLablelV.setBounds(10, 135, 64, 28);
		panelInfo.add(weightLablelV);
		
		JLabel dateCreatedLabelV = new JLabel("Patient Created:");
		dateCreatedLabelV.setFont(new Font("Rockwell", Font.BOLD, 15));
		dateCreatedLabelV.setBounds(10, 22, 137, 14);
		panelInfo.add(dateCreatedLabelV);
		
		JLabel heightLabelV = new JLabel("Height:");
		heightLabelV.setFont(new Font("Rockwell", Font.BOLD, 15));
		heightLabelV.setBounds(10, 156, 64, 26);
		panelInfo.add(heightLabelV);
		
		JLabel dateModifiedLabelV = new JLabel("Patient Modfied:");
		dateModifiedLabelV.setFont(new Font("Rockwell", Font.BOLD, 15));
		dateModifiedLabelV.setBounds(10, 48, 137, 14);
		panelInfo.add(dateModifiedLabelV);
		
		JLabel dobLabelV = new JLabel("Date of Birth:");
		dobLabelV.setFont(new Font("Rockwell", Font.BOLD, 15));
		dobLabelV.setBounds(10, 73, 104, 14);
		panelInfo.add(dobLabelV);
		
		JLabel sexLabelV = new JLabel("Sex:");
		sexLabelV.setFont(new Font("Rockwell", Font.BOLD, 15));
		sexLabelV.setBounds(10, 182, 64, 14);
		panelInfo.add(sexLabelV);
		
		JLabel ethnicityLabelV = new JLabel("Ethnicity:");
		ethnicityLabelV.setFont(new Font("Rockwell", Font.BOLD, 15));
		ethnicityLabelV.setBounds(10, 115, 71, 26);
		panelInfo.add(ethnicityLabelV);
		
		JLabel sexLabelAns = new JLabel("");
		sexLabelAns.setFont(new Font("Rockwell", Font.PLAIN, 15));
		sexLabelAns.setBounds(84, 183, 95, 14);
		panelInfo.add(sexLabelAns);
		
		JLabel ethnicityLabelAns = new JLabel("");
		ethnicityLabelAns.setFont(new Font("Rockwell", Font.PLAIN, 15));
		ethnicityLabelAns.setBounds(84, 122, 95, 14);
		panelInfo.add(ethnicityLabelAns);
		
		JLabel heightLabelAns = new JLabel("");
		heightLabelAns.setFont(new Font("Rockwell", Font.PLAIN, 15));
		heightLabelAns.setBounds(85, 163, 94, 14);
		panelInfo.add(heightLabelAns);
		
		JLabel weightLabelAns = new JLabel("");
		weightLabelAns.setFont(new Font("Rockwell", Font.PLAIN, 15));
		weightLabelAns.setBounds(84, 143, 94, 14);
		panelInfo.add(weightLabelAns);
		
		Panel panelFiles = new Panel();
		tabbedPane.addTab("Patient Files", null, panelFiles, null);
		panelFiles.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 424, 47);
		panelFiles.add(panel);
		panel.setLayout(null);
		
		JLabel fileLabel = new JLabel("Files");
		fileLabel.setForeground(Color.WHITE);
		fileLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
		fileLabel.setBounds(10, 11, 57, 25);
		panel.add(fileLabel);
		
		searchFilesField = new JTextField();
		searchFilesField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					searchFilterFiles(searchFilesField.getText());
				} catch (IOException | org.json.simple.parser.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		searchFilesField.setForeground(Color.DARK_GRAY);
		searchFilesField.setBounds(65, 14, 131, 20);
		panel.add(searchFilesField);
		searchFilesField.setColumns(10);
		
		JButton allButton = new JButton("All");
		allButton.setForeground(Color.WHITE);
		allButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		allButton.setBackground(Color.LIGHT_GRAY);
		allButton.setBounds(197, 13, 48, 23);
		panel.add(allButton);
		
		JButton monthButton1 = new JButton("6 MONTH");
		monthButton1.setForeground(Color.WHITE);
		monthButton1.setFont(new Font("Tahoma", Font.BOLD, 10));
		monthButton1.setBackground(Color.LIGHT_GRAY);
		monthButton1.setBounds(245, 13, 87, 23);
		panel.add(monthButton1);
		
		JButton monthButton2 = new JButton("1 MONTH");
		monthButton2.setForeground(Color.WHITE);
		monthButton2.setFont(new Font("Tahoma", Font.BOLD, 10));
		monthButton2.setBackground(Color.LIGHT_GRAY);
		monthButton2.setBounds(332, 13, 90, 23);
		panel.add(monthButton2);
		listFiles.setFont(new Font("Rockwell", Font.PLAIN, 18));
		
		
		listFiles.setBounds(0, 46, 424, 389);
		listFiles.setModel(DLMFiles);
		listFiles.setSelectionBackground(Color.lightGray);
		panelFiles.add(listFiles);
		
		JLabel patient_pic = new JLabel("");
		patient_pic.setBounds(10, 7, 87, 80);
		frame.getContentPane().add(patient_pic);
		patient_pic.setIcon(new ImageIcon(addFilesPage.class.getResource("/cpsc488_project/PatientPic.png")));
		
		
		JLabel createdLabelAns = new JLabel("");
		createdLabelAns.setFont(new Font("Rockwell", Font.PLAIN, 15));
		createdLabelAns.setBounds(135, 22, 137, 14);
		panelInfo.add(createdLabelAns);
		
		JLabel modifiedLabelAns = new JLabel("");
		modifiedLabelAns.setFont(new Font("Rockwell", Font.PLAIN, 15));
		modifiedLabelAns.setBounds(135, 48, 137, 14);
		panelInfo.add(modifiedLabelAns);
		
		JLabel dobLabelAns = new JLabel("");
		dobLabelAns.setFont(new Font("Rockwell", Font.PLAIN, 15));
		dobLabelAns.setBounds(135, 73, 137, 14);
		panelInfo.add(dobLabelAns);
		
		
		//Fill in metadata
				sexLabelAns.setText(patientDirectory.sexSelected.toUpperCase());
				weightLabelAns.setText(patientDirectory.weightSelected);
				heightLabelAns.setText(patientDirectory.heightSelected);
				ethnicityLabelAns.setText(patientDirectory.ethnicitySelected.toUpperCase());
				dobLabelAns.setText(patientDirectory.dobSelected);
				modifiedLabelAns.setText(patientDirectory.dateModifiedSelected);
				createdLabelAns.setText(patientDirectory.dateCreatedSelected);
				
				JLabel backgroundLabel = new JLabel("");
				backgroundLabel.setIcon(new ImageIcon(viewFilePage.class.getResource("/cpsc488_project/bluebackground.jpg")));
				backgroundLabel.setBounds(0, 0, 429, 131);
				frame.getContentPane().add(backgroundLabel);
		
		frame.setBounds(100, 100, 445, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private ArrayList<String> getNames() throws IOException, org.json.simple.parser.ParseException
	{
		ArrayList<String> names =new ArrayList<String>();
		FileReader reader = new FileReader("../client/file_data.json");
		JSONParser parser = new JSONParser();
		
		b = (JSONArray) parser.parse(reader);
		//System.out.println(a);
		// https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
		String name, fileName;
		for (Object o : b) {
		    JSONObject person = (JSONObject) o;
		    
		    //Fill in JList with First name and Last name
		    name = (String) person.get("title");
		    fileName = name.substring(0, 1).toUpperCase() + name.substring(1);
	
		    names.add(fileName);
		    
		    
		    //names.add(name);
		}
		
		
		return names;
	}
	
}
