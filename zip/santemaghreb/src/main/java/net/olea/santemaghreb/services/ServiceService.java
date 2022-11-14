package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Service;

public interface ServiceService {

	List<Service> getServices();
	Optional<Service> getService(Long id);
	Service addService(Service service);
	Service updateService(Long id, Service service);
	void deleteService(Long id);

}
