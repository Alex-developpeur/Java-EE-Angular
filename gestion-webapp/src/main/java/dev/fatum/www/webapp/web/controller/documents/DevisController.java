package dev.fatum.www.webapp.web.controller.documents;

import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.Profil;
import dev.fatum.www.model.entities.documents.Devis;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import dev.fatum.www.webapp.web.controller.AbstractController;
import dev.fatum.www.webapp.web.controller.GroupesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Ressource REST pour les {@link Entreprise}.
 *
 * @author LEONARD
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class DevisController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(GroupesController.class);

    /**
     * Renvoie tous les {@link Devis}
     *
     * @return List
     */
    @GetMapping(value="/ListeDevis")
    public List<Devis> listeDesDevis() {
        List<Devis> devisList;
        devisList = getManagerFactory().getDevisManager().findAllDevisDesc();
        if(devisList == null) {
            logger.error("##### DevisController @Get : erreur, liste des devis non trouvée.");
            throw new ResourceNotFoundException("La liste des devis est INTROUVABLE.");
        }
        logger.info("##### DevisController @Get : liste des devis trouvée.");
        return devisList;
    }

    /**
     * Renvoie le {@link Devis} d'identifiant {@code id}
     *
     * @param id identifiant du {@link Devis}
     * @return {@link Devis}
     */
    @GetMapping(value = "/Devis/{id}")
    public Devis afficherUnDevis(@PathVariable int id) {
        Devis devis = getManagerFactory().getDevisManager().findDevisById(id);
        if (devis == null) {
            logger.error("##### DevisController @Get : devis " + id + " non trouvé.");
            throw new ResourceNotFoundException("Le devis avec l'id " + id + " est INTROUVABLE.");
        }
        logger.info("##### DevisController @Get : devis " + id + " trouvé.");
        return devis;
    }

    /**
     * Enregistre le {@link Devis}
     *
     * @param devis objet {@link Devis}
     *
     * @return Void
     */
    @PostMapping(value = "/Devis")
    public void ajouterUnDevis(@Valid @RequestBody Devis devis) {
        getManagerFactory().getDevisManager().saveDevis(devis);
        logger.info("##### DevisController @Post : devis enregistré.");
    }


    /**
     * Edite le {@link Devis}
     *
     * @param devis objet {@link Devis}
     * @return Void
     */
    @PutMapping(value = "/Devis")
    public void modifierUnDevis(@Valid @RequestBody Devis devis) {
        getManagerFactory().getDevisManager().updateDevis(devis);
        logger.info("##### DevisController @Put : devis " + devis.getId() + " édité.");
    }

    /**
     * Supprime le {@link Devis} d'identifiant {@code id}
     *
     * @param id identifiant du {@link Devis}
     * @return Void
     */
    @DeleteMapping(value = "/Devis/{id}")
    public void supprimerUnDevis(@PathVariable int id) {
        getManagerFactory().getDevisManager().deleteDevisById(id);
        logger.info("##### DevisController @Delete : devis " + id + " supprimé.");
    }

    /**
     * Renvoie le devis au format PDF
     *
     * @param user email du {@link Profil}
     * @param entrepriseId identifiant de l'{@link Entreprise}
     * @param numeroDevis numero du {@link Devis}
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping(value = "/ViewPdf/{user}/{entrepriseId}/{numeroDevis}")
    public ResponseEntity<Resource> viewPdf(@PathVariable String user, @PathVariable int entrepriseId, @PathVariable String numeroDevis) throws Exception
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName().equals(user)) {
            String fileName = "documents/entreprise-" + entrepriseId + "/devis/" + numeroDevis + ".pdf";
            File file = new File(fileName);
            if(file.exists()) {
                HttpHeaders header = new HttpHeaders();
                header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);
                Path path = Paths.get(file.getAbsolutePath());
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
                logger.info("##### DevisController @Get : pdf devis trouvé.");
                return ResponseEntity.ok()
                        .headers(header)
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType("application/pdf"))
                        .body(resource);
            }
        }
        return null;
    }
}
