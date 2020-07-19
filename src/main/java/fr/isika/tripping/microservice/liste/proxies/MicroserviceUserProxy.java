package fr.isika.tripping.microservice.liste.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import fr.isika.tripping.microservice.liste.beans.UserBean;

@FeignClient(name="microservice.user", url="localhost:9999")
public interface MicroserviceUserProxy {

	@GetMapping(path="/tripping/user/findAllUsers")
	public List<UserBean> getAllUsers() ;
	
}
