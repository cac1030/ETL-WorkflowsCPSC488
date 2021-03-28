package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.JLabel;
import java.awt.Font;

public class viewFilePage {

	JFrame frame = new JFrame();

	
	
	public viewFilePage() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 90, 429, 463);
		frame.getContentPane().add(tabbedPane);
		
		Panel panelInfo = new Panel();
		tabbedPane.addTab("Patient Info", null, panelInfo, null);
		panelInfo.setLayout(null);
		
		JLabel genderLabelV = new JLabel("Gender:");
		genderLabelV.setBounds(10, 11, 46, 14);
		panelInfo.add(genderLabelV);
		
		JLabel weightLablelV = new JLabel("Weight:");
		weightLablelV.setBounds(10, 32, 46, 14);
		panelInfo.add(weightLablelV);
		
		Panel panelFiles = new Panel();
		tabbedPane.addTab("Patient Files", null, panelFiles, null);
		panelFiles.setLayout(null);
		
		JLabel view_patientn_label = new JLabel("");
		view_patientn_label.setFont(new Font("Rockwell", Font.BOLD, 30));
		view_patientn_label.setBounds(107, 27, 246, 46);
		view_patientn_label.setText(patientDirectory.nameSelected);
		frame.getContentPane().add(view_patientn_label);
		
		JLabel patient_pic = new JLabel("");
		patient_pic.setBounds(10, 11, 87, 80);
		frame.getContentPane().add(patient_pic);
		patient_pic.setIcon(new ImageIcon(addFilesPage.class.getResource("/cpsc488_project/PatientPic.png")));
		
		frame.setBounds(100, 100, 445, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
