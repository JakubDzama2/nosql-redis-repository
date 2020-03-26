package sk.upjs.nosql_redis_repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface StudentRepository extends CrudRepository<RedisStudent, Long> {

	public List<RedisStudent> findByPriezvisko(String priezvisko);
	
}
