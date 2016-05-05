package at.fh.swenga.samt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Min;


public class UserModel implements Comparable<UserModel> {

	@Min(1)
	private int id;

	private String firstName;
	private String lastName;
	private String degreeCourse;
	private String email;
	private String password;
	private String profilePicture;
	
	//#######################################
	//verknüpfung zu Grades
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
    @OrderBy("gradeNumber")
    private ArrayList<GradeModel> grades;
    
    //verknüpfung zu Notes
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
    @OrderBy("dateOfNote")
    private List<NotesModel> notes;
    
    //verknüpfung zu forum
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<ForumModel> forums;
	
	//verknüpfung zu gruppen
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<GroupModel> groups;
	
	//######################################

	public UserModel() {
	}

	public UserModel(int id, String firstName, String lastName, String degreeCourse, String email, String password,
			String profilePicture) {
		super();
		this.id = id;
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
	
	
	//##########################################
	//getters and setters to foreign elements
	
	public ArrayList<GradeModel> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<GradeModel> grades) {
		this.grades = grades;
	}
	
	public void addGrade(GradeModel grade) {		
		if (grades== null) {
		grades= new ArrayList<GradeModel>();
		}
		grades.add(grade);
	}

	public List<NotesModel> getNotes() {
		return notes;
	}

	public void setNotes(List<NotesModel> notes) {
		this.notes = notes;
	}
	
	public void addNote(NotesModel note) {		
		if (notes== null) {
		notes= new ArrayList<NotesModel>();
		}
		notes.add(note);
	}

	public List<ForumModel> getForums() {
		return forums;
	}

	public void setForums(List<ForumModel> forums) {
		this.forums = forums;
	}
	
	public void addForum(ForumModel forum) {		
		if (forums== null) {
		forums= new ArrayList<ForumModel>();
		}
		forums.add(forum);
	}

	public List<GroupModel> getGrous() {
		return groups;
	}

	public void setGrous(List<GroupModel> grous) {
		this.groups = grous;
	}
	
	public void addGroup(GroupModel group) {		
		if (groups == null) {
		groups = new ArrayList<GroupModel>();
		}
		groups.add(group);
	}
	
	//###################################

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
