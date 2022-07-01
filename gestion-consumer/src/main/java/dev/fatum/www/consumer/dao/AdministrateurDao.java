package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LEONARD
 */
public interface AdministrateurDao extends JpaRepository<Administrateur, Integer> {
	
	Administrateur findById(int id);

	Administrateur findByEmail(String email);

	List<Administrateur> findAllByOrderByIdDesc();
}
