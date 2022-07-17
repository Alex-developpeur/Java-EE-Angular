package dev.fatum.www.business.manager;

import dev.fatum.www.model.entities.Administrateur;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;

import java.util.List;
import java.util.Map;

public interface AdministrateurManager {

    void saveAdministrateur(Administrateur administrateur) throws ResourceAlreadyExists;

    List<Administrateur> findAllAdministrateursDesc();

    Administrateur findAdministrateurById(int id);

    void updateAdministrateur(Administrateur administrateur) throws ResourceAlreadyExists;

    void deleteAdministrateurById(int id);

    List<Map<String, String>> totauxDonnees();
}
