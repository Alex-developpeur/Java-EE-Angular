package dev.fatum.www.business.manager.documents;

import dev.fatum.www.model.entities.documents.Facture;

import java.util.List;

public interface FactureManager {

    void saveFacture(Facture facture);

    List<Facture> findAllFacturesDesc();

    Facture findFactureById(int id);

    void updateFacture(Facture facture);

    void deleteFactureById(int id);

}
