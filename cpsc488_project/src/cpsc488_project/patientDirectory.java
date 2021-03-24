package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
import javax.swing.ImageIcon;




public class patientDirectory {

	JFrame frame = new JFrame();
	DefaultListModel<Object> DLM = new DefaultListModel<Object>();
	
	private final JList<Object> nameList = new JList<Object>();
	private JTextField searchField;
	private final JLabel biometricsLabel = new JLabel("Left Click a Name to Edit Biometics*");
	private final JLabel addFileLabel = new JLabel("Right Click a Name to add a file to patient*");
	private final JScrollPane scrollPane = new JScrollPane();
	public static String nameSelected="";
	private final JLabel backgroundpic2 = new JLabel("");
	
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
	
	
	
	
	
	private void bindData() throws IOException, org.json.simple.parser.ParseException {
		getNames().stream().forEach((name) -> {
			DLM.addElement(name);
		});
		
	}
	
	//Search Box to filter through strings
	private void searchFilter(String searchTerm) throws IOException, org.json.simple.parser.ParseException
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
	
	
	public patientDirectory() throws org.json.simple.parser.ParseException, IOException {
		
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
					
					
					
					nameSelected=nameList.getSelectedValue().toString();
					
					//If left click
					if (SwingUtilities.isLeftMouseButton(e))
					{
						
						System.out.println(nameSelected);
					
						
						//updatePatientPage window = new updatePatientPage();
						//window.frame.setVisible(true);
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
			nameList.setBackground(Color.WHITE);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Patient Directory");
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 35));
		lblNewLabel.setBounds(76, 39, 291, 56);
		frame.getContentPane().add(lblNewLabel);
		
		searchField = new JTextField();
		
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					searchFilter(searchField.getText());
				} catch (IOException | org.json.simple.parser.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		//Image Source https://www.vecteezy.com/vector-art/1432251-light-blue-background-with-rectangles
		frame.getContentPane().add(addFileLabel);
		backgroundpic2.setIcon(new ImageIcon(patientDirectory.class.getResource("/cpsc488_project/bluebackground.jpg")));
		backgroundpic2.setBounds(0, 85, 444, 504);
		
		frame.getContentPane().add(backgroundpic2);
		
		
	}
	
	
	
	private ArrayList<String> getNames() throws IOException, org.json.simple.parser.ParseException
	{
		ArrayList<String> names =new ArrayList<String>();
		FileReader reader = new FileReader("../client/patient_data.json");
		JSONParser parser = new JSONParser();
		
		// json
		
		JSONArray a = (JSONArray) parser.parse(reader);
		System.out.println(a);
		// https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
		String name, fName, lName;
		for (Object o : a) {
		    JSONObject person = (JSONObject) o;

		    name = (String) person.get("first_name");
		    fName = name.substring(0, 1).toUpperCase() + name.substring(1);
		    System.out.println(fName);

		    name = (String) person.get("last_name");
		    lName = name.substring(0, 1).toUpperCase() + name.substring(1);
		    System.out.println(lName);
		    
		    names.add(fName + " " + lName);
		}
		
		return names;
	}
	
	
	
	private void popup() {
		
		
		JMenuItem add = new JMenuItem("Add File");
		JMenuItem edit = new JMenuItem("Edit Patient");
		
		//popupMenu.add(add);
		//popupMenu.add(edit);
		
	}
	
	
}