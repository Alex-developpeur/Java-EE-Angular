package dev.fatum.www.business.impl.manager.documents;

import dev.fatum.www.business.impl.manager.AbstractManagerImpl;
import dev.fatum.www.business.impl.manager.GroupesManagerImpl;
import dev.fatum.www.business.manager.documents.DevisManager;
import dev.fatum.www.business.util.pdf.EditionDocument;
import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.Personne;
import dev.fatum.www.model.entities.Utilisateur;
import dev.fatum.www.model.entities.documents.Devis;
import dev.fatum.www.model.entities.documents.LigneDevis;
import dev.fatum.www.model.entities.documents.NumerosDocuments;
import dev.fatum.www.model.exceptions.ResourceNotCompleteException;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Manager des beans du package {@link Devis}.
 *
 * @author LEONARD
 */
@Service
public class DevisManagerImpl extends AbstractManagerImpl implements DevisManager {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    /**
     * Enregistre le {@link Devis}
     *
     * @param devis objet {@link Devis}
     * @return Void
     */
    @Override
    public void saveDevis(Devis devis) {
        List<LigneDevis> ligneDevisList = devis.getLigneDevisList();
        if(ligneDevisList == null || ligneDevisList.isEmpty()) {
            logger.error("##### DevisManagerImpl : erreur, aucune ligne de devis trouvées.");
            throw new ResourceNotCompleteException("Aucune ligne de devis trouvées, échec de l'enregistrement.");
        } else {
            for (LigneDevis ligneDevis : ligneDevisList) {
                ligneDevis.setDevis(devis);
            }
            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date(System.currentTimeMillis());
            devis.setDateEmission(formatter.format(date));
            devis.setEtatDevis("En attente");
            formatter= new SimpleDateFormat("yyyy");
            devis.setNumeroDevis("D" + formatter.format(date) + "-" +  getNumeroDevis());

            getDaoFactory().getDevisDao().save(devis);
            logger.info("##### DevisManagerImpl : devis enregistré.");

            if(devis.getPersonne() != null) {
                int id = devis.getPersonne().getId();
                Personne personne = getDaoFactory().getPersonneDao().findById(id);
                devis.setPersonne(personne);
            } else {
                int id = devis.getEntreprise().getId();
                Entreprise entreprise = getDaoFactory().getEntrepriseDao().findById(id);
                devis.setEntreprise(entreprise);
            }
            new EditionDocument(devis);
        }
    }

    /**
     * Renvoie tous les {@link Devis}
     *
     * @return List
     */
    @Override
    public List<Devis> findAllDevisDesc() {
        List<Devis> devisList;
        devisList = getDaoFactory().getDevisDao().findAllByOrderByIdDesc();

        if(devisList == null) {
            logger.error("##### DevisManagerImpl : erreur, liste des devis non trouvée.");
            throw new ResourceNotFoundException("La liste des devis est INTROUVABLE.");
        }
        logger.info("##### DevisManagerImpl : liste des devis trouvée.");
        return devisList;
    }

    /**
     * Renvoie le devis demandé
     *
     * @param id l'identifiant du devis
     * @return {@link Devis}
     * @throws ResourceNotFoundException si devis est null
     */
    @Override
    public Devis findDevisById(int id) {
        Devis devis = getDaoFactory().getDevisDao().findById(id);
        if(devis == null) {
            logger.error("##### DevisManagerImpl : devis " + id + " non trouvé.");
            throw new ResourceNotFoundException("Le devis avec l'id " + id + " est INTROUVABLE.");
        } else {
            logger.info("##### DevisManagerImpl : devis " + id + " trouvé.");
            return devis;
        }
    }

    /**
     * Edite le {@link Devis}
     *
     * @param devis objet {@link Devis}
     * @return Void
     */
    @Override
    public void updateDevis(Devis devis) {
        List<LigneDevis> ligneDevisList = devis.getLigneDevisList();
        for(LigneDevis ligneDevis : ligneDevisList) {
            ligneDevis.setDevis(devis);
        }
        getDaoFactory().getDevisDao().save(devis);
        logger.info("##### DevisManagerImpl : devis " + devis.getId() + " édité.");
    }

    /**
     * Supprime le {@link Devis} d'identifiant {@code id}
     *
     * @param id identifiant de le {@link Devis}
     *
     * @return Void
     */
    @Override
    public void deleteDevisById(int id) {
        getDaoFactory().getDevisDao().deleteById(id);
        logger.info("##### DevisManagerImpl : devis " + id + " supprimé.");
    }

    String getNumeroDevis() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Utilisateur utilisateur = getDaoFactory().getUtilisateurDao().findByEmail(authentication.getName());
            Integer id = utilisateur.getEntrepriseList().get(0).getId();
            NumerosDocuments numerosDocuments = getDaoFactory().getNumerosDocumentsDao().findByEntrepriseId(id);
            numerosDocuments.setNumeroDevis(numerosDocuments.getNumeroDevis() + 1);
            getDaoFactory().getNumerosDocumentsDao().save(numerosDocuments);
            NumberFormat nf = new DecimalFormat("0000");
            return nf.format(numerosDocuments.getNumeroDevis());
        }
        return null;
    }
}
