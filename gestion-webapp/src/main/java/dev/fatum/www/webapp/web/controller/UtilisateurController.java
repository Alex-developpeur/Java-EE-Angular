package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.model.entities.Utilisateur;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Ressource REST pour les {@link Utilisateur}.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1")
public class UtilisateurController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    /**
     * Renvoie tous les {@link Utilisateur}
     *
     * @return List
     */
    @RequestMapping(value="/Utilisateurs", method = RequestMethod.GET)
    public List<Utilisateur> listeDesUtilisateurs() {
        List<Utilisateur> listeUtilisateur;
        listeUtilisateur = getManagerFactory().getUtilisateurManager().findAllUtilisateursDesc();
        if(listeUtilisateur == null) {
            logger.error("##### UtilisateurController @Get : erreur, liste des utilisateurs non trouvé.");
            throw new ResourceNotFoundException("La liste des utilisateurs est INTROUVABLE.");
        }
        logger.info("##### UtilisateurController @Get : liste des utilisateurs trouvé.");
        return listeUtilisateur;
    }

    /**
     * Renvoie l'{@link Utilisateur} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Utilisateur}
     * @return {@link Utilisateur}
     * @throws ResourceNotFoundException Si le {@link Utilisateur} n'a pas été trouvé
     */
    @GetMapping(value = "/Utilisateur/{id}")
    public Utilisateur afficherUnUtilisateur(@PathVariable int id) {
        Utilisateur utilisateur = getManagerFactory().getUtilisateurManager().findUtilisateurById(id);
        if (utilisateur == null) {
            logger.error("##### UtilisateurController @Get : utilisateur " + id + " non trouvé.");
            throw new ResourceNotFoundException("L'utilisateur avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### UtilisateurController @Get : utilisateur " + id + " trouvé.");
        return utilisateur;
    }

    /**
     * Enregistre l'{@link Utilisateur}
     *
     * @param utilisateur objet {@link Utilisateur}
     * @return Void
     */
    @PostMapping(value = "/Utilisateur")
    public void ajouterUnUtilisateur(@Valid @RequestBody Utilisateur utilisateur) throws ResourceAlreadyExists {
        getManagerFactory().getUtilisateurManager().saveUtilisateur(utilisateur);
        logger.info("##### UtilisateurController @Post : utilisateur enregistré.");
    }

    /**
     * Edite l'{@link Utilisateur}
     *
     * @param utilisateur objet {@link Utilisateur}
     * @return Void
     */
    @PutMapping(value = "/Utilisateur")
    public void modifierUnUtilisateur(@Valid @RequestBody Utilisateur utilisateur) throws ResourceAlreadyExists {
        getManagerFactory().getUtilisateurManager().updateUtilisateur(utilisateur);
        logger.info("##### UtilisateurController @Put : utilisateur " + utilisateur.getId() + " édité.");
    }

    /**
     * Supprime l'{@link Utilisateur} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Utilisateur}
     * @return Void
     */
    @DeleteMapping(value = "/Utilisateur/{id}")
    public void supprimerUnUtilisateur(@PathVariable int id) {
        getManagerFactory().getUtilisateurManager().deleteUtilisateurById(id);
        logger.info("##### UtilisateurController @Delete : utilisateur " + id + " supprimé.");
    }

}
