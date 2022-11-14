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

import net.olea.santemaghreb.entities.College;
import net.olea.santemaghreb.services.CollegeService;

@RestController
@RequestMapping(path="/api/")
public class CollegeController {

public CollegeService collegeService;

public CollegeController(CollegeService collegeService) {
	this.collegeService = collegeService;
}

@GetMapping(path="/colleges")
public List<College> getColleges(){
	return collegeService.getColleges();
}

@GetMapping(path="/college/{id}")
public Optional<College> getCollege(@PathVariable Long id){
	return collegeService.getCollege(id);
}

@PostMapping(path="/college")
public College addCollege(@RequestBody College college){
	return collegeService.addCollege(college);
}

@PutMapping(path="/college/{id}")
public College updateCollege(@PathVariable Long id, @RequestBody College college){
	return collegeService.updateCollege(id, college);
}

@DeleteMapping(path="/college/{id}")
public void deleteCollege(@PathVariable Long id){
	collegeService.deleteCollege(id);
}

}
