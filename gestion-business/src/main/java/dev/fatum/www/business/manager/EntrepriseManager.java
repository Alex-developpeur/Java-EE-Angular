package dev.fatum.www.business.manager;

import dev.fatum.www.model.entities.Entreprise;

import java.util.List;

public interface EntrepriseManager {

    void saveEntreprise(Entreprise entreprise);

    List<Entreprise> findAllEntreprisesDesc();

    Entreprise findEntrepriseById(int id);

    void updateEntreprise(Entreprise entreprise);

    void deleteEntrepriseById(int id);
}
