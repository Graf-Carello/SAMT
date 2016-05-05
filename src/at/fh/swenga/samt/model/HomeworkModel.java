package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "homework")
public class HomeworkModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true, length = 200)
	private String description;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date deadline;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<UserModel> users;

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
