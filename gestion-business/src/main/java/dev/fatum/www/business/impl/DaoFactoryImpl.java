package dev.fatum.www.business.impl;

import dev.fatum.www.business.DaoFactory;
import dev.fatum.www.consumer.dao.*;
import dev.fatum.www.consumer.dao.documents.DevisDao;
import dev.fatum.www.consumer.dao.documents.FactureDao;
import dev.fatum.www.consumer.dao.documents.NumerosDocumentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoFactoryImpl implements DaoFactory {

    @Autowired
    private DevisDao devisDao;

    @Autowired
    private FactureDao factureDao;

    @Autowired
    private NumerosDocumentsDao numerosDocumentsDao;

    @Autowired
    private PersonneDao personneDao;

    @Autowired
    private EntrepriseDao entrepriseDao;

    @Autowired
    private GroupesDao groupesDao;

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private AdministrateurDao administrateurDao;

    @Autowired
    private ProfilDao profilDao;

    @Override
    public DevisDao getDevisDao() {
        return devisDao;
    }

    @Override
    public FactureDao getFactureDao() {
        return factureDao;
    }

    @Override
    public NumerosDocumentsDao getNumerosDocumentsDao() {
        return numerosDocumentsDao;
    }

    @Override
    public PersonneDao getPersonneDao() {
        return personneDao;
    }

    @Override
    public EntrepriseDao getEntrepriseDao() {
        return entrepriseDao;
    }

    @Override
    public GroupesDao getGroupesDao() {
        return groupesDao;
    }

    @Override
    public UtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    @Override
    public AdministrateurDao getAdministrateurDao() {
        return administrateurDao;
    }

    @Override
    public ProfilDao getProfilDao() {
        return profilDao;
    }
}
