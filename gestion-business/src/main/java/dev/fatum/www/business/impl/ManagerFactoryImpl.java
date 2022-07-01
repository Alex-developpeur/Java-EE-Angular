package dev.fatum.www.business.impl;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.business.manager.*;
import dev.fatum.www.business.manager.documents.DevisManager;
import dev.fatum.www.business.manager.documents.FactureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerFactoryImpl implements ManagerFactory {

    @Autowired
    private DevisManager devisManager;

    @Autowired
    private FactureManager factureManager;

    @Autowired
    private PersonneManager personneManager;

    @Autowired
    private EntrepriseManager entrepriseManager;

    @Autowired
    private UtilisateurManager utilisateurManager;

    @Autowired
    private AdministrateurManager administrateurManager;

    @Autowired
    private GroupesManager groupesManager;

    @Autowired
    private ProfilManager profilManager;

    @Override
    public DevisManager getDevisManager() {
        return devisManager;
    }

    @Override
    public FactureManager getFactureManager() {
        return factureManager;
    }

    @Override
    public PersonneManager getPersonneManager() {
        return personneManager;
    }

    public void setPersonneManager(PersonneManager personneManager) {
        this.personneManager = personneManager;
    }

    @Override
    public EntrepriseManager getEntrepriseManager() {
        return entrepriseManager;
    }

    public void setEntrepriseManager(EntrepriseManager entrepriseManager) {
        this.entrepriseManager = entrepriseManager;
    }

    @Override
    public UtilisateurManager getUtilisateurManager() {
        return utilisateurManager;
    }

    public void setUtilisateurManager(UtilisateurManager utilisateurManager) {
        this.utilisateurManager = utilisateurManager;
    }

    @Override
    public AdministrateurManager getAdministrateurManager() {
        return administrateurManager;
    }

    public void setAdministrateurManager(AdministrateurManager administrateurManager) {
        this.administrateurManager = administrateurManager;
    }

    @Override
    public GroupesManager getGroupesManager() {
        return groupesManager;
    }

    public void setGroupesManager(GroupesManager groupesManager) {
        this.groupesManager = groupesManager;
    }

    @Override
    public ProfilManager getProfilManager() {
        return profilManager;
    }

    public ProfilManager setProfilManager(ProfilManager profilManager) {
        return this.profilManager = profilManager;
    }

}
