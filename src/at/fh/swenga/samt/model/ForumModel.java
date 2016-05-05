package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class ForumModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	String name;
	
	@Column(nullable = false, length = 30)
	String post;
	
	@Column(nullable = false, length = 30)
	String thread;
	
	@Temporal(TemporalType.DATE)
	Date date;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<UserModel> users;
	
	public ForumModel() {}

	public ForumModel(int id, String name, String post, String thread, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.post = post;
		this.thread = thread;
		this.date = date;
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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
	
	public void addUser(UserModel user) {		
		if (users== null) {
			users= new ArrayList<UserModel>();
		}
		users.add(user);
	}	

}
