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
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Projects")
public class ProjectModel {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "PID", columnDefinition = "int default 0")
	private int pid;

	@Column(nullable = false, length = 50)
	private String projectName;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Future
	private Date deadline;

	@Column(name = "progress", columnDefinition = "int default 0")
	private int progress;

	private String course;
	private int user;

	@Column(name = "isArchived", columnDefinition = "boolean default false")
	private Boolean isArchived;

	@ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
	private List<UserModel> users;

	@Version
	long version;

	public ProjectModel() {
		// TODO Auto-generated constructor stub
	}

	public ProjectModel(int pid, String projectName, Date deadline, Integer progress, String course, int user) {
		super();
		this.pid = pid;
		this.projectName = projectName;
		this.deadline = deadline;
		this.progress = progress;
		this.course = course;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	public void addUser(UserModel user) {
		if (users == null) {
			users = new ArrayList<UserModel>();
		}
		users.add(user);
	}

}
