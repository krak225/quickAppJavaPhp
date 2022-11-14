package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Type_document;

public interface Type_documentService {

	List<Type_document> getType_documents();
	Optional<Type_document> getType_document(Long id);
	Type_document addType_document(Type_document type_document);
	Type_document updateType_document(Long id, Type_document type_document);
	void deleteType_document(Long id);

}
