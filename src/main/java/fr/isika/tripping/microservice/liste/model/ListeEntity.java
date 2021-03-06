package fr.isika.tripping.microservice.liste.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ListeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	
	private String category; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "liste", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemEntity> items;
	
	private String user;
	
	public ListeEntity() {
	}

	public ListeEntity(Integer id, String category, List<ItemEntity> items, String user) {
		super();
		this.id = id;
		this.category = category;
		this.items = items;
		this.user = user;
	}

	public ListeEntity( String category, String user) {
		super();
		this.category = category;
		this.user = user;
	}
	
	public ListeEntity(String category, List<ItemEntity> items, String user) {
		super();
		this.category = category;
		this.items = items;
		this.user = user;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
	


}
