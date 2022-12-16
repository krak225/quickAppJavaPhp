package com.krak.rhweb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.krak.rhweb.CustomProperties;
import com.krak.rhweb.model.Employe;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeProxy {
	@Autowired
	private CustomProperties props;
	private String baseApiUrl;
	
	
	/**
	 * Get all employees
	 * @return An iterable of all employees
	 */
	public Iterable<Employe> getEmployes() {

		String getEmployesUrl = props.getApiUrl() + "/employes";

		RestTemplate restTemplate = new RestTemplate();
		//restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("username", "password"));
		ResponseEntity<Iterable<Employe>> response = restTemplate.exchange(
				getEmployesUrl, 
				HttpMethod.GET, 
				null,//new HttpEntity<T>(createHeaders(username, password)),
				new ParameterizedTypeReference<Iterable<Employe>>() {}
			);
		
		System.out.println("Get Employes call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get an employee by the id
	 * @param id The id of the employee
	 * @return The employee which matches the id
	 */
	public Employe getEmploye(int id) {
		String getEmployeUrl = props.getApiUrl() + "/employes/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employe> response = restTemplate.exchange(
				getEmployeUrl, 
				HttpMethod.GET, 
				null,
				Employe.class
			);
		
		System.out.println("Get Employe call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new employee 
	 * @param e A new employee (without an id)
	 * @return The employee full filled (with an id)
	 */
	public Employe createEmploye(Employe e) {
		String createEmployeUrl = props.getApiUrl() + "/employes/add";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employe> request = new HttpEntity<Employe>(e);
		ResponseEntity<Employe> response = restTemplate.exchange(
				createEmployeUrl, 
				HttpMethod.POST, 
				request, 
				Employe.class);
		
		System.out.println("Create Employe call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update an employee - using the PUT HTTP Method.
	 * @param e Existing employee to update
	 */
	public Employe updateEmploye(Employe e) {
		String updateEmployeUrl = props.getApiUrl() + "/employes/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employe> request = new HttpEntity<Employe>(e);
		ResponseEntity<Employe> response = restTemplate.exchange(
				updateEmployeUrl, 
				HttpMethod.PUT, 
				request, 
				Employe.class);
		
		System.out.println("Update Employe call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete an employee using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The employee to delete
	 */
	public void deleteEmploye(int id) {
		String deleteEmployeUrl = props.getApiUrl() + "/employes/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteEmployeUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		System.out.println("Delete Employe call " + response.getStatusCode().toString());
		
	}
	
	
}

