package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;




import cpsc488_project.addPatientPage.Cmd;
import java.text.ParseException;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class patientDirectory {

	JFrame frame = new JFrame();
	DefaultListModel<Object> DLM = new DefaultListModel<Object>();
	
	private final JList<Object> nameList = new JList<Object>();
	private JTextField searchField;
	private final JLabel biometricsLabel = new JLabel("Left Click a Name to Edit Biometics*");
	private final JLabel addFileLabel = new JLabel("Right Click a Name to add a file to patient*");
	private final JScrollPane scrollPane = new JScrollPane();
	
	
	public class CmdPatients {
		public void patientName() throws Exception {
			
		//Navigate into Client folder and run python script to fetch patients
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "cd.. && cd Client/ && python3 irods_patient_fetch.py && dir && cd ");
      
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
	
	
	
	
	
	private void bindData() {
		getNames().stream().forEach((name) -> {
			DLM.addElement(name);
		});
		
	}
	
	//Search Box to filter through strings
	private void searchFilter(String searchTerm)
	{
		DefaultListModel<Object> filterItems = new DefaultListModel<Object>();
		ArrayList<String> names=getNames();
		
		names.stream().forEach((name) -> {
			String filteredName=name.toString().toLowerCase();
			if (filteredName.contains(searchTerm.toLowerCase())) {
				filterItems.addElement(name);
			}
		});
		DLM=filterItems;
		nameList.setModel(DLM);
		
	}
	
	
	public patientDirectory() throws org.json.simple.parser.ParseException {
		
		this.bindData();
		///////////////////////////////////////////////////////////////
		
		
		
	
		CmdPatients cmd = new CmdPatients();
		try {
			//Run Command Prompt
			cmd.patientName();
			 System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///////////////////////////////////////////
		
		
		
		JSONParser jsonP = new JSONParser();
		
		//Read from client folder
		try(FileReader reader = new FileReader("../client/patient_data.json")){
			Object obj = jsonP.parse(reader);
			JSONArray patientList = (JSONArray) obj;
			System.out.println(patientList);
			
			
			//Iterate through json file
			patientList.forEach(fName -> parsePatientObj((JSONObject)fName));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
		
		
		
		
		
		////////////////////////////////////////////////////////
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 460, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		scrollPane.setBounds(108, 158, 216, 303);
		
		frame.getContentPane().add(scrollPane);
			scrollPane.setViewportView(nameList);
		
			//When Name is clicked open update BIO-metrics
			nameList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					//If left click
					if (SwingUtilities.isLeftMouseButton(e))
					{
						updatePatientPage window = new updatePatientPage();
						window.frame.setVisible(true);
					}
					//If right click
					else if (SwingUtilities.isRightMouseButton(e))
					{
						addFilesPage window = new addFilesPage();
						window.frame.setVisible(true);
					}
				}
			});
			nameList.setModel(DLM);
			nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			nameList.setBackground(Color.LIGHT_GRAY);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Patient Directory");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(86, 34, 271, 56);
		frame.getContentPane().add(lblNewLabel);
		
		searchField = new JTextField();
		
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchFilter(searchField.getText());
			}
			
		
		});
		searchField.setBounds(181, 127, 143, 20);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchLabel.setBounds(118, 130, 53, 14);
		frame.getContentPane().add(searchLabel);
		biometricsLabel.setBounds(108, 472, 249, 14);
		
		frame.getContentPane().add(biometricsLabel);
		addFileLabel.setBounds(108, 488, 249, 14);
		
		frame.getContentPane().add(addFileLabel);
		
		
	}
	
	
	
	private static void parsePatientObj(JSONObject fName)
	{
	//Get fnames from json file
	String fnameslist = (String) fName.get("first_name");
	//Debug Print Fnames
	System.out.println("First Name: " + fnameslist);
	}
	
	private ArrayList<String> getNames()
	{
		ArrayList<String> names =new ArrayList<String>();
		
		
		
		//for (i=0; i< .length;i++) {
		//	names.add(first_name[i]);
		//}
		
		//names.add(fnameslist);
		
		
		
		//PlaceHolder Names/needs iterate through fnamelist above
		
		names.add("John");
		names.add("Christian");
		names.add("Wayne");
		names.add("Jeff");
		names.add("Jesse");
		names.add("Peach");
		names.add("Mario");
		names.add("Frank");
		names.add("Brittany");
		names.add("Benny");
		names.add("Jordan");
		names.add("Dan");
		names.add("Nick");
		names.add("Justin");
		names.add("Mike");
		names.add("Kathy");
		names.add("Dave");
		names.add("Joe");
		
		
		return names;
	}
	
}
