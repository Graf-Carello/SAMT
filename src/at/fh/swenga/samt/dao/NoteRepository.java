package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.samt.model.NoteModel;
import at.fh.swenga.samt.model.UserModel;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<NoteModel, Integer>{
	
	public List<NoteModel> findAllByOrderByIdDesc();
	
	public List<NoteModel> findByUserOrderByIdDesc (UserModel userModel);
	
	@Query("select n from NoteModel n where n.isPublic=true")
	public List<NoteModel> findIfPublic();
	
	@Query("select u.userName from NoteModel n join n.user u where n.id = :id")
	public String findAuthor(@Param("id") int id);
	

}
