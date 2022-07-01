package dev.fatum.www.consumer.dao.documents;

import dev.fatum.www.model.entities.documents.NumerosDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LEONARD
 */
public interface NumerosDocumentsDao extends JpaRepository<NumerosDocuments, Integer> {

    NumerosDocuments findByEntrepriseId(int id);

}
