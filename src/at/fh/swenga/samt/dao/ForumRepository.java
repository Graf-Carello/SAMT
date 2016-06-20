package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.samt.model.ForumModel;

@Repository
@Transactional
public interface ForumRepository extends JpaRepository<ForumModel, Integer>{
	
	public List<ForumModel> findTop1ByOrderByIdDesc();
	
}
