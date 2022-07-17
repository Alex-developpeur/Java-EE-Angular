package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LEONARD
 */
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findById(int id);

    Utilisateur findByEmail(String email);

    List<Utilisateur> findAllByOrderByIdDesc();
}
