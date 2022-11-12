package net.krak.quickapp.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Operation;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

private OperationRepository operationRepository;

public OperationServiceImpl(OperationRepository operationRepository) {
	this.operationRepository = operationRepository;
}

@Override
public List<Operation> getOperations(){
	return operationRepository.findAll();
}

@Override
public Operation getOperation(Long id){
	return operationRepository.findById(id);
}

@Override
public Operation AddOperation(Operation operation){
	return operationRepository.save(operation);
}

@Override
public Operation EditOperation(Long id, Operation operation){
	return operationRepository.save(operation);
}

@Override
public Operation deleteOperation(Long id){
	return operationRepository.delete(id);
}

}
