package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.model.entities.Profil;
import dev.fatum.www.model.entities.Utilisateur;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import dev.fatum.www.webapp.bean.AuthenticationBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Ressource REST pour les connexions.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class BasicAuthController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(BasicAuthController.class);

    /**
     * Effectue une connexion sécurisé.
     *
     * @return {@link AuthenticationBean}
     */
    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        logger.info("##### @Get : profil authentifié.");
        return new AuthenticationBean("Vous êtes authentifié.");
    }

    /**
     * Renvoie le {@link Profil} authentifié {@code auth}
     *
     * @param auth identifiant du {@link Authentication}
     * @return {@link Profil}
     * @throws ResourceNotFoundException Si le {@link Profil} n'a pas été trouvé
     */
    @GetMapping(path = "/connection")
    public Profil connection(Authentication auth) {
        Profil profilAuth = getManagerFactory().getProfilManager().getProfilByEmail(auth.getName());
        if (profilAuth == null) {
            logger.error("##### BasicAuthController @Get : profil non trouvé.");
            throw new ResourceNotFoundException("Le profil est INTROUVABLE.");
        }
        logger.info("##### BasicAuthController @Get : profil connecté.");
        return profilAuth;
    }

    /**
     * Enregistre le nouvel {@link Utilisateur}
     *
     * @param utilisateur objet {@link Utilisateur}
     * @return Void
     */
    @PostMapping(value = "/nouvel-utilisateur")
    public void nouvelUtilisateur(@Valid @RequestBody Utilisateur utilisateur) throws ResourceAlreadyExists {
        getManagerFactory().getUtilisateurManager().saveUtilisateur(utilisateur);
        logger.info("##### BasicAuthController @Post : nouvel utilisateur enregistré.");
    }

}