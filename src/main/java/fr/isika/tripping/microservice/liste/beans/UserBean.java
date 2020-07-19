package fr.isika.tripping.microservice.liste.beans;

public class UserBean {
	private Integer id; 
	
	private String username;

	public UserBean() {
	}

	
	public UserBean(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
