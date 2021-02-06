package cpsc488_project;

import java.util.ArrayList;

public class Patient {
	private String firstName;
	private String lastName;
	private final int dob; 			// Expressed as a number between 1 - 366
	private boolean sex;			// True for male, false for female
	private ArrayList<PatientDocument> documents = new ArrayList<>();
	
	public Patient(String firstName, String lastName, int dob, boolean sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.sex = sex;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public boolean getSex() {
		return sex;
	}



	public void setSex(boolean sex) {
		this.sex = sex;
	}



	public ArrayList<PatientDocument> getDocuments() {
		return documents;
	}
	
	
	
	public void addDocument(int docType) {
		// documents.add(new PatientDocument());
	}


	
	public int getDob() {
		return dob;
	}
	
}