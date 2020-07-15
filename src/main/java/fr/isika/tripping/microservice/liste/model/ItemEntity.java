package fr.isika.tripping.microservice.liste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 

	private String itemLabel; 
	
	private int quantity; 
	
	private boolean isInBag; 
	
	@ManyToOne
	@JoinColumn(name = "liste_id", nullable = false)
	private ListeEntity liste;
	
	public ItemEntity() {
	}

	public ItemEntity(Integer id, String itemLabel, int quantity, boolean isInBag, ListeEntity liste) {
		super();
		this.id = id;
		this.itemLabel = itemLabel;
		this.quantity = quantity;
		this.isInBag = isInBag;
		this.liste = liste;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isInBag() {
		return isInBag;
	}

	public void setInBag(boolean isInBag) {
		this.isInBag = isInBag;
	}

	public ListeEntity getListe() {
		return liste;
	}

	public void setListe(ListeEntity liste) {
		this.liste = liste;
	}

}
