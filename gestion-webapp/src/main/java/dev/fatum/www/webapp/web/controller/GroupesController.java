package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.model.entities.Groupes;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Ressource REST pour les {@link Groupes}.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1")
public class GroupesController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(GroupesController.class);
    /**
     * Renvoie tous les {@link Groupes}
     *
     * @return List
     * @throws ResourceNotFoundException Si le {@link Groupes} n'a pas été trouvé
     */
    @GetMapping(value="/Groupes")
    public List<Groupes> listeDesGroupes() {
        List<Groupes> listeGroupe = new ArrayList<>();
        listeGroupe = getManagerFactory().getGroupesManager().findAllGroupesDesc();
        if(listeGroupe == null) {
            logger.error("##### GroupesController @Get : erreur, liste des groupes non trouvé.");
            throw new ResourceNotFoundException("La liste des groupes est INTROUVABLE.");
        }
        logger.info("##### GroupesController @Get : liste des groupes trouvé.");
        return listeGroupe;
    }

    /**
     * Renvoie le {@link Groupes} d'identifiant {@code id}
     *
     * @param id identifiant du {@link Groupes}
     * @return {@link Groupes}
     * @throws ResourceNotFoundException Si le {@link Groupes} n'a pas été trouvé
     */
    @GetMapping(value = "/Groupe/{id}")
    public Groupes afficherUnGroupe(@PathVariable int id) {
        Groupes groupe = getManagerFactory().getGroupesManager().findGroupeById(id);
        if (groupe == null) {
            logger.error("##### GroupesController @Get : groupe " + id + " non trouvé.");
            throw new ResourceNotFoundException("Le groupe avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### GroupesController @Get : groupe " + id + " trouvé.");
        return groupe;
    }

    /**
     * Enregistre le {@link Groupes}
     *
     * @param groupe objet {@link Groupes}
     * @return Void
     */
    @PostMapping(value = "/Groupe")
    public void ajouterUnGroupe(@Valid @RequestBody Groupes groupe) throws ResourceAlreadyExists {
        getManagerFactory().getGroupesManager().saveGroupe(groupe);
        logger.info("##### GroupesController @Post : groupe enregistré.");
    }

    /**
     * Edite le {@link Groupes}
     *
     * @param groupe objet {@link Groupes}
     * @return Void
     */
    @PutMapping(value = "/Groupe")
    public void modifierUnGroupe(@Valid @RequestBody Groupes groupe) throws ResourceAlreadyExists {
        getManagerFactory().getGroupesManager().updateGroupe(groupe);
        logger.info("##### GroupesController @Put : groupe " + groupe.getId() + " édité.");
    }

    /**
     * Supprime le {@link Groupes} d'identifiant {@code id}
     *
     * @param id identifiant du {@link Groupes}
     * @return Void
     */
    @DeleteMapping(value = "/Groupe/{id}")
    public void supprimerUnGroupe(@PathVariable int id) {
        getManagerFactory().getGroupesManager().deleteGroupeById(id);
        logger.info("##### GroupesController @Delete : groupe " + id + " supprimé.");
    }

}
