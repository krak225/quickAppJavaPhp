package com.krak.rhweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krak.rhweb.model.Employe;
import com.krak.rhweb.repository.EmployeProxy;

import lombok.Data;

@Data
@Service
public class EmployeService {
	
	@Autowired
	private EmployeProxy employeProxy;
	
	public Employe getEmploye(final int id) {
		return employeProxy.getEmploye(id);
	}
	
	public Iterable<Employe> getEmployes() {
		return employeProxy.getEmployes();
	}
	
	public void deleteEmploye(final int id) {
		employeProxy.deleteEmploye(id);
	}
	
	public Employe saveEmploye(Employe employe) {
		Employe savedEmploye;
		
		// Functional rule : Last name must be capitalized.
		employe.setPrenoms(employe.getPrenoms().toUpperCase());

		if(employe.getId() == 0) {//null replaced by 0
			// If id is null, then it is a new employe.
			savedEmploye = employeProxy.createEmploye(employe);
		} else {
			savedEmploye = employeProxy.updateEmploye(employe);
		}
		
		return savedEmploye;
	}

}