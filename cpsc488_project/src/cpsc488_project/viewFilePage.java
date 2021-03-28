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
		
		JLabel weightLablelV = new JLabel("Weight:");
		weightLablelV.setFont(new Font("Tahoma", Font.BOLD, 15));
		weightLablelV.setBounds(10, 98, 64, 28);
		panelInfo.add(weightLablelV);
		
		JLabel dateCreatedLabelV = new JLabel("Patient Created:");
		dateCreatedLabelV.setFont(new Font("Tahoma", Font.BOLD, 15));
		dateCreatedLabelV.setBounds(10, 22, 137, 14);
		panelInfo.add(dateCreatedLabelV);
		
		JLabel heightLabelV = new JLabel("Height:");
		heightLabelV.setFont(new Font("Tahoma", Font.BOLD, 15));
		heightLabelV.setBounds(10, 120, 64, 26);
		panelInfo.add(heightLabelV);
		
		JLabel dateModifiedLabelV = new JLabel("Patient Modfied:");
		dateModifiedLabelV.setFont(new Font("Tahoma", Font.BOLD, 15));
		dateModifiedLabelV.setBounds(10, 48, 137, 14);
		panelInfo.add(dateModifiedLabelV);
		
		JLabel dobLabelV = new JLabel("Date of Birth:");
		dobLabelV.setFont(new Font("Tahoma", Font.BOLD, 15));
		dobLabelV.setBounds(10, 73, 104, 14);
		panelInfo.add(dobLabelV);
		
		JLabel sexLabelV = new JLabel("Sex:");
		sexLabelV.setFont(new Font("Tahoma", Font.BOLD, 15));
		sexLabelV.setBounds(10, 145, 64, 14);
		panelInfo.add(sexLabelV);
		
		JLabel ethnicityLabelV = new JLabel("Ethnicity:");
		ethnicityLabelV.setFont(new Font("Tahoma", Font.BOLD, 15));
		ethnicityLabelV.setBounds(10, 158, 71, 26);
		panelInfo.add(ethnicityLabelV);
		
		JLabel sexLabelAns = new JLabel("");
		sexLabelAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sexLabelAns.setBounds(83, 147, 64, 14);
		panelInfo.add(sexLabelAns);
		
		JLabel ethnicityLabelAns = new JLabel("");
		ethnicityLabelAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ethnicityLabelAns.setBounds(84, 165, 64, 14);
		panelInfo.add(ethnicityLabelAns);
		
		JLabel heightLabelAns = new JLabel("");
		heightLabelAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		heightLabelAns.setBounds(84, 128, 64, 14);
		panelInfo.add(heightLabelAns);
		
		JLabel weightLabelAns = new JLabel("");
		weightLabelAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		weightLabelAns.setBounds(84, 107, 64, 14);
		panelInfo.add(weightLabelAns);
		
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
		
		
		JLabel createdLabelAns = new JLabel("");
		createdLabelAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createdLabelAns.setBounds(135, 22, 64, 14);
		panelInfo.add(createdLabelAns);
		
		JLabel modifiedLabelAns = new JLabel("");
		modifiedLabelAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modifiedLabelAns.setBounds(135, 48, 64, 14);
		panelInfo.add(modifiedLabelAns);
		
		JLabel dobLabelAns = new JLabel("");
		dobLabelAns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dobLabelAns.setBounds(135, 73, 64, 14);
		panelInfo.add(dobLabelAns);
		
		
		//Fill in text
				sexLabelAns.setText(patientDirectory.sexSelected);
				weightLabelAns.setText(patientDirectory.weightSelected);
				heightLabelAns.setText(patientDirectory.heightSelected);
				ethnicityLabelAns.setText(patientDirectory.ethnicitySelected);
				dobLabelAns.setText(patientDirectory.dobSelected);
				modifiedLabelAns.setText(patientDirectory.dateModifiedSelected);
				createdLabelAns.setText(patientDirectory.dateCreatedSelected);
		
		frame.setBounds(100, 100, 445, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
