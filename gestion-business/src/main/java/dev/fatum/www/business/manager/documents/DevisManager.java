package dev.fatum.www.business.manager.documents;

import dev.fatum.www.model.entities.documents.Devis;

import java.util.List;

public interface DevisManager {

    void saveDevis(Devis devis);

    List<Devis> findAllDevisDesc();

    Devis findDevisById(int id);

    void updateDevis(Devis devis);

    void deleteDevisById(int id);

}
