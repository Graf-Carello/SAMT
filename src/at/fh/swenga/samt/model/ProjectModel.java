package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "project")
public class ProjectModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 50)
	private String projectName;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date deadline;

	private String progress;
	private String course;
	
	@ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
	private List<UserModel> users;
	
	@Version
	long version;
	
	
	public ProjectModel() {
		// TODO Auto-generated constructor stub
	}

	public ProjectModel(int id, String projectName, Date deadline, String progress, String course) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.deadline = deadline;
		this.progress = progress;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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
