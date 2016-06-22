package at.fh.swenga.samt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.samt.model.EventModel;


public interface EventRepository extends JpaRepository<EventModel, Integer>{
	
	public List<EventModel> findByCreator (int creator);
}
