package dev.fatum.www.consumer.dao.documents;

import dev.fatum.www.model.entities.documents.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LEONARD
 */
public interface DevisDao extends JpaRepository<Devis, Integer> {

    Devis findById(int id);

    List<Devis> findAllByOrderByIdDesc();

}
