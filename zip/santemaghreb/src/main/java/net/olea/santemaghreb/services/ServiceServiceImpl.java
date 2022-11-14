package net.olea.santemaghreb.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Statut;

import net.olea.santemaghreb.entities.Service;

import net.olea.santemaghreb.entities.Service;

import net.olea.santemaghreb.repositories.ServiceRepository;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

private ServiceRepository serviceRepository;

public ServiceServiceImpl(ServiceRepository serviceRepository) {
	this.serviceRepository = serviceRepository;
}

@Override
public List<Service> getServices(){
	return serviceRepository.findAll();
}

@Override
public Optional<Service> getService(Long id){
	return serviceRepository.findById(id);
}

@Override
public Service addService(Service service){
	service.setStatut(Statut.ACTIVE);
	service.setDate_creation(Timestamp.from(Instant.now()));
	service.setDate_modification(Timestamp.from(Instant.now()));
	return serviceRepository.save(service);
}

@Override
public Service updateService(Long id, Service service_new_data){
	Optional<Service> otptionalService_old  = serviceRepository.findById(id);
	Service service_old = otptionalService_old.get();

	service_new_data.setStatut(service_old.getStatut());
	service_new_data.setDate_creation(service_old.getDate_creation());
	service_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return serviceRepository.save(service_new_data);
}

@Override
public void deleteService(Long id){
	Optional<Service> otptionalService = serviceRepository.findById(id);
	Service service = otptionalService.get();

	service.setStatut(service.getStatut());
	service.setDate_modification(Timestamp.from(Instant.now()));

	serviceRepository.deleteById(id);
}

}
