package fr.isika.tripping.microservice.liste.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.isika.tripping.microservice.liste.model.ItemEntity;

public interface ItemRepo extends CrudRepository<ItemEntity, Integer> {
	
	@Query("from ItemEntity i where i.liste.id = :lid")
	Iterable<ItemEntity> findItemsBylistId(@Param("lid") Integer listeId );

	@Query("from ItemEntity i where i.liste.category = :cat")
	Iterable<ItemEntity> findItemsByCategory(@Param("cat")String category);

	@Query("from ItemEntity i where i.itemLabel = :itemLabel")
	Optional<ItemEntity> findItemByItemLabel(@Param("itemLabel")String itemLabel);

	

	
	@Modifying
	@Query(nativeQuery=true, value = "DELETE FROM ItemEntity i where i.itemLabel = :itemLabel")
	void deleteByItemLabel(@Param("itemLabel")String itemLabel);

	//Integer deleteByItemLabel(String itemLabel);

}
