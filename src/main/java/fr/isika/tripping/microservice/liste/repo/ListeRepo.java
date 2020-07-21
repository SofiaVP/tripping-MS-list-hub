package fr.isika.tripping.microservice.liste.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.isika.tripping.microservice.liste.model.ListeEntity;

public interface ListeRepo extends CrudRepository<ListeEntity, Integer>{
	@Query("from ListeEntity l where l.category = :cat")
	Optional<ListeEntity> findByCategory(@Param("cat")String category);

	@Query("SELECT id FROM ListeEntity l WHERE l.category = :cat ")
	Optional<Integer> findListIdByCategory(@Param("cat")String category);

	@Query("from ListeEntity l where l.user = :user")
	Iterable<ListeEntity> findByUser(@Param("user")String user);

	Optional<ListeEntity> findByCategoryAndUser(String category, String user);
}
