package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.FileChooserUI;

import cpsc488_project.addPatientPage.Cmd;

import java.awt.Font;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class addFilesPage {

	JFrame frame = new JFrame();

	public class CmdFiles {
		public void test2(String PATIENT_NAME, String filename, String address, String data) throws Exception {
		
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
		
		JLabel fileTitleLabel = new JLabel("Attach Files to Patient :");
		fileTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		fileTitleLabel.setBounds(10, 32, 284, 50);
		frame.getContentPane().add(fileTitleLabel);
		
		JLabel filesPatientLabel = new JLabel("Christian");
		filesPatientLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		filesPatientLabel.setBounds(304, 49, 103, 20);
		frame.getContentPane().add(filesPatientLabel);
		
		JButton addSingleFileButton = new JButton("Add File");
		addSingleFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename;
				String PATIENT_NAME = "DOE_JANE";
				String address = "52.168.52.180";
				String data = "1247";
				//Scanner fileIn;
				int response;
				JFileChooser filechooser = new JFileChooser("//Documents");
				
				filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				response = filechooser.showOpenDialog(null);
				
				if(response == JFileChooser.APPROVE_OPTION) {
					filename = filechooser.getSelectedFile().getName();
					 
					CmdFiles cmd = new CmdFiles();
					try {
						cmd.test2(PATIENT_NAME,filename,address,data);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "File Added");
				}
			}
		});
					
	
		addSingleFileButton.setBounds(246, 151, 125, 23);
		frame.getContentPane().add(addSingleFileButton);
		
		JList list = new JList();
		list.setBackground(Color.LIGHT_GRAY);
		list.setBounds(20, 135, 221, 319);
		frame.getContentPane().add(list);
		
		JButton deleteFileButton = new JButton("Delete File");
		deleteFileButton.setBounds(246, 185, 125, 23);
		frame.getContentPane().add(deleteFileButton);
		
		JButton fetchButton = new JButton("Fetch Data");
		fetchButton.setBounds(67, 105, 125, 23);
		frame.getContentPane().add(fetchButton);
		frame.setBounds(100, 100, 450, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
