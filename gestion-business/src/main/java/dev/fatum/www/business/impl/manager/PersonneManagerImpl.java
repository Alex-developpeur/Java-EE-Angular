package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.PersonneManager;
import dev.fatum.www.model.entities.Coordonnees;
import dev.fatum.www.model.entities.Personne;
import dev.fatum.www.model.exceptions.ResourceNotCompleteException;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager des beans du package {@link Personne}.
 *
 * @author LEONARD
 */

@Service
public class PersonneManagerImpl extends AbstractManagerImpl implements PersonneManager {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    /**
     * Enregistre la {@link Personne}
     *
     * @param personne objet {@link Personne}
     * @return Void
     */
    @Override
    public void savePersonne(Personne personne) {
        List<Personne> personneList = new ArrayList<>();
        personneList.add(personne);
        Coordonnees coordonnees = personne.getCoordonnees();
        if(coordonnees == null) {
            logger.error("##### PersonneManagerImpl : erreur, coordonnees de la personnes non trouvées.");
            throw new ResourceNotCompleteException(
                    "Les coordonnées de la personne sont manquantes, échec de l'enregistrement.");
        } else {
            coordonnees.setPersonneList(personneList);
            getDaoFactory().getPersonneDao().save(personne);
            logger.info("##### PersonneManagerImpl : personne enregistrée.");
        }
    }

    /**
     * Renvoie toutes les {@link Personne}
     *
     * @return List
     * @throws ResourceNotFoundException if personne is null
     */
    @Override
    public List<Personne> findAllPersonnesDesc() {
        List<Personne> personneList;
        personneList = getDaoFactory().getPersonneDao().findAllByOrderByIdDesc();

        if(personneList == null) {
            logger.error("##### PersonneManagerImpl : erreur, liste des personnes non trouvée.");
            throw new ResourceNotFoundException("La liste des personnes est INTROUVABLE.");
        }
        logger.info("##### PersonneManagerImpl : liste des personnes trouvée.");
        return personneList;
    }

    /**
     * Renvoie la personne demandée
     *
     * @param id l'identifiant de la personne
     * @return {@link Personne}
     * @throws ResourceNotFoundException si personne est null
     */
    @Override
    public Personne findPersonneById(int id) {
        Personne personne = getDaoFactory().getPersonneDao().findById(id);
        if(personne == null) {
            logger.error("##### PersonneManagerImpl : personne " + id + " non trouvée.");
            throw new ResourceNotFoundException("La personne avec l'id " + id + " est INTROUVABLE.");
        } else {
            logger.info("##### PersonneManagerImpl : personne " + id + " trouvée.");
            return personne;
        }
    }

    /**
     * Edite l'{@link Personne}
     *
     * @param personne objet {@link Personne}
     * @return Void
     */
    @Override
    public void updatePersonne(Personne personne) {
        List<Personne> personneList = new ArrayList<>();
        personneList.add(personne);
        Coordonnees coordonnees = personne.getCoordonnees();
        if(coordonnees == null) {
            logger.error("##### PersonneManagerImpl : erreur, coordonnees de la personnes non trouvées.");
            throw new ResourceNotCompleteException(
                    "Les coordonnées de la personne sont manquantes, échec de l'édition.");
        } else {
            coordonnees.setPersonneList(personneList);
            getDaoFactory().getPersonneDao().save(personne);
            logger.info("##### PersonneManagerImpl : personne " + personne.getId() + " éditée.");
        }
    }

    /**
     * Supprime la {@link Personne} d'identifiant {@code id}
     *
     * @param id identifiant de la {@link Personne}
     *
     * @return Void
     */
    @Override
    public void deletePersonneById(int id) {
        getDaoFactory().getPersonneDao().deleteById(id);
        logger.info("##### PersonneManagerImpl : personne " + id + " supprimée.");
    }
}
