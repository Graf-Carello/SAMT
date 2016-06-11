package at.fh.swenga.samt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "Homework")
public class HomeworkModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true, length = 200)
	private String description;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date deadline;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserModel user;
	
	@Version
	long version;

	public HomeworkModel() {
		// TODO Auto-generated constructor stub
	}

	public HomeworkModel(int id, String description, Date deadline, UserModel user) {
		super();
		this.id = id;
		this.description = description;
		this.deadline = deadline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUsers(UserModel user) {
		this.user = user;
	}
}
