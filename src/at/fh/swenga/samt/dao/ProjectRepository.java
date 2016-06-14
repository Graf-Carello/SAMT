package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.samt.model.ProjectModel;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectModel, Integer>{
	
	@Query("select p from p ProjectModel p.isArchived = true")
	public List<ProjectModel> findActiveProjects();
	
	@Query("select u.userName from p ProjectModel join p.uid u where u.id = :id")
	public String findArchivedProjects(@Param("id") int id);
	

}
