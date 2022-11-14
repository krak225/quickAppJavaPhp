package net.olea.santemaghreb.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.olea.santemaghreb.entities.Service;
import net.olea.santemaghreb.services.ServiceService;

@RestController
@RequestMapping(path="/api/")
public class ServiceController {

public ServiceService serviceService;

public ServiceController(ServiceService serviceService) {
	this.serviceService = serviceService;
}

@GetMapping(path="/services")
public List<Service> getServices(){
	return serviceService.getServices();
}

@GetMapping(path="/service/{id}")
public Optional<Service> getService(@PathVariable Long id){
	return serviceService.getService(id);
}

@PostMapping(path="/service")
public Service addService(@RequestBody Service service){
	return serviceService.addService(service);
}

@PutMapping(path="/service/{id}")
public Service updateService(@PathVariable Long id, @RequestBody Service service){
	return serviceService.updateService(id, service);
}

@DeleteMapping(path="/service/{id}")
public void deleteService(@PathVariable Long id){
	serviceService.deleteService(id);
}

}
