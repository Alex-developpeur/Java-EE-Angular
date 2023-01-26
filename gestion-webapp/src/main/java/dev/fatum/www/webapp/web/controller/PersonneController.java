package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.model.entities.Personne;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Ressource REST pour les {@link Personne}.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1")
public class PersonneController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(PersonneController.class);

    /**
     * Renvoie toutes les {@link Personne}
     *
     * @return List
     */
    @RequestMapping(value="/Personnes", method = RequestMethod.GET)
    public List<Personne> listeDesPersonnes() {
        List<Personne> personneList;
        personneList = getManagerFactory().getPersonneManager().findAllPersonnesDesc();
        if(personneList == null) {
            logger.error("##### PersonneController @Get : erreur, liste des Personnes non trouvée.");
            throw new ResourceNotFoundException("La liste des personnes est INTROUVABLE.");
        }
        logger.info("##### PersonneController @Get : liste des personnes trouvée.");
        return personneList;
    }

    /**
     * Renvoie la {@link Personne} d'identifiant {@code id}
     *
     * @param id identifiant de la {@link Personne}
     * @return {@link Personne}
     */
    @GetMapping(value = "/Personne/{id}")
    public Personne afficherUnePersonne(@PathVariable int id) {
        Personne personne = getManagerFactory().getPersonneManager().findPersonneById(id);
        if (personne == null) {
            logger.error("##### PersonneController @Get : personne " + id + " non trouvée.");
            throw new ResourceNotFoundException("La personnes avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### PersonneController @Get : personne " + id + " trouvée.");
        return personne;
    }

    /**
     * Enregistre la {@link Personne}
     *
     * @param personne objet {@link Personne}
     *
     * @return Void
     */
    @PostMapping(value = "/Personne")
    public void ajouterUnePersonne(@Valid @RequestBody Personne personne) {
        getManagerFactory().getPersonneManager().savePersonne(personne);
        logger.info("##### PersonneController @Post : personne enregistrée.");
    }

    /**
     * Edite la {@link Personne}
     *
     * @param personne objet {@link Personne}
     * @return Void
     */
    @PutMapping(value = "/Personne")
    public void modifierUnePersonne(@Valid @RequestBody Personne personne) {
        getManagerFactory().getPersonneManager().updatePersonne(personne);
        logger.info("##### PersonneController @Put : personne " + personne.getId() + " éditée.");
    }

    /**
     * Supprime la {@link Personne} d'identifiant {@code id}
     *
     * @param id identifiant de la {@link Personne}
     * @return Void
     */
    @DeleteMapping(value = "/Personne/{id}")
    public void supprimerUnePersonne(@PathVariable int id) {
        getManagerFactory().getPersonneManager().deletePersonneById(id);
        logger.info("##### PersonneController @Delete : personne " + id + " supprimée.");
    }

}
