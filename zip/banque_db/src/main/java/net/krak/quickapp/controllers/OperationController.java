package net.krak.quickapp.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.krak.quickapp.entities.Operation;
import net.krak.quickapp.services.OperationService;

@RestController
@RequestMapping(path="/api/")
public class OperationController {

public OperationService operationService;

public OperationController(OperationService operationService) {
	this.operationService = operationService;
}

@GetMapping(path="/operations")
public List<Operation> getOperations(){
	return operationService.findAll();
}

@GetMapping(path="/operation/{id}")
public Operation getOperation(@PathVariable Long id){
	return operationService.findById(id);
}

@PostMapping(path="/operation")
public Operation AddOperation(Operation operation){
	return operationService.save(operation);
}

@PutMapping(path="/operation/{id}")
public Operation EditOperation(@PathVariable Long id, Operation operation){
	return operationService.save(operation);
}

@DeleteMapping(path="/operation/{id}")
public Operation DeleteOperation(@PathVariable Long id){
	return operationService.delete(id);
}

}
