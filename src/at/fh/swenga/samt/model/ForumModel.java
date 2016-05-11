package at.fh.swenga.samt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="Forum")
public class ForumModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String forumName;
	
	@Column
	private String post;
	
	@Column
	private String thread;
	
	@Column
	private Boolean isMain;
	
	@ManyToMany(mappedBy = "forum", fetch = FetchType.EAGER)
	private List<UserModel> users;

	@Version
	long version;
    
    
    public ForumModel() {
		// TODO Auto-generated constructor stub
    }


	public ForumModel(String forumName, String post, String thread, Boolean isMain) {
		super();
		this.forumName = forumName;
		this.post = post;
		this.thread = thread;
		this.isMain = isMain;
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


	public Boolean getIsMain() {
		return isMain;
	}


	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}


	public List<UserModel> getUsers() {
		return users;
	}


	public void setUsers(List<UserModel> user) {
		this.users = user;
	}
    
	
    
    
}
    
 