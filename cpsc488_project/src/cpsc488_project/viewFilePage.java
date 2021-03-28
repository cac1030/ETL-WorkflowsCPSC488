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
		genderLabelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genderLabelV.setBounds(10, 141, 64, 14);
		panelInfo.add(genderLabelV);
		
		JLabel weightLablelV = new JLabel("Weight:");
		weightLablelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		weightLablelV.setBounds(10, 98, 64, 28);
		panelInfo.add(weightLablelV);
		
		JLabel dateCreatedLabelV = new JLabel("Patient Created:");
		dateCreatedLabelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateCreatedLabelV.setBounds(10, 22, 119, 14);
		panelInfo.add(dateCreatedLabelV);
		
		JLabel heightLabelV = new JLabel("Height:");
		heightLabelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		heightLabelV.setBounds(10, 117, 64, 26);
		panelInfo.add(heightLabelV);
		
		JLabel dateModifiedLabelV = new JLabel("Patient Modfied:");
		dateModifiedLabelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateModifiedLabelV.setBounds(10, 48, 119, 14);
		panelInfo.add(dateModifiedLabelV);
		
		JLabel dobLabelV = new JLabel("Date of Birth:");
		dobLabelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dobLabelV.setBounds(10, 73, 104, 14);
		panelInfo.add(dobLabelV);
		
		JLabel sexLabelV = new JLabel("Sex:");
		sexLabelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sexLabelV.setBounds(10, 158, 64, 14);
		panelInfo.add(sexLabelV);
		
		JLabel ethnicityLabelV = new JLabel("Ethnicity:");
		ethnicityLabelV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ethnicityLabelV.setBounds(10, 170, 64, 26);
		panelInfo.add(ethnicityLabelV);
		
		Panel panelFiles = new Panel();
		tabbedPane.addTab("Patient Files", null, panelFiles, null);
		panelFiles.setLayout(null);
		
		JLabel view_patientn_label = new JLabel("");
		view_patientn_label.setFont(new Font("Rockwell", Font.BOLD, 30));
		view_patientn_label.setBounds(107, 27, 246, 46);
		view_patientn_label.setText(patientDirectory.nameSelected);
		frame.getContentPane().add(view_patientn_label);
		
		JLabel patient_pic = new JLabel("");
		patient_pic.setBounds(10, 7, 87, 80);
		frame.getContentPane().add(patient_pic);
		patient_pic.setIcon(new ImageIcon(addFilesPage.class.getResource("/cpsc488_project/PatientPic.png")));
		
		frame.setBounds(100, 100, 445, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
