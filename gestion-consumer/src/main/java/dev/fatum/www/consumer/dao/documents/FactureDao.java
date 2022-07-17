package dev.fatum.www.consumer.dao.documents;

import dev.fatum.www.model.entities.documents.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LEONARD
 */
public interface FactureDao  extends JpaRepository<Facture, Integer> {

    Facture findById(int id);

    List<Facture> findAllByOrderByIdDesc();

}

