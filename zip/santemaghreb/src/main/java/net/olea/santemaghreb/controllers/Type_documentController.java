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

import net.olea.santemaghreb.entities.Type_document;
import net.olea.santemaghreb.services.Type_documentService;

@RestController
@RequestMapping(path="/api/")
public class Type_documentController {

public Type_documentService type_documentService;

public Type_documentController(Type_documentService type_documentService) {
	this.type_documentService = type_documentService;
}

@GetMapping(path="/type_documents")
public List<Type_document> getType_documents(){
	return type_documentService.getType_documents();
}

@GetMapping(path="/type_document/{id}")
public Optional<Type_document> getType_document(@PathVariable Long id){
	return type_documentService.getType_document(id);
}

@PostMapping(path="/type_document")
public Type_document addType_document(@RequestBody Type_document type_document){
	return type_documentService.addType_document(type_document);
}

@PutMapping(path="/type_document/{id}")
public Type_document updateType_document(@PathVariable Long id, @RequestBody Type_document type_document){
	return type_documentService.updateType_document(id, type_document);
}

@DeleteMapping(path="/type_document/{id}")
public void deleteType_document(@PathVariable Long id){
	type_documentService.deleteType_document(id);
}

}
