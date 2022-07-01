package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.model.entities.Administrateur;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Ressource REST pour les {@link Administrateur}.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class AdministrateurController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(GroupesController.class);

    /**
     * Renvoie tous les {@link Administrateur}
     *
     * @return List
     */
    @RequestMapping(value="/Administrateurs", method = RequestMethod.GET)
    public List<Administrateur> listeDesAdministrateurs() {
        List<Administrateur> listeAdministrateur;
        listeAdministrateur = getManagerFactory().getAdministrateurManager().findAllAdministrateursDesc();
        if(listeAdministrateur == null) {
            logger.error("##### AdministrateurController @Get : erreur, liste des administrateurs non trouvée.");
            throw new ResourceNotFoundException("La liste des administrateurs est INTROUVABLE.");
        }
        logger.info("##### AdministrateurController @Get : liste des administrateurs trouvée.");
        return listeAdministrateur;
    }

    /**
     * Renvoie l'{@link Administrateur} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Administrateur}
     * @return {@link Administrateur}
     * @throws ResourceNotFoundException Si l'{@link Administrateur} n'a pas été trouvé
     */
    @GetMapping(value = "/Administrateur/{id}")
    public Administrateur afficherUnAdministrateur(@PathVariable int id) {
        Administrateur administrateur = getManagerFactory().getAdministrateurManager().findAdministrateurById(id);
        if (administrateur == null) {
            logger.error("##### AdministrateurController @Get : administrateur " + id + " non trouvé.");
            throw new ResourceNotFoundException("L'administrateurs avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### AdministrateurController @Get : administrateur " + id + " trouvé.");
        return administrateur;
    }

    /**
     * Enregistre l'{@link Administrateur}
     *
     * @param administrateur objet {@link Administrateur}
     *
     * @return Void
     */
    @PostMapping(value = "/Administrateur")
    public void ajouterUnAdministrateur(@Valid @RequestBody Administrateur administrateur) throws ResourceAlreadyExists {
        getManagerFactory().getAdministrateurManager().saveAdministrateur(administrateur);
        logger.info("##### AdministrateurController @Post : administrateur enregistré.");
    }

    /**
     * Edite l'{@link Administrateur}
     *
     * @param administrateur objet {@link Administrateur}
     * @return Void
     */
    @PutMapping(value = "/Administrateur")
    public void modifierUnAdministrateur(@Valid @RequestBody Administrateur administrateur) throws ResourceAlreadyExists {
        getManagerFactory().getAdministrateurManager().updateAdministrateur(administrateur);
        logger.info("##### AdministrateurController @Put : administrateur " + administrateur.getId() + " édité.");
    }

    /**
     * Supprime l'{@link Administrateur} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Administrateur}
     * @return Void
     */
    @DeleteMapping(value = "/Administrateur/{id}")
    public void supprimerUnAdministrateur(@PathVariable int id) {
        getManagerFactory().getAdministrateurManager().deleteAdministrateurById(id);
        logger.info("##### AdministrateurController @Delete : administrateur " + id + " supprimé.");
    }

    /**
     * Supprime l'{@link Administrateur} d'identifiant {@code id}
     *
     * @return Void
     */
    @RequestMapping(value="/Dashboard", method = RequestMethod.GET)
    public List<Map<String, String>> donneesTableauDeBord() {
        logger.info("##### AdministrateurController @Get : Dashboard.");
        return getManagerFactory().getAdministrateurManager().totauxDonnees();
    }

}
