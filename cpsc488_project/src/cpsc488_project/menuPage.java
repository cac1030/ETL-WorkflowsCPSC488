package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class menuPage {

	JFrame frame = new JFrame();
	
	
	public menuPage() {
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 693, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton PatientDirectoryButton = new JButton("Patient Directory");
		PatientDirectoryButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PatientDirectoryButton.setBounds(339, 174, 163, 58);
		PatientDirectoryButton.setFocusable(false);
		PatientDirectoryButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				patientDirectory window = new patientDirectory();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(PatientDirectoryButton);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddPatient.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				addPatientPage window = new addPatientPage();
				window.frame.setVisible(true);
			}
		});
		btnAddPatient.setBounds(181, 174, 148, 58);
		btnAddPatient.setFocusable(false);
		frame.getContentPane().add(btnAddPatient);
		
		JLabel menuLabel = new JLabel("Main Menu");
		menuLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		menuLabel.setBounds(222, 76, 236, 101);
		frame.getContentPane().add(menuLabel);
		
	}
}
