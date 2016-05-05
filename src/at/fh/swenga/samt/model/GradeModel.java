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
	int number;
	
	@Column(nullable = false, length = 30)
	String course;
	
	@Temporal(TemporalType.DATE)
	Date date;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	UserModel user;
	
	public GradeModel() {
	}

	public GradeModel(int id, int number, String course, Date date) {
		super();
		this.id = id;
		this.number = number;
		this.course = course;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	

	
	

}

