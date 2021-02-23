package cpsc488_project;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.Font;


public class tablePage {

	JFrame frame = new JFrame();
	JTextField textFieldName = new JTextField();
	JTextField textFieldGender = new JTextField();
	JTextField textFieldAge = new  JTextField();
	JTextField textFieldNotes = new JTextField();
	JTable table = new JTable();

	
	DefaultTableModel model;
	
	
	tablePage() {
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Patient Name:");
		lblName.setBounds(82, 116, 91, 20);
		frame.getContentPane().add(lblName);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(82, 147, 71, 20);
		frame.getContentPane().add(lblGender);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(82, 183, 71, 20);
		frame.getContentPane().add(lblAge);
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setBounds(82, 214, 71, 20);
		frame.getContentPane().add(lblNotes);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(183, 116, 86, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldGender = new JTextField();
		textFieldGender.setColumns(10);
		textFieldGender.setBounds(183, 147, 86, 20);
		frame.getContentPane().add(textFieldGender);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(183, 183, 86, 20);
		frame.getContentPane().add(textFieldAge);
		
		textFieldNotes = new JTextField();
		textFieldNotes.setColumns(10);
		textFieldNotes.setBounds(183, 214, 203, 51);
		frame.getContentPane().add(textFieldNotes);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 276, 591, 193);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i=table.getSelectedRow();
				textFieldName.setText(model.getValueAt(i, 0).toString());
				textFieldGender.setText(model.getValueAt(i, 1).toString());
				textFieldAge.setText(model.getValueAt(i, 2).toString());
				textFieldNotes.setText(model.getValueAt(i, 3).toString());
				
			}
		});
		
		model=new DefaultTableModel();
		Object[] column = {"Patient Name", "Gender", "Age", "Notes"};
		final Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		//Add TextField to Table
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				row[0]= textFieldName.getText();
				row[1]= textFieldGender.getText();
				row[2]= textFieldAge.getText();
				row[3]= textFieldNotes.getText();
				model.addRow(row);
			}
		});
		
		
		btnAdd.setBounds(379, 115, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i =table.getSelectedRow();
				
				if(i>=0)
				{
				model.setValueAt(textFieldName.getText(), i, 0);
				model.setValueAt(textFieldGender.getText(), i, 0);
				model.setValueAt(textFieldAge.getText(), i, 0);
				model.setValueAt(textFieldNotes.getText(), i, 0);
				JOptionPane.showMessageDialog(null,"Updated");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please select a Row");
				}
			}
		});
		btnUpdate.setBounds(491, 115, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int i=table.getSelectedRow();
					model.removeRow(i);
				}
		});
		btnDelete.setBounds(379, 146, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		//Clear Out Rows
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				textFieldName.setText("");
				textFieldGender.setText("");
				textFieldAge.setText("");
				textFieldNotes.setText("");
			}
		});
		btnClear.setBounds(491, 146, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel lblTitle = new JLabel("Patient Data");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTitle.setBounds(47, 35, 222, 53);
		frame.getContentPane().add(lblTitle);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				//Upload to iRods
			}
		});
		
		btnUpload.setBounds(435, 193, 89, 23);
		frame.getContentPane().add(btnUpload);
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				
				//Allow user to select file 
				JFileChooser fileChooser = new JFileChooser();
				int response = fileChooser.showOpenDialog(null);
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					
				
				
				try {
					//Separate Column Names by ,
					BufferedReader br = new BufferedReader(new FileReader(file));
					String firstLine = br.readLine().trim();
					String[] columnsName = firstLine.split(",");
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setColumnIdentifiers(columnsName);
					
					Object [] tableLines = br.lines().toArray();
					
					//Separate Rows by /
					for(int i = 0; i < tableLines.length; i++) {
						String line = tableLines[i].toString().trim();
						String[] dataRow = line.split("/");
						model.addRow(dataRow);
						
					}
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
		}
		});
		btnImport.setBounds(435, 73, 89, 23);
		frame.getContentPane().add(btnImport);
	}
}
