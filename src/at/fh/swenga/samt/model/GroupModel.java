package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class GroupModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	int groupName;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<UserModel> users;
	
	public GroupModel() {}

	public GroupModel(int id, int groupName) {
		super();
		this.id = id;
		this.groupName = groupName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupName() {
		return groupName;
	}

	public void setGroupName(int groupName) {
		this.groupName = groupName;
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
