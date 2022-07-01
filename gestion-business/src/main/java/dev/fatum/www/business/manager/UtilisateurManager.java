package dev.fatum.www.business.manager;

import dev.fatum.www.model.entities.Utilisateur;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;

import java.util.List;

public interface UtilisateurManager {

    void saveUtilisateur(Utilisateur utilisateur) throws ResourceAlreadyExists;

    List<Utilisateur> findAllUtilisateursDesc();

    Utilisateur findUtilisateurById(int id);

    void updateUtilisateur(Utilisateur utilisateur) throws ResourceAlreadyExists;

    void deleteUtilisateurById(int id);

}
