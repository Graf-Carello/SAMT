package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
	String forumName;
	
	@Column(nullable = false, length = 30)
	String forumPost;
	
	@Column(nullable = false, length = 30)
	String forumThread;
	
	@Temporal(TemporalType.DATE)
	Date dateOfPost;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<UserModel> users;
	
	public ForumModel() {}

	public ForumModel(int id, String forumName, String forumPost, String forumThread, Date dateOfPost) {
		super();
		this.id = id;
		this.forumName = forumName;
		this.forumPost = forumPost;
		this.forumThread = forumThread;
		this.dateOfPost = dateOfPost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getForumPost() {
		return forumPost;
	}

	public void setForumPost(String forumPost) {
		this.forumPost = forumPost;
	}

	public String getForumThread() {
		return forumThread;
	}

	public void setForumThread(String forumThread) {
		this.forumThread = forumThread;
	}

	public Date getDateOfPost() {
		return dateOfPost;
	}

	public void setDateOfPost(Date dateOfPost) {
		this.dateOfPost = dateOfPost;
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
