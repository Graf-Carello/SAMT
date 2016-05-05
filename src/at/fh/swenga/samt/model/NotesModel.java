package at.fh.swenga.samt.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class NotesModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	@Column(nullable = false, length = 30)
	String header;
	
	@Column(nullable = false, length = 30)
	String content;
	
	@Temporal(TemporalType.DATE)
	Date dateOfNote;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	UserModel user;
	
	public NotesModel() {}

	public NotesModel(int id, String header, String content, Date dateOfNote) {
		super();
		this.id = id;
		this.header = header;
		this.content = content;
		this.dateOfNote = dateOfNote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Date getDateOfNote() {
		return dateOfNote;
	}

	public void setDateOfNote(Date dateOfNote) {
		this.dateOfNote = dateOfNote;
	}
	
}
