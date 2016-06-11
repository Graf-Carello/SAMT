package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.samt.model.NoteModel;
import at.fh.swenga.samt.model.UserModel;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<NoteModel, Integer>{
	
	public List<NoteModel> findAllByOrderByIdDesc();
	
	public List<NoteModel> findByUserOrderByIdDesc (UserModel userM);
	
	

}
