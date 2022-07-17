package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.EntrepriseManager;
import dev.fatum.www.model.entities.Coordonnees;
import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.documents.NumerosDocuments;
import dev.fatum.www.model.exceptions.ResourceNotCompleteException;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Manager des beans du package {@link Entreprise}.
 *
 * @author LEONARD
 */

@Service
public class EntrepriseManagerImpl extends AbstractManagerImpl implements EntrepriseManager {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    /**
     * Enregistre l'{@link Entreprise}
     *
     * @param entreprise objet {@link Entreprise}
     * @return Void
     */
    @Override
    public void saveEntreprise(Entreprise entreprise) {
        List<Coordonnees> coordonneesList = entreprise.getCoordonneesList();
        if(coordonneesList == null || coordonneesList.isEmpty()) {
            logger.error("##### EntrepriseManagerImpl : erreur, coordonnees de l'entreprise non trouvées.");
            throw new ResourceNotCompleteException(
                    "Les coordonnées de l'entreprise sont manquantes, échec de l'enregistrement.");
        } else {
            Coordonnees coordonnees = coordonneesList.get(0);
            coordonnees.setEntreprise(entreprise);
            getDaoFactory().getEntrepriseDao().save(entreprise);
            logger.info("##### EntrepriseManagerImpl : entreprise enregistrée.");
            if(entreprise.getProfil() != null) {
                NumerosDocuments numerosDocuments = new NumerosDocuments(entreprise.getId());
                getDaoFactory().getNumerosDocumentsDao().save(numerosDocuments);
            }
        }
    }

    /**
     * Renvoie toutes les {@link Entreprise}
     *
     * @return List
     * @throws ResourceNotFoundException if entreprise is null
     */
    @Override
    public List<Entreprise> findAllEntreprisesDesc() {
        List<Entreprise> entrepriseList;
        entrepriseList = getDaoFactory().getEntrepriseDao().findAllByOrderByIdDesc();

        if(entrepriseList == null) {
            logger.error("##### EntrepriseManagerImpl : erreur, liste des Entreprises non trouvée.");
            throw new ResourceNotFoundException("La liste des Entreprises est INTROUVABLE.");
        }
        logger.info("##### EntrepriseManagerImpl : liste des Entreprises trouvée.");
        return entrepriseList;
    }

    /**
     * Renvoie l'entreprise demandée
     *
     * @param id l'identifiant de l'entreprise
     * @return {@link Entreprise}
     * @throws ResourceNotFoundException si entreprise est null
     */
    @Override
    public Entreprise findEntrepriseById(int id) {
        Entreprise entreprise = getDaoFactory().getEntrepriseDao().findById(id);
        if(entreprise == null) {
            logger.error("##### EntrepriseManagerImpl : entreprise " + id + " non trouvée.");
            throw new ResourceNotFoundException("L'entreprise avec l'id " + id + " est INTROUVABLE.");
        } else {
            logger.info("##### EntrepriseManagerImpl : entreprise " + id + " trouvée.");
            return entreprise;
        }
    }

    /**
     * Edite l'{@link Entreprise}
     *
     * @param entreprise objet {@link Entreprise}
     * @return Void
     */
    @Override
    public void updateEntreprise(Entreprise entreprise) {
        List<Coordonnees> coordonneesList = entreprise.getCoordonneesList();
        if(coordonneesList == null || coordonneesList.isEmpty()) {
            logger.error("##### EntrepriseManagerImpl : erreur, coordonnees de l'entreprise non trouvées.");
            throw new ResourceNotCompleteException(
                    "Les coordonnées de l'entreprise sont manquantes, échec de l'édition.");
        } else {
            Coordonnees coordonnees = coordonneesList.get(0);
            coordonnees.setEntreprise(entreprise);
            getDaoFactory().getEntrepriseDao().save(entreprise);
            logger.info("##### EntrepriseManagerImpl : entreprise " + entreprise.getId() + " éditée.");
        }
    }

    /**
     * Supprime l'{@link Entreprise} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Entreprise}
     *
     * @return Void
     */
    @Override
    public void deleteEntrepriseById(int id) {
        getDaoFactory().getEntrepriseDao().deleteById(id);
        logger.info("##### EntrepriseManagerImpl : entreprise " + id + " supprimée.");
    }
}
