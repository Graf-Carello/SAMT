package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "user")
public class UserModel implements Comparable<UserModel> {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String firstName;

	@Column(nullable = false, length = 30)
	private String lastName;

	private String degreeCourse;
	private String email;

	@Column(nullable = false, length = 20)
	private String password;
	private String profilePicture;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<ProjectModel> projects;

	@OneToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
	private List<HomeworkModel> homework;

	@OneToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
	private List<GradeModel> grades;

	@OneToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
	private List<NoteModel> notes;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<ForumModel> forum;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<GroupModel> groups;

	@Version
	long version;

	public UserModel() {
	}

	public UserModel(String firstName, String lastName, String degreeCourse, String email, String password,
			String profilePicture) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.degreeCourse = degreeCourse;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDegreeCourse() {
		return degreeCourse;
	}

	public void setDegreeCourse(String degreeCourse) {
		this.degreeCourse = degreeCourse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public List<ProjectModel> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectModel> projects) {
		this.projects = projects;
	}

	public List<HomeworkModel> getHomework() {
		return homework;
	}

	public void setHomework(List<HomeworkModel> homework) {
		this.homework = homework;
	}

	public void addHomework(HomeworkModel homework) {
		if (this.homework == null) {
			this.homework = new ArrayList<HomeworkModel>();
		}
		this.homework.add(homework);
	}

	public List<GradeModel> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeModel> grades) {
		this.grades = grades;
	}

	public void addGrade(GradeModel grade) {
		if (grades == null) {
			grades = new ArrayList<GradeModel>();
		}
		grades.add(grade);
	}

	public List<NoteModel> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteModel> note) {
		this.notes = note;
	}

	public void addNote(NoteModel note) {
		if (notes == null) {
			notes = new ArrayList<NoteModel>();
		}
		notes.add(note);
	}

	public List<ForumModel> getForum() {
		return forum;
	}

	public void setForum(List<ForumModel> forum) {
		this.forum = forum;
	}
	
	public void addForum(ForumModel forum) {
		if (this.forum == null) {
			this.forum = new ArrayList<ForumModel>();
		}
		this.forum.add(forum);
	}

	public List<GroupModel> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupModel> groups) {
		this.groups = groups;
	}
	
	public void addGroup(GroupModel group) {
		if (groups == null) {
			groups = new ArrayList<GroupModel>();
		}
		groups.add(group);
	}

	@Override
	public int compareTo(UserModel o) {
		return id - o.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
