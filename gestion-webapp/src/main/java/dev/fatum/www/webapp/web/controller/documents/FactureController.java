package dev.fatum.www.webapp.web.controller.documents;

import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.documents.Facture;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import dev.fatum.www.webapp.web.controller.AbstractController;
import dev.fatum.www.webapp.web.controller.GroupesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Ressource REST pour les {@link Entreprise}.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class FactureController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(GroupesController.class);

    /**
     * Renvoie toutes les {@link Facture}
     *
     * @return List
     */
    @RequestMapping(value="/ListeFactures", method = RequestMethod.GET)
    public List<Facture> listeDesFactures() {
        List<Facture> factureList;
        factureList = getManagerFactory().getFactureManager().findAllFacturesDesc();
        if(factureList == null) {
            logger.error("##### FactureController @Get : erreur, liste des factures non trouvée.");
            throw new ResourceNotFoundException("La liste des factures est INTROUVABLE.");
        }
        logger.info("##### FactureController @Get : liste des factures trouvée.");
        return factureList;
    }

    /**
     * Renvoie la {@link Facture} d'identifiant {@code id}
     *
     * @param id identifiant de la {@link Facture}
     * @return {@link Facture}
     */
    @GetMapping(value = "/Facture/{id}")
    public Facture afficherUneFacture(@PathVariable int id) {
        Facture facture = getManagerFactory().getFactureManager().findFactureById(id);
        if (facture == null) {
            logger.error("##### FactureController @Get : facture " + id + " non trouvée.");
            throw new ResourceNotFoundException("Le facture avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### FactureController @Get : facture " + id + " trouvée.");
        return facture;
    }

    /**
     * Enregistre la {@link Facture}
     *
     * @param facture objet {@link Facture}
     *
     * @return Void
     */
    @PostMapping(value = "/Facture")
    public void ajouterUneFacture(@Valid @RequestBody Facture facture) {
        getManagerFactory().getFactureManager().saveFacture(facture);
        logger.info("##### FactureController @Post : facture enregistrée.");
    }


    /**
     * Edite la {@link Facture}
     *
     * @param facture objet {@link Facture}
     * @return Void
     */
    @PutMapping(value = "/Facture")
    public void modifierUneFacture(@Valid @RequestBody Facture facture) {
        getManagerFactory().getFactureManager().updateFacture(facture);
        logger.info("##### FactureController @Put : facture " + facture.getId() + " éditée.");
    }

    /**
     * Supprime la {@link Facture} d'identifiant {@code id}
     *
     * @param id identifiant de la {@link Facture}
     * @return Void
     */
    @DeleteMapping(value = "/Facture/{id}")
    public void supprimerUneFacture(@PathVariable int id) {
        getManagerFactory().getFactureManager().deleteFactureById(id);
        logger.info("##### FactureController @Delete : facture " + id + " supprimée.");
    }
}

