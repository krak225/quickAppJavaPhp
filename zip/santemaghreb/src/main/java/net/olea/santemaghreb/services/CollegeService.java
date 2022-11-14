package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.College;

public interface CollegeService {

	List<College> getColleges();
	Optional<College> getCollege(Long id);
	College addCollege(College college);
	College updateCollege(Long id, College college);
	void deleteCollege(Long id);

}
