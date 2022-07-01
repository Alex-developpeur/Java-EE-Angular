package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LEONARD
 */
public interface EntrepriseDao extends JpaRepository<Entreprise, Integer> {

    Entreprise findById(int id);

    List<Entreprise> findAllByOrderByIdDesc();

}
