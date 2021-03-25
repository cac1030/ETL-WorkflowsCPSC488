package cpsc488_project;



import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addFilesPage {

	JFrame frame = new JFrame();
	private JTextField metadataField;
	private String filename;

	public class CmdFiles {
		public void uploadFile(String PATIENT_NAME, String filename, String address, String data) throws Exception {
		
		//Navigate into Client folder and run Python3 script to add patient
        ProcessBuilder builder = new ProcessBuilder(
        		"cmd.exe", "/c", "cd.. && cd Client/ && python3 irods_up.py " + PATIENT_NAME + " " + filename);
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
	
	public addFilesPage() {
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel fileTitleLabel = new JLabel("Attach Files to Patient");
		fileTitleLabel.setFont(new Font("Rockwell", Font.BOLD, 24));
		fileTitleLabel.setBounds(110, 11, 269, 50);
		frame.getContentPane().add(fileTitleLabel);
		
		JLabel filesPatientLabel = new JLabel("");
		filesPatientLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		filesPatientLabel.setBounds(145, 55, 234, 20);
		frame.getContentPane().add(filesPatientLabel);
		
		JList listfiles = new JList();
		listfiles.setBackground(Color.LIGHT_GRAY);
		listfiles.setBounds(10, 141, 148, 313);
		frame.getContentPane().add(listfiles);
		
		JButton fetchButton = new JButton("Fetch New Files");
		fetchButton.setBounds(23, 110, 125, 23);
		fetchButton.setFocusable(false);
		frame.getContentPane().add(fetchButton);
		
		JLabel picLabel = new JLabel("");
		picLabel.setIcon(new ImageIcon(addFilesPage.class.getResource("/cpsc488_project/FolderPic.png")));
		
		picLabel.setBounds(20, 11, 80, 80);
		frame.getContentPane().add(picLabel);
		
		metadataField = new JTextField();
		metadataField.setBounds(168, 240, 256, 214);
		frame.getContentPane().add(metadataField);
		metadataField.setColumns(10);
		
		JLabel metadataLabel = new JLabel("Attach Notes:");
		metadataLabel.setBounds(168, 225, 99, 14);
		frame.getContentPane().add(metadataLabel);
		
		JLabel fileNameLabel = new JLabel("");
		fileNameLabel.setForeground(new Color(0, 0, 255));
		fileNameLabel.setBounds(303, 142, 99, 14);
		frame.getContentPane().add(fileNameLabel);
		frame.setBounds(100, 100, 450, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		filesPatientLabel.setText(patientDirectory.nameSelected);
		
		
		
		
		JButton browseButton = new JButton("Browse");
		browseButton.setFocusable(false);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String filename;
				int response;
				JFileChooser filechooser = new JFileChooser("//Documents");
				
				//Browse File Explorer for a file
				filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				response = filechooser.showOpenDialog(null);
				
				if(response == JFileChooser.APPROVE_OPTION) {
					
					filename = filechooser.getSelectedFile().getName();
					//Set Label 
					fileNameLabel.setText(filename);
					
				}
				
			}
			
		});
					
		browseButton.setBounds(168, 141, 125, 30);
		frame.getContentPane().add(browseButton);
		
		JButton addFileButton = new JButton("Upload File");
		addFileButton.setFocusable(false);
		addFileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String PATIENT_NAME = patientDirectory.nameSelected;
				String address = "54.227.89.39";
				String data = metadataField.getText();
				
				//No File
				if(filename == null) {
					JOptionPane.showMessageDialog(null, "Please Add a File before Uploading");
					
				}
				if(metadataField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Attach a note before Uploading");
				}
				//Metadata Empty
				
				else
				{
				//Upload File
			
				CmdFiles cmd = new CmdFiles();
				try {
					cmd.uploadFile(PATIENT_NAME,filename,address,data);
					JOptionPane.showMessageDialog(null, "File Added");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				}
			
			}
		});
		addFileButton.setBounds(168, 182, 125, 30);
		frame.getContentPane().add(addFileButton);
		
		
	}
}
