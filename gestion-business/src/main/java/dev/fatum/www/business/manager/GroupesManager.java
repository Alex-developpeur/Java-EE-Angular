package dev.fatum.www.business.manager;

import dev.fatum.www.model.entities.Groupes;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;

import java.util.List;

public interface GroupesManager {

    void saveGroupe(Groupes groupe) throws ResourceAlreadyExists;

    List<Groupes> findAllGroupesDesc();

    Groupes findGroupeById(int id);

    void updateGroupe(Groupes groupe) throws ResourceAlreadyExists;

    void deleteGroupeById(int id);

}
