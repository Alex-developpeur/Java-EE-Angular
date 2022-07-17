package dev.fatum.www.business;

import dev.fatum.www.consumer.dao.*;
import dev.fatum.www.consumer.dao.documents.DevisDao;
import dev.fatum.www.consumer.dao.documents.FactureDao;
import dev.fatum.www.consumer.dao.documents.NumerosDocumentsDao;

public interface DaoFactory {

    DevisDao getDevisDao();

    FactureDao getFactureDao();

    NumerosDocumentsDao getNumerosDocumentsDao();

    PersonneDao getPersonneDao();

    EntrepriseDao getEntrepriseDao();

    GroupesDao getGroupesDao();

    UtilisateurDao getUtilisateurDao();

    AdministrateurDao getAdministrateurDao();

    ProfilDao getProfilDao();

}
