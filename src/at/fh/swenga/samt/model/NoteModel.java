package at.fh.swenga.samt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Notes")
public class NoteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true, length = 500)
	private String content;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserModel users;

	@Version
	long version;

	public NoteModel() {
		// TODO Auto-generated constructor stub
	}

	public NoteModel(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserModel getUsers() {
		return users;
	}

	public void setUsers(UserModel users) {
		this.users = users;
	}

}
