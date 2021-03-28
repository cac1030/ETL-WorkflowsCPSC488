package cpsc488_project;



import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class addFilesPage {

	JFrame frame = new JFrame();
	private JTextArea metadataField;
	private String filename;
	private JLabel backgroundpic;

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
		fileTitleLabel.setBounds(100, 11, 269, 50);
		frame.getContentPane().add(fileTitleLabel);
		
		JLabel filesPatientLabel = new JLabel("");
		filesPatientLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		filesPatientLabel.setBounds(110, 53, 234, 20);
		frame.getContentPane().add(filesPatientLabel);
		
		JLabel picLabel = new JLabel("");
		picLabel.setIcon(new ImageIcon(addFilesPage.class.getResource("/cpsc488_project/FolderPic.png")));
		
		picLabel.setBounds(10, 11, 80, 80);
		frame.getContentPane().add(picLabel);
		
		metadataField = new JTextArea();
		metadataField.setRows(10);
		metadataField.setBounds(10, 240, 369, 214);
		metadataField.setLineWrap(true);
		metadataField.setWrapStyleWord(true);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
	    metadataField.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		frame.getContentPane().add(metadataField);
		
		JLabel metadataLabel = new JLabel("Attach Notes:");
		metadataLabel.setBounds(10, 224, 99, 14);
		frame.getContentPane().add(metadataLabel);
		
		JLabel fileNameLabel = new JLabel("");
		fileNameLabel.setForeground(new Color(0, 0, 255));
		fileNameLabel.setBounds(145, 142, 161, 14);
		frame.getContentPane().add(fileNameLabel);
		frame.setBounds(100, 100, 405, 504);
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
					
		browseButton.setBounds(10, 142, 125, 30);
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
		addFileButton.setBounds(10, 183, 125, 30);
		frame.getContentPane().add(addFileButton);
		
		backgroundpic = new JLabel("");
		backgroundpic.setBounds(0, 0, 389, 533);
		backgroundpic.setIcon(new ImageIcon(addPatientPage.class.getResource("/cpsc488_project/bluebackground.jpg")));
		frame.getContentPane().add(backgroundpic);
		
		
		
	}
}
