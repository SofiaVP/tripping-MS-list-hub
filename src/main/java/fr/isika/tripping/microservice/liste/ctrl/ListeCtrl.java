package fr.isika.tripping.microservice.liste.ctrl;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.isika.tripping.microservice.liste.model.ItemEntity;
import fr.isika.tripping.microservice.liste.model.ListeEntity;
import fr.isika.tripping.microservice.liste.repo.ItemRepo;
import fr.isika.tripping.microservice.liste.repo.ListeRepo;

@CrossOrigin("*")
@Controller
@RestController
@RequestMapping (path = "/tripping/lists")
public class ListeCtrl {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ListeRepo listeRepo; 
	
	@Autowired
	private ItemRepo itemRepo; 
	
	
	//++++++++++++++++++++ Find all items +++++++++++++++++++++
	@GetMapping(path = "item/findAllItems")
	public @ResponseBody Iterable<ItemEntity> getAllItems() {
		log.info("Tu es sur la bonne route ma bichettte");
		return itemRepo.findAll();
	}
	
	//++++++++++++++++++++ Find all lists +++++++++++++++++++++
	@GetMapping(path = "/findAllLists")
	public @ResponseBody Iterable<ListeEntity> getAllLists() {
		return listeRepo.findAll();
	}
	
	//++++++++++++++++++++++++++ find items by Liste category +++++++++++++
	@GetMapping(path = "items/findItemsByListeCategory/{category}")
	public @ResponseBody Iterable<ItemEntity> findItemsByCategory(@PathVariable String category) {
		return itemRepo.findItemsByCategory(category);
	}
	
	//++++++++++++++++++++++++++ find item by itemLabel +++++++++++++
	@GetMapping(path = "items/findItemByItemLabel/{itemLabel}")
	public @ResponseBody Optional<ItemEntity> findItemByitemLabel(@PathVariable String itemLabel) {
		return itemRepo.findItemByItemLabel(itemLabel);
	}
	
	//++++++++++++++++++++++++++ find item by id +++++++++++++
	@GetMapping(path = "items/findItemById/{id}")
	public @ResponseBody Optional<ItemEntity> findItemById(@PathVariable Integer id) {
		return itemRepo.findById(id);
	}
	
	//++++++++++++++++++++++++++ find list by category +++++++++++++
	@GetMapping(path = "findListByCategory/{category}")
	public @ResponseBody Optional<ListeEntity> findListByCategory(@PathVariable String category) {
		return listeRepo.findByCategory(category);
	}
	
	//++++++++++++++++++++++++++ find listId by category +++++++++++++	
	@GetMapping(path = "findListIdByCategory/{category}")
	public @ResponseBody Optional<Integer> findListIdByCategory(@PathVariable String category) {
		return listeRepo.findListIdByCategory(category);
	}
	
	//++++++++++++++++++++++++++ Create a new list +++++++++++++
	@PostMapping(path = "/createList")
	public ResponseEntity<Void> createList(@RequestBody ListeEntity list){
		log.info("In ListeCtrl ---------> method :createList ");
		ListeEntity createdList = listeRepo.save(list) ; 

		if (createdList==null) 
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdList.getId())
				.toUri();
		log.info("-----------------> list = "+list.toString()+"created");

		return ResponseEntity.noContent().build();

	}

	//++++++++++++++++++++++++++ Create a new item +++++++++++++
	@PostMapping(path = "item/createItem")
	public ResponseEntity<Void> createItem(@RequestBody ItemEntity item){
		log.info("In ListeCtrl ---------> method :createItem ");
		ItemEntity createdItem = itemRepo.save(item) ; 

		if (createdItem==null) 
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdItem.getId())
				.toUri();
		log.info("-----------------> list = "+item.toString()+"created");

		return ResponseEntity.noContent().build();

	}
		
	//++++++++++++++++++++++++++ delete item by id +++++++++++++
		@DeleteMapping(path = "items/removeItemById/{id}")
		public void removeItem(@PathVariable Integer id) {
			itemRepo.deleteById(id);
		}
		
	//++++++++++++++++++++++++++ delete item by Item +++++++++++++
		@DeleteMapping(path = "items/removeItemByItem")
		public void removeItem(@RequestBody ItemEntity itemToBeDeleted) {
			itemRepo.delete(itemToBeDeleted);
		}
		
	//++++++++++++++++++++++++++ delete item by item_label +++++++++++++
		@DeleteMapping(path = "items/removeItemByItemLabel/{itemLabel}")
		public void removeItemByItemLabel(@PathVariable String itemLabel) {
			log.info("In ListeCtrl ---------> method : removeItemByItemLabel ");
			Optional<ItemEntity> itemFound = findItemByitemLabel(itemLabel);
			if(itemFound.isPresent()) {
				ItemEntity itemToBeDeleted = itemFound.get();
				log.info("itemToBeDeleted"+ itemToBeDeleted);
				itemRepo.delete(itemToBeDeleted);
			}
			else {
				log.info("Sorry there is a problem : We have found none or sevral items with that label");
			}
			
		}

	//++++++++++++++++++++++++++ delete item by item_label +++++++++++++
	@DeleteMapping(path = "removeListByCategory/{category}")
	public void removeListByCategory(@PathVariable String category) {
		log.info("In ListeCtrl ---------> method : removeListByCategory ");
		Optional<ListeEntity> listfound = findListByCategory(category);
		if(listfound.isPresent()) {
			ListeEntity listToBeDeleted = listfound.get();
			listeRepo.delete(listToBeDeleted);
		}else {
			log.info("something went rong sorry");
		}
	}
		
		
		
	//++++++++++++++++++++++++++ find items by listid +++++++++++++
		@GetMapping(path = "items/findItemsByListeId/{listeId}")
		public @ResponseBody Iterable<ItemEntity> findItemsByListeId(@PathVariable Integer listeId) {
			return itemRepo.findItemsBylistId(listeId);
		}
	
		
	//++++++++++++++++++++++++++ find list by id +++++++++++++
		@GetMapping(path = "findListById/{id}")
		public @ResponseBody Optional<ListeEntity> findListById(@PathVariable Integer id) {
			return listeRepo.findById(id);
		}
}




















