package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Bureau;

public interface BureauService {

	List<Bureau> getBureaus();
	Optional<Bureau> getBureau(Long id);
	Bureau addBureau(Bureau bureau);
	Bureau updateBureau(Long id, Bureau bureau);
	void deleteBureau(Long id);

}
