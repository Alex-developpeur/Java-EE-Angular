package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.entities.Profil;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilDao extends JpaRepository<Profil, Integer> {

    @EntityGraph(value = "Profil.groupes")
    Profil findByEmail(String email);

    Profil emailDuplicated(String email, int id);
}
