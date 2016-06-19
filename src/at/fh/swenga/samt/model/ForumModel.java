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
@Table(name = "Forum")
public class ForumModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private Integer user;

	@ManyToMany(mappedBy = "forum", fetch = FetchType.EAGER)
	private List<UserModel> users;

	@Version
	long version;

	public ForumModel() {
		// TODO Auto-generated constructor stub
	}

	public ForumModel(String title, String content, Integer user) {
		super();
		this.title = title;
		this.content = content;		
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> user) {
		this.users = user;
	}

}
