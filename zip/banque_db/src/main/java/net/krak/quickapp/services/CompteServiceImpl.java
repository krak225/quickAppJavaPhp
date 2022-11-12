package net.krak.quickapp.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Compte;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

private CompteRepository compteRepository;

public CompteServiceImpl(CompteRepository compteRepository) {
	this.compteRepository = compteRepository;
}

@Override
public List<Compte> getComptes(){
	return compteRepository.findAll();
}

@Override
public Compte getCompte(Long id){
	return compteRepository.findById(id);
}

@Override
public Compte AddCompte(Compte compte){
	return compteRepository.save(compte);
}

@Override
public Compte EditCompte(Long id, Compte compte){
	return compteRepository.save(compte);
}

@Override
public Compte deleteCompte(Long id){
	return compteRepository.delete(id);
}

}
