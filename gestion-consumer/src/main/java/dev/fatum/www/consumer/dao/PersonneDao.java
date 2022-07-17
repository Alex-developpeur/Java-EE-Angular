package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LEONARD
 */
public interface PersonneDao extends JpaRepository<Personne, Integer> {

	Personne findById(int id);

	List<Personne> findAllByOrderByIdDesc();

}
