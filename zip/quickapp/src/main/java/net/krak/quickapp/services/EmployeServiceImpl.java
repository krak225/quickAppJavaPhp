package net.krak.quickapp.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Statut;

import net.krak.quickapp.entities.Employe;

import net.krak.quickapp.entities.Employe;

import net.krak.quickapp.repositories.EmployeRepository;

@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {

private EmployeRepository employeRepository;

public EmployeServiceImpl(EmployeRepository employeRepository) {
	this.employeRepository = employeRepository;
}

@Override
public List<Employe> getEmployes(){
	return employeRepository.findAll();
}

@Override
public Optional<Employe> getEmploye(Long id){
	return employeRepository.findById(id);
}

@Override
public Employe addEmploye(Employe employe){
	employe.setStatut(Statut.ACTIVE);
	employe.setDate_creation(Instant.now());
	employe.setDate_modification(Instant.now());
	return employeRepository.save(employe);
}

@Override
public Employe updateEmploye(Long id, Employe employe_new_data){
	Optional<Employe> otptionalEmploye_old  = employeRepository.findById(id);
	Employe employe_old = otptionalEmploye_old.get();

	employe_new_data.setStatut(employe_old.getStatut());
	employe_new_data.setDate_creation(employe_old.getDate_creation());
	employe_new_data.setDate_modification(Instant.now());

	return employeRepository.save(employe_new_data);
}

@Override
public void deleteEmploye(Long id){
	Optional<Employe> otptionalEmploye = employeRepository.findById(id);
	Employe employe = otptionalEmploye.get();

	employe.setStatut(employe.getStatut());
	employe.setDate_modification(Instant.now());

	employeRepository.deleteById(id);
}

}
