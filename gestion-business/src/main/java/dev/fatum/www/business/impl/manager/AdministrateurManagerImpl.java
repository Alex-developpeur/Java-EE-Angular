package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.AdministrateurManager;

import dev.fatum.www.model.entities.Administrateur;
import dev.fatum.www.model.entities.Groupes;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Manager des beans du package {@link Administrateur}.
 *
 * @author LEONARD
 */

@Service
public class AdministrateurManagerImpl extends AbstractManagerImpl implements AdministrateurManager {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    /**
     * Enregistre l'{@link Administrateur}
     *
     * @param administrateur objet {@link Administrateur}
     * @return Void
     */
    @Override
    public void saveAdministrateur(Administrateur administrateur) throws ResourceAlreadyExists {
        if(getDaoFactory().getProfilDao().findByEmail(administrateur.getEmail()) != null) {
            logger.error("##### AdministrateurManagerImpl : erreur, administrateur non enregistré.");
            throw new ResourceAlreadyExists("Cet email existe déjà, veuillez en changer.");
        } else {
            Set<Groupes> groupesSet = new HashSet<>();
            administrateur.setGroupesSet(groupesSet);
            Groupes groupes = getDaoFactory().getGroupesDao().findByNom("ADMIN");
            if(groupes == null) {
                logger.error("##### AdministrateurManagerImpl : erreur, groupe ADMIN non trouvé.");
                throw new ResourceNotFoundException("Le groupe ADMIN est introuvable, échec de l'enregistrement.");
            } else {
                groupesSet.add(groupes);
            }
            administrateur.setMdp(passwordEncoder().encode(administrateur.getMdp()));
            getDaoFactory().getAdministrateurDao().save(administrateur);
            logger.info("##### AdministrateurManagerImpl : administrateur enregistré.");
        }
    }

    /**
     * Renvoie tous les {@link Administrateur}
     *
     * @return List
     */
    @Override
    public List<Administrateur> findAllAdministrateursDesc() {
        List<Administrateur> administrateurList;
        administrateurList = getDaoFactory().getAdministrateurDao().findAllByOrderByIdDesc();

        if(administrateurList == null) {
            logger.error("##### AdministrateurManagerImpl : erreur, liste des administrateurs non trouvée.");
            throw new ResourceNotFoundException("La liste des administrateurs est introuvable.");
        }
        logger.info("##### AdministrateurManagerImpl : liste des administrateurs trouvée.");
        return administrateurList;
    }

    /**
     * Renvoie l'administrateur demandé
     *
     * @param id l'identifiant de l'administrateur
     * @return {@link Administrateur}
     * @throws ResourceNotFoundException si administrateur est null
     */
    @Override
    public Administrateur findAdministrateurById(int id) {
        Administrateur administrateur = getDaoFactory().getAdministrateurDao().findById(id);
        if(administrateur == null) {
            logger.error("##### AdministrateurManagerImpl : administrateur " + id + " non trouvé.");
            throw new ResourceNotFoundException("L'administrateur avec l'id " + id + " est introuvable.");
        } else {
            logger.info("##### AdministrateurManagerImpl : administrateur " + id + " trouvé.");
            return administrateur;
        }
    }

    /**
     * Edite l'{@link Administrateur}
     *
     * @param administrateur objet {@link Administrateur}
     * @return Void
     */
    @Override
    public void updateAdministrateur(Administrateur administrateur) throws ResourceAlreadyExists {
        int id = administrateur.getId();
        Administrateur oldAdministrateur = getDaoFactory().getAdministrateurDao().findById(id);
        if(!administrateur.getMdp().equals(oldAdministrateur.getMdp())) {
            administrateur.setMdp(passwordEncoder().encode(administrateur.getMdp()));
        }
        if(getDaoFactory().getProfilDao().emailDuplicated(administrateur.getEmail(), administrateur.getId()) != null) {
            logger.error("##### AdministrateurManagerImpl : erreur, administrateur non édité.");
            throw new ResourceAlreadyExists("Cet email existe déjà, veuillez en changer.");
        } else if (!administrateur.getGroupesSet().iterator().next().getNom().equals("ADMIN")) {
            logger.error("##### AdministrateurManagerImpl : erreur, groupe ADMIN non trouvé.");
            throw new ResourceNotFoundException("Le groupe ADMIN est introuvable, échec de l'édition.");
        } else {
            getDaoFactory().getAdministrateurDao().save(administrateur);
            logger.info("##### AdministrateurManagerImpl : administrateur " + administrateur.getId() + " édité." + administrateur.getGroupesSet().iterator().next().getNom());
        }
    }

    /**
     * Supprime l'{@link Administrateur} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Administrateur}
     *
     * @return Void
     */
    @Override
    public void deleteAdministrateurById(int id) {
        getDaoFactory().getAdministrateurDao().deleteById(id);
        logger.info("##### AdministrateurManagerImpl : administrateur " + id + " supprimé.");
    }

    /**
     * Fait le total de chaque données.
     *
     * @return List
     */
    @Override
    public List<Map<String, String>> totauxDonnees() {
        Map<String, String> mapGroupes = new HashMap<>();
        mapGroupes.put("nom", "Groupes");
        mapGroupes.put("nombre", String.valueOf(getDaoFactory().getGroupesDao().count()));
        List<Map<String, String>> mapList = new ArrayList<>();
        mapList.add(mapGroupes);
        Map<String, String> mapAdministrateur = new HashMap<>();
        mapAdministrateur.put("nom", "Administrateur");
        mapAdministrateur.put("nombre", String.valueOf(getDaoFactory().getAdministrateurDao().count()));
        mapList.add(mapAdministrateur);
        Map<String, String> mapUtilisateur = new HashMap<>();
        mapUtilisateur.put("nom", "Utilisateur");
        mapUtilisateur.put("nombre", String.valueOf(getDaoFactory().getUtilisateurDao().count()));
        mapList.add(mapUtilisateur);
        logger.info("##### AdministrateurManagerImpl : Dashboard.");
        return mapList;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
