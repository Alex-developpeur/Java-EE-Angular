package dev.fatum.www.business.manager;

import dev.fatum.www.model.entities.Personne;

import java.util.List;

public interface PersonneManager {

    void savePersonne(Personne personne);

    List<Personne> findAllPersonnesDesc();

    Personne findPersonneById(int id);

    void updatePersonne(Personne personne);

    void deletePersonneById(int id);
}
