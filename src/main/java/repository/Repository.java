package repository;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.CrudRepository;


public interface Repository <T> {
	
	public Iterable<T> findAll();
	
	public T findById(ObjectId id);
	
	public void save(T object);	
	
	public void remove(ObjectId id);
	
	public void createCollection();
	
	public void dropCollection();
	
	public void update(ObjectId id, T object);
	

}
