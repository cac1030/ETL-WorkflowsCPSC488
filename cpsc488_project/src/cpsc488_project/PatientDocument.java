package cpsc488_project;

import java.util.ArrayList;
import java.util.List;

public class PatientDocument {
	private final static int PDF = 0;
	private final static int IMAGE = 1;
	private final static int TEXT = 2;
	private String filePath;
	private String title;
	private ArrayList<String> notes = new ArrayList<>();
	private final int docType;
	private final String dateCreated;
	private String dateModified;
	
	public PatientDocument(String filePath, String title, String[] notes, int docType, String dateCreated) {
		this.filePath = filePath;
		this.title = title;
		this.docType = docType;
		this.dateCreated = dateCreated;
		
		for(String note : notes) {
			this.notes.add(note);
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void addNote(String note) {
		this.notes.add(note);
	}
	
	public int getDocType() {
		return docType;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	
	

}
