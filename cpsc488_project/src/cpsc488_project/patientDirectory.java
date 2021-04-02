package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import cpsc488_project.addPatientPage.Cmd;
import java.text.ParseException;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.MouseInfo;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;




public class patientDirectory {

	JFrame frame = new JFrame();
	DefaultListModel<Object> DLM = new DefaultListModel<Object>();
	
	private final JList<Object> nameList = new JList<Object>();
	private JTextField searchField;
	private final JLabel addFileLabel = new JLabel("Select a Name by left clicking then");
	private final JScrollPane scrollPane = new JScrollPane();
	public static String nameSelected="";
	public static String sexSelected="";
	public static String firstName="";
	public static String lastName="";
	public static String weightSelected="";
	public static String heightSelected="" ;
	public static String ethnicitySelected ="";
	public static String dobSelected ="";
	public static String dateCreatedSelected ="";
	public static String dateModifiedSelected ="";
	private final JLabel backgroundpic2 = new JLabel("");
	
	public JSONArray a;
	public int indicies;
	
	
	private final JPopupMenu pop = new JPopupMenu();
	String row="";
	private final JLabel lblRightClickA = new JLabel("Right click the name to view options*");
	
	public class CmdPatients {
		public void patientName() throws Exception {
			
		//Navigate into Client folder and run Python3 script to fetch patients
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "cd.. && cd Client/ && python3 irods_patient_fetch.py " + "-f");
      
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
		
		
		CmdPatients cmd = new CmdPatients();
		try {
			//Run Command Prompt
			cmd.patientName();
			 //System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///////////////////////////////////////////
		this.bindData();
		///////////////////////////////////////////////////////////////
		
		addPopup();
		
		
		
		////////////////////////////////////////////////////////
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 460, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblRightClickA.setVerticalAlignment(SwingConstants.TOP);
		lblRightClickA.setBounds(108, 486, 278, 28);
		
		frame.getContentPane().add(lblRightClickA);
		scrollPane.setBounds(108, 158, 216, 303);
		
		frame.getContentPane().add(scrollPane);
			
			scrollPane.setViewportView(nameList);
		
			//When Name is clicked open options
			nameList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					
		
					nameSelected=nameList.getSelectedValue().toString();
					
					nameList.setSelectedIndex(nameList.locationToIndex(e.getPoint()));
					
					//Index Number Selected
					indicies = nameList.getSelectedIndex();
					
					//Send Info Selected to View Info Page
					
					JSONObject selected = (JSONObject) a.get(indicies);
					firstName = (String) selected.get("first_name");
				    lastName = (String) selected.get("last_name");
					dobSelected = (String) selected.get("dob");
				    dateCreatedSelected = (String) selected.get("date_created");
				    dateModifiedSelected = (String) selected.get("date_modified");
				    sexSelected = (String) selected.get("sex");
				    weightSelected = (String) selected.get("weight");
				    heightSelected = (String) selected.get("height");
				    ethnicitySelected = (String) selected.get("ethnicity");
					
					
					
					
					
					//Right click open popup
					if (SwingUtilities.isRightMouseButton(e) && nameList.locationToIndex(e.getPoint())==nameList.getSelectedIndex())
					{
						if(! nameList.isSelectionEmpty()) {
							
							pop.show(nameList,e.getX(),e.getY());
							
						}
						
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
		addFileLabel.setVerticalAlignment(SwingConstants.TOP);
		addFileLabel.setBounds(108, 472, 196, 20);
		
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
		
		a = (JSONArray) parser.parse(reader);
		//System.out.println(a);
		// https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
		String name,fname,lname;
		for (Object o : a) {
		    JSONObject person = (JSONObject) o;
		    
		    //Fill in JList with First name and Last name
		    name = (String) person.get("first_name");
		    fname = name.substring(0, 1).toUpperCase() + name.substring(1);
	
		    
		    name = (String) person.get("last_name");
		    lname = name.substring(0, 1).toUpperCase() + name.substring(1);
		   
		    
		    names.add(fname + " " + lname);
		    
		    
		}
		
		
		return names;
	}
	
	
	
	private void addPopup() {
		//Image Source https://icons8.com/icon/cFp23Lr2MzW0/upload-file
		//new ImageIcon(LoginPage.class.getResource("/cpsc488_project/lock.png"))
		JMenuItem add =new JMenuItem("Add File", new ImageIcon(patientDirectory.class.getResource("/cpsc488_project/upload.png")));
		JMenuItem view =new JMenuItem("View Info", new ImageIcon(patientDirectory.class.getResource("/cpsc488_project/viewpic.png")));
		//Image Source https://icons8.com/icon/3553/edit-file
		JMenuItem edit = new JMenuItem("Edit Biometrics", new ImageIcon(patientDirectory.class.getResource("/cpsc488_project/editpic.png")));
		
		
		pop.add(add);
		pop.add(view);
		pop.add(edit);
		
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addFilesPage window = new addFilesPage();
				window.frame.setVisible(true);
			}
			
		});
		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewFilePage window;
				try {
					window = new viewFilePage();
					window.frame.setVisible(true);
				} catch (org.json.simple.parser.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
		});
		
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updatePatientPage window = new updatePatientPage();
				window.frame.setVisible(true);
			}
			
		});
	}
}