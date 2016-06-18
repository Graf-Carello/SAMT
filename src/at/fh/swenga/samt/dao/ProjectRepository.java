package at.fh.swenga.samt.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.samt.model.ProjectModel;
import at.fh.swenga.samt.model.UserModel;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectModel, Integer>{
	
	@Query("select p from ProjectModel p where p.isArchived = false and p.user = :user")
	public List<ProjectModel> findActiveProjects(@Param("user") int user);
	
	@Query("select p from ProjectModel p where p.isArchived = true and p.user = :user")
	public List<ProjectModel> findArchivedProjects(@Param("user") int user);

	public ProjectModel findTop1ByOrderByPidDesc();
	
	@Query("select p.user from ProjectModel p where p.pid = :pid")
	public Set<Integer> findUserByPid(@Param("pid") int pid);
	
	@Query("select p from ProjectModel p where p.pid = :pid")
	public List<ProjectModel> findByPid(@Param("pid") int pid);
	
	@Query("select p.pid from ProjectModel p where p.id = :id")
	public int findPidById(@Param("id") int id);
	
	//@Query("select u from ProjectModel p join p.user u where p.pid = :pid")
	//public List<UserModel> findMembers(@Param("pid") int pid);
	
}
