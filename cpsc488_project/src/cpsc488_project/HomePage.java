package cpsc488_project;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HomePage {

	JFrame frame = new JFrame();
	JLabel homePageLabel = new JLabel("New Page");
	
	
	HomePage(){
		
		homePageLabel.setBounds(0,0,200,35);
		//Null font style, 25 font
		homePageLabel.setFont(new Font(null, Font.BOLD,25));
		
		
		frame.add(homePageLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Size of screen
		frame.setSize(1000,1000);
		//No layout manager
		frame.setLayout(null);
		//Visible
		frame.setVisible(true);
		
	}
}
