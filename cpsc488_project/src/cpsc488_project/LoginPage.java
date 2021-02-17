package cpsc488_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener{

	//Setup for Buttons and Labels
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton clearButton = new JButton("Clear");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel noteLabel = new JLabel();
	
	
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage(HashMap<String,String> loginInfoOriginal){
		
		logininfo = loginInfoOriginal;
		
		//x 50 y 100 75 long 25 height 
		userIDLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		
		//Add Labels to Frame
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(noteLabel);
		
		//Position of Note in Frame
		noteLabel.setBounds(125,250,250,35);
		noteLabel.setFont(new Font(null,Font.BOLD,15));
		
		//Position of UserName in Frame
		userIDField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		//Position of Login Button
		loginButton.setBounds(125,200,100,25);
		//Turn off Blue Highlight around button
		loginButton.setFocusable(false);
		//Listener
		loginButton.addActionListener(this);
		
		//Position for Clear Button
		clearButton.setBounds(225,200,100,25);
		clearButton.setFocusable(false);
		clearButton.addActionListener(this);
		
		//Add Frame to screen
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(noteLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(clearButton);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Size of Frame
		frame.setSize(500,500);
		frame.setLayout(null);
		//Visible
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Clear the Text Fields
		if(e.getSource()==clearButton) {
			//Empty Text Boxes
			userIDField.setText("");
			userPasswordField.setText("");
		}
		
		if(e.getSource()==loginButton) {
			
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			if(logininfo.containsKey(userID)) {
				//If User name/Password is correct -> Success
				if(logininfo.get(userID).equals(password)) {
					noteLabel.setText("*Successful Login");
					//Before Launching Page Delete Login Screen
					frame.dispose();
					HomePage homePage = new HomePage();
				}
				else {
					//User name/Password is wrong
					noteLabel.setText("*Incorrect Password");
				}
			}
			else {
				noteLabel.setText("*Username does not exist");
			}
		}
	}
}
