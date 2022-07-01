package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.UtilisateurManager;
import dev.fatum.www.model.entities.Groupes;
import dev.fatum.www.model.entities.Utilisateur;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manager des beans du package {@link Utilisateur}.
 *
 * @author LEONARD
 */

@Service
public class UtilisateurManagerImpl extends AbstractManagerImpl implements UtilisateurManager {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    /**
     * Enregistre l'{@link Utilisateur}
     *
     * @param utilisateur objet {@link Utilisateur}
     * @return Void
     */
    @Override
    public void saveUtilisateur(Utilisateur utilisateur) throws ResourceAlreadyExists {
        if(getDaoFactory().getProfilDao().findByEmail(utilisateur.getEmail()) != null) {
            logger.error("##### UtilisateurManagerImpl : erreur, utilisateur non enregistré.");
            throw new ResourceAlreadyExists("Cet email existe déjà, veuillez en changer.");
        } else {
            Set<Groupes> groupesSet = new HashSet<>();
            utilisateur.setGroupesSet(groupesSet);
            Groupes groupes = getDaoFactory().getGroupesDao().findByNom("USER");
            if(groupes == null) {
                logger.error("##### UtilisateurManagerImpl : erreur, groupe USER non trouvé.");
                throw new ResourceNotFoundException("Le groupe USER est introuvable, échec de l'enregistrement.");
            } else {
                groupesSet.add(groupes);
            }
            utilisateur.setMdp(passwordEncoder().encode(utilisateur.getMdp()));
            getDaoFactory().getUtilisateurDao().save(utilisateur);
            logger.info("##### UtilisateurManagerImpl : utilisateur enregistré.");
        }
    }

    /**
     * Renvoie tous les {@link Utilisateur}
     *
     * @return List
     */
    @Override
    public List<Utilisateur> findAllUtilisateursDesc() {
        List<Utilisateur> utilisateurList;
        utilisateurList = getDaoFactory().getUtilisateurDao().findAllByOrderByIdDesc();

        if(utilisateurList == null) {
            logger.error("##### UtilisateurManagerImpl : erreur, liste des utilisateurs non trouvée.");
            throw new ResourceNotFoundException("La liste des utilisateurs est introuvable.");
        }
        logger.info("##### UtilisateurManagerImpl : liste des utilisateurs trouvée.");
        return utilisateurList;
    }

    /**
     * Renvoie l'utilisateur demandé
     *
     * @param id l'identifiant de l'utilisateur
     * @return {@link Utilisateur}
     * @throws ResourceNotFoundException si utilisateur est null
     */
    @Override
    public Utilisateur findUtilisateurById(int id) {
        Utilisateur utilisateur = getDaoFactory().getUtilisateurDao().findById(id);
        if(utilisateur == null) {
            logger.error("##### UtilisateurManagerImpl : utilisateur " + id + " non trouvé.");
            throw new ResourceNotFoundException("L'utilisateur avec l'id " + id + " est introuvable.");
        } else {
            logger.info("##### UtilisateurManagerImpl : utilisateur " + id + " trouvé.");
            return utilisateur;
        }
    }

    /**
     * Edite l'{@link Utilisateur}
     *
     * @param utilisateur objet {@link Utilisateur}
     * @return Void
     */
    @Override
    public void updateUtilisateur(Utilisateur utilisateur) throws ResourceAlreadyExists {
        int id = utilisateur.getId();
        Utilisateur oldUtilisateur = getDaoFactory().getUtilisateurDao().findById(id);
        if(!utilisateur.getMdp().equals(oldUtilisateur.getMdp())) {
            utilisateur.setMdp(passwordEncoder().encode(utilisateur.getMdp()));
        }
        if(getDaoFactory().getProfilDao().emailDuplicated(utilisateur.getEmail(), utilisateur.getId()) != null) {
            logger.error("##### UtilisateurManagerImpl : erreur, utilisateur non édité.");
            throw new ResourceAlreadyExists("Cet email existe déjà, veuillez en changer.");
        } else if (!utilisateur.getGroupesSet().iterator().next().getNom().equals("USER")) {
            logger.error("##### UtilisateurManagerImpl : erreur, groupe USER non trouvé.");
            throw new ResourceNotFoundException("Le groupe USER est introuvable, échec de l'édition.");
        } else {
            getDaoFactory().getUtilisateurDao().save(utilisateur);
            logger.info("##### UtilisateurManagerImpl : utilisateur " + utilisateur.getId() + " édité.");
        }
    }

    /**
     * Supprime l'{@link Utilisateur} d'identifiant {@code id}
     *
     * @param id identifiant de l'{@link Utilisateur}
     * @return Void
     */
    @Override
    public void deleteUtilisateurById(int id) {
        getDaoFactory().getUtilisateurDao().deleteById(id);
        logger.info("##### UtilisateurManagerImpl : utilisateur " + id + " supprimé.");
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
