package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.fh.swenga.samt.model.EventModel;

public interface EventRepository extends JpaRepository<EventModel, Integer>{
	
	@Query("select e.name, e.startDate, e.endDate from EventModel e join e.user u where u.id = :userId ")
	public List<EventModel> findByUserId(@Param("userId") int userId);

}
