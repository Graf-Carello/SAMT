package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.samt.model.HomeworkModel;
import at.fh.swenga.samt.model.ProjectModel;
import at.fh.swenga.samt.model.UserModel;

@Repository
@Transactional
public interface HomeworkRepository extends JpaRepository<HomeworkModel, Integer>{
	
	public List<HomeworkModel> findByUser(UserModel userModel); 
	
	//@Query("select h from HomeworkModel h where h.user = :user order by deadline asc")
	public List<HomeworkModel> findByUserOrderByDeadlineAsc(UserModel user);

}
