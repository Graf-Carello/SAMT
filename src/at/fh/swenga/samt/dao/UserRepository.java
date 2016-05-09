package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.samt.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	public List<UserModel> findByLastName(String lastName);	
	
	public UserModel findFirstByEmail(String email);
}
