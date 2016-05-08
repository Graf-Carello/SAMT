package at.fh.swenga.samt.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="forum")
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
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserModel user;

	@Version
	long version;
    
    
    public ForumModel() {
		// TODO Auto-generated constructor stub
    }


	public ForumModel(int id, String forumName, String post, String thread, Boolean isMain) {
		super();
		this.id = id;
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


	public UserModel getUser() {
		return user;
	}


	public void setUser(UserModel user) {
		this.user = user;
	}
    
	
    
    
}
    
 