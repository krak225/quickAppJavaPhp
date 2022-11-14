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

import net.olea.santemaghreb.entities.College;

import net.olea.santemaghreb.entities.College;

import net.olea.santemaghreb.repositories.CollegeRepository;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {

private CollegeRepository collegeRepository;

public CollegeServiceImpl(CollegeRepository collegeRepository) {
	this.collegeRepository = collegeRepository;
}

@Override
public List<College> getColleges(){
	return collegeRepository.findAll();
}

@Override
public Optional<College> getCollege(Long id){
	return collegeRepository.findById(id);
}

@Override
public College addCollege(College college){
	college.setStatut(Statut.ACTIVE);
	college.setDate_creation(Timestamp.from(Instant.now()));
	college.setDate_modification(Timestamp.from(Instant.now()));
	return collegeRepository.save(college);
}

@Override
public College updateCollege(Long id, College college_new_data){
	Optional<College> otptionalCollege_old  = collegeRepository.findById(id);
	College college_old = otptionalCollege_old.get();

	college_new_data.setStatut(college_old.getStatut());
	college_new_data.setDate_creation(college_old.getDate_creation());
	college_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return collegeRepository.save(college_new_data);
}

@Override
public void deleteCollege(Long id){
	Optional<College> otptionalCollege = collegeRepository.findById(id);
	College college = otptionalCollege.get();

	college.setStatut(college.getStatut());
	college.setDate_modification(Timestamp.from(Instant.now()));

	collegeRepository.deleteById(id);
}

}
