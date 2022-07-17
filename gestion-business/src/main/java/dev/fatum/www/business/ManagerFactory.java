package dev.fatum.www.business;

import dev.fatum.www.business.manager.*;
import dev.fatum.www.business.manager.documents.DevisManager;
import dev.fatum.www.business.manager.documents.FactureManager;

public interface ManagerFactory {

    DevisManager getDevisManager();

    FactureManager getFactureManager();

    PersonneManager getPersonneManager();

    EntrepriseManager getEntrepriseManager();

    GroupesManager getGroupesManager();

    UtilisateurManager getUtilisateurManager();

    AdministrateurManager getAdministrateurManager();

    ProfilManager getProfilManager();
}
