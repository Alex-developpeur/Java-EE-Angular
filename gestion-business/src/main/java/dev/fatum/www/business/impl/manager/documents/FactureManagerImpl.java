package dev.fatum.www.business.impl.manager.documents;

import dev.fatum.www.business.impl.manager.AbstractManagerImpl;
import dev.fatum.www.business.impl.manager.GroupesManagerImpl;
import dev.fatum.www.business.manager.documents.FactureManager;
import dev.fatum.www.model.entities.documents.Facture;
import dev.fatum.www.model.entities.documents.LigneFacture;
import dev.fatum.www.model.exceptions.ResourceNotCompleteException;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Manager des beans du package {@link Facture}.
 *
 * @author LEONARD
 */
@Service
public class FactureManagerImpl extends AbstractManagerImpl implements FactureManager {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    /**
     * Enregistre la {@link Facture}
     *
     * @param facture objet {@link Facture}
     * @return Void
     */
    @Override
    public void saveFacture(Facture facture) {
        List<LigneFacture> ligneFactureList = facture.getLigneFactureList();
        if(ligneFactureList == null || ligneFactureList.isEmpty()) {
            logger.error("##### FactureManagerImpl : erreur, aucune ligne de facture trouvées.");
            throw new ResourceNotCompleteException("Aucune ligne de facture trouvées, échec de l'enregistrement.");
        } else {
            for(LigneFacture ligneFacture : ligneFactureList) {
                ligneFacture.setFacture(facture);
            }
        }
        getDaoFactory().getFactureDao().save(facture);
        logger.info("##### FactureManagerImpl : facture enregistrée.");
    }

    /**
     * Renvoie toutes les {@link Facture}
     *
     * @return List
     */
    @Override
    public List<Facture> findAllFacturesDesc() {
        List<Facture> factureList;
        factureList = getDaoFactory().getFactureDao().findAllByOrderByIdDesc();

        if(factureList == null) {
            logger.error("##### FactureManagerImpl : erreur, liste des facture non trouvée.");
            throw new ResourceNotFoundException("La liste des factures est INTROUVABLE.");
        }
        logger.info("##### FactureManagerImpl : liste des factures trouvée.");
        return factureList;
    }

    /**
     * Renvoie la facture demandée
     *
     * @param id l'identifiant de la facture
     * @return {@link Facture}
     * @throws ResourceNotFoundException si facture est null
     */
    @Override
    public Facture findFactureById(int id) {
        Facture facture = getDaoFactory().getFactureDao().findById(id);
        if(facture == null) {
            logger.error("##### FactureManagerImpl : facture " + id + " non trouvée.");
            throw new ResourceNotFoundException("Le facture avec l'id " + id + " est INTROUVABLE.");
        } else {
            logger.info("##### FactureManagerImpl : facture " + id + " trouvée.");
            return facture;
        }
    }

    /**
     * Edite la {@link Facture}
     *
     * @param facture objet {@link Facture}
     * @return Void
     */
    @Override
    public void updateFacture(Facture facture) {
        List<LigneFacture> ligneFactureList = facture.getLigneFactureList();
        for(LigneFacture ligneFacture : ligneFactureList) {
            ligneFacture.setFacture(facture);
        }
        getDaoFactory().getFactureDao().save(facture);
        logger.info("##### FactureManagerImpl : facture " + facture.getId() + " éditée.");
    }

    /**
     * Supprime la {@link Facture} d'identifiant {@code id}
     *
     * @param id identifiant de la {@link Facture}
     *
     * @return Void
     */
    @Override
    public void deleteFactureById(int id) {
        getDaoFactory().getFactureDao().deleteById(id);
        logger.info("##### FactureManagerImpl : facture " + id + " supprimée.");
    }
}
