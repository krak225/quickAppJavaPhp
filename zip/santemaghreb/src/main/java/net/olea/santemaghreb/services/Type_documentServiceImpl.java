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

import net.olea.santemaghreb.entities.Type_document;

import net.olea.santemaghreb.entities.Type_document;

import net.olea.santemaghreb.repositories.Type_documentRepository;

@Service
@Transactional
public class Type_documentServiceImpl implements Type_documentService {

private Type_documentRepository type_documentRepository;

public Type_documentServiceImpl(Type_documentRepository type_documentRepository) {
	this.type_documentRepository = type_documentRepository;
}

@Override
public List<Type_document> getType_documents(){
	return type_documentRepository.findAll();
}

@Override
public Optional<Type_document> getType_document(Long id){
	return type_documentRepository.findById(id);
}

@Override
public Type_document addType_document(Type_document type_document){
	type_document.setStatut(Statut.ACTIVE);
	type_document.setDate_creation(Timestamp.from(Instant.now()));
	type_document.setDate_modification(Timestamp.from(Instant.now()));
	return type_documentRepository.save(type_document);
}

@Override
public Type_document updateType_document(Long id, Type_document type_document_new_data){
	Optional<Type_document> otptionalType_document_old  = type_documentRepository.findById(id);
	Type_document type_document_old = otptionalType_document_old.get();

	type_document_new_data.setStatut(type_document_old.getStatut());
	type_document_new_data.setDate_creation(type_document_old.getDate_creation());
	type_document_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return type_documentRepository.save(type_document_new_data);
}

@Override
public void deleteType_document(Long id){
	Optional<Type_document> otptionalType_document = type_documentRepository.findById(id);
	Type_document type_document = otptionalType_document.get();

	type_document.setStatut(type_document.getStatut());
	type_document.setDate_modification(Timestamp.from(Instant.now()));

	type_documentRepository.deleteById(id);
}

}
