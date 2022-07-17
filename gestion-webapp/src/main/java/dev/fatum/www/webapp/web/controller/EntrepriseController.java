package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.business.util.files.FileUploadUtil;
import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Ressource REST pour les {@link Entreprise}.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class EntrepriseController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(GroupesController.class);

    /**
     * Renvoie toutes les {@link Entreprise}
     *
     * @return List
     */
    @RequestMapping(value="/Entreprises", method = RequestMethod.GET)
    public List<Entreprise> listeDesEntreprises() {
        List<Entreprise> entrepriseList;
        entrepriseList = getManagerFactory().getEntrepriseManager().findAllEntreprisesDesc();
        if(entrepriseList == null) {
            logger.error("##### EntrepriseController @Get : erreur, liste des Entreprises non trouvée.");
            throw new ResourceNotFoundException("La liste des Entreprises est INTROUVABLE.");
        }
        logger.info("##### EntrepriseController @Get : liste des Entreprises trouvée.");
        return entrepriseList;
    }

    /**
     * Renvoie l'{@link Entreprise} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Entreprise}
     * @return {@link Entreprise}
     */
    @GetMapping(value = "/Entreprise/{id}")
    public Entreprise afficherUneEntreprise(@PathVariable int id) {
        Entreprise entreprise = getManagerFactory().getEntrepriseManager().findEntrepriseById(id);
        if (entreprise == null) {
            logger.error("##### EntrepriseController @Get : entreprise " + id + " non trouvée.");
            throw new ResourceNotFoundException("L'entreprises avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### EntrepriseController @Get : entreprise " + id + " trouvée.");
        return entreprise;
    }

    /**
     * Enregistre l'{@link Entreprise}
     *
     * @param entreprise objet {@link Entreprise}
     *
     * @return Void
     */
    @PostMapping(value = "/Entreprise")
    public void ajouterUneEntreprise(@Valid @RequestBody Entreprise entreprise) {
        getManagerFactory().getEntrepriseManager().saveEntreprise(entreprise);
        logger.info("##### EntrepriseController @Post : entreprise enregistrée.");
    }

    /**
     * Edite l'{@link Entreprise}
     *
     * @param entreprise objet {@link Entreprise}
     * @return Void
     */
    @PutMapping(value = "/Entreprise")
    public void modifierUneEntreprise(@Valid @RequestBody Entreprise entreprise) {
        getManagerFactory().getEntrepriseManager().updateEntreprise(entreprise);
        logger.info("##### EntrepriseController @Put : entreprise " + entreprise.getId() + " éditée.");
    }

    /**
     * Supprime l'{@link Entreprise} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Entreprise}
     * @return Void
     */
    @DeleteMapping(value = "/Entreprise/{id}")
    public void supprimerUneEntreprise(@PathVariable int id) {
        getManagerFactory().getEntrepriseManager().deleteEntrepriseById(id);
        logger.info("##### EntrepriseController @Delete : entreprise " + id + " supprimée.");
    }

    /**
     * Upload l'image
     *
     * @param multipartFile fichier image eu format {@link MultipartFile}
     * @param entrepriseId identifiant de l'{@link Entreprise}
     *
     * @return Void
     */
    @PostMapping(value = "/UploadImage/{entrepriseId}")
    public void saveUser(@RequestParam("image") MultipartFile multipartFile, @PathVariable int entrepriseId) throws IOException {
        if(multipartFile.getOriginalFilename() != null) {
            String fileName = "logo.png";
            String uploadDir = "documents/entreprise-" + entrepriseId + "/img/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            logger.info("##### EntrepriseController @Delete : upload de l'image.");
        }
    }

}
