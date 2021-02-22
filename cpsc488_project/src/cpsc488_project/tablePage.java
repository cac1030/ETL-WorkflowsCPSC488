package cpsc488_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;

public class tablePage {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldGender;
	private JTextField textFieldAge;
	private JTextField textFieldNotes;
	private JTable table;
	
	DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablePage window = new tablePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tablePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		textFieldNotes.setBounds(183, 214, 86, 20);
		frame.getContentPane().add(textFieldNotes);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 276, 591, 193);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i=table.getSelectedRow();
				lblName.setText(model.getValueAt(i, 0).toString());
				lblGender.setText(model.getValueAt(i, 1).toString());
				lblAge.setText(model.getValueAt(i, 2).toString());
				lblNotes.setText(model.getValueAt(i, 3).toString());
				
			}
		});
		
		model=new DefaultTableModel();
		Object[] column = {"Patient Name", "Gender", "Age", "Notes"};
		final Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		//Add TextField to Table
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				row[0]= lblName.getText();
				row[1]= lblGender.getText();
				row[2]= lblAge.getText();
				row[3]= lblNotes.getText();
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
				model.setValueAt(lblName.getText(), i, 0);
				model.setValueAt(lblGender.getText(), i, 0);
				model.setValueAt(lblAge.getText(), i, 0);
				model.setValueAt(lblNotes.getText(), i, 0);
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
				lblName.setText("");
				lblGender.setText("");
				lblAge.setText("");
				lblNotes.setText("");
			}
		});
		btnClear.setBounds(491, 146, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel lblTitle = new JLabel("Patient Data");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTitle.setBounds(47, 35, 222, 53);
		frame.getContentPane().add(lblTitle);
	}
}
