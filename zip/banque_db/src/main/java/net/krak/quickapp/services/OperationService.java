package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Operation;

public interface OperationService {

	List<Operation> getOperations();
	Optional Operation getOperation(Long id);
	Operation AddOperation(Operation operation);
	Operation EditOperation(Long id, Operation operation);
	void Operation deleteOperation(Long id);

}
