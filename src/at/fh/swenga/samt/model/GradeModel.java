package at.fh.swenga.samt.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Grade")
/*@NamedQueries( //container for all our queries
	
		{
			@NamedQuery (
					
					number="GradeModel.doSomethingWithNumbers",
					//LIKE is not case sensitiv, searchs everywhere
					query = "SELECT g FROM GradeModel g WHERE g.number = :number"
					)
		}
		
		)
		*/
public class GradeModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	int gradeNumber;
	
	@Column(nullable = false, length = 30)
	String course;
	
	@Temporal(TemporalType.DATE)
	Date dateOfGrade;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	UserModel user;
	
	public GradeModel() {
	}

	public GradeModel(int id, int gradeNumber, String course, Date dateOfGrade) {
		super();
		this.id = id;
		this.gradeNumber = gradeNumber;
		this.course = course;
		this.dateOfGrade = dateOfGrade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(int gradeNumber) {
		this.gradeNumber = gradeNumber;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getDateOfGrade() {
		return dateOfGrade;
	}

	public void setDateOfGrade(Date dateOfGrade) {
		this.dateOfGrade = dateOfGrade;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	

	
	

}

