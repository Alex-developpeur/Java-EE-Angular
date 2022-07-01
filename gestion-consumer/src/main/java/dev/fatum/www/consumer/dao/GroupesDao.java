package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.entities.Groupes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LEONARD
 */
public interface GroupesDao extends JpaRepository<Groupes, Integer> {

    Groupes findById(int id);

    Groupes findByNom(String nom);

    List<Groupes> findAllByOrderByIdDesc();

    Groupes nomDuplicated(String nom, int id);
}
