package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.GroupesManager;
import dev.fatum.www.model.entities.Groupes;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager des beans du package {@link Groupes}.
 *
 * @author LEONARD
 */

@Service
public class GroupesManagerImpl extends AbstractManagerImpl implements GroupesManager {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    /**
     * Enregistre le {@link Groupes}
     *
     * @param groupe objet {@link Groupes}
     * @return Void
     */
    @Override
    public void saveGroupe(Groupes groupe) throws ResourceAlreadyExists {
        if(getDaoFactory().getGroupesDao().findByNom(groupe.getNom()) != null) {
            logger.error("##### GroupesManagerImpl : erreur, groupe non enregistré.");
            throw new ResourceAlreadyExists("Ce groupe existe déjà, veuillez en changer.");
        } else {
            getDaoFactory().getGroupesDao().save(groupe);
            logger.info("##### GroupesManagerImpl : groupe enregistré.");
        }
    }

    /**
     * Renvoie tous les {@link Groupes}
     *
     * @return List
     */
    @Override
    public List<Groupes> findAllGroupesDesc() {
        List<Groupes> groupesList = getDaoFactory().getGroupesDao().findAllByOrderByIdDesc();
        if(groupesList == null) {
            logger.error("##### GroupesManagerImpl : erreur, liste des groupes non trouvée.");
            throw new ResourceNotFoundException("La liste des groupes est INTROUVABLE.");
        }
        logger.info("##### GroupesManagerImpl : liste des groupes trouvée.");
        return groupesList;
    }

    /**
     * Renvoie le groupe demandé
     *
     * @param id l'identifiant du groupe
     * @return {@link Groupes}
     * @throws ResourceNotFoundException si groupe est null
     */
    @Override
    public Groupes findGroupeById(int id) {
        Groupes groupe = getDaoFactory().getGroupesDao().findById(id);
        if (groupe == null) {
            logger.error("##### GroupesManagerImpl : groupe " + id + " non trouvé.");
            throw new ResourceNotFoundException("Le groupe avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### GroupesManagerImpl : groupe " + id + " trouvé.");
        return groupe;
    }

    /**
     * Edite le {@link Groupes}
     *
     * @param groupe objet {@link Groupes}
     * @return Void
     */
    @Override
    public void updateGroupe(Groupes groupe) throws ResourceAlreadyExists {
        if(getDaoFactory().getGroupesDao().nomDuplicated(groupe.getNom(), groupe.getId()) != null) {
            logger.error("##### GroupesManagerImpl : erreur, groupe non édité.");
            throw new ResourceAlreadyExists("Ce groupe existe déjà, veuillez en changer.");
        } else {
            getDaoFactory().getGroupesDao().save(groupe);
            logger.info("##### GroupesManagerImpl : groupe " + groupe.getId() + " édité.");
        }
    }

    /**
     * Supprime le {@link Groupes} d'identifiant {@code id}
     *
     * @param id identifiant du {@link Groupes}
     * @return Void
     */
    @Override
    public void deleteGroupeById(int id) {
        getDaoFactory().getGroupesDao().deleteById(id);
        logger.info("##### GroupesManagerImpl : groupe " + id + " supprimé.");
    }

}
