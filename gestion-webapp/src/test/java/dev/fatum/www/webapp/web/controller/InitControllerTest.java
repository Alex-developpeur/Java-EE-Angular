package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.consumer.dao.*;
import dev.fatum.www.consumer.dao.documents.DevisDao;
import dev.fatum.www.consumer.dao.documents.FactureDao;
import dev.fatum.www.consumer.dao.documents.NumerosDocumentsDao;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.springframework.boot.test.mock.mockito.MockBean;

public class InitControllerTest implements TestInstancePostProcessor {

    @MockBean
    private AdministrateurDao administrateurDao;

    @MockBean
    private UtilisateurDao utilisateurDao;

    @MockBean
    private PersonneDao personneDao;

    @MockBean
    private ProfilDao profilDao;

    @MockBean
    private EntrepriseDao entrepriseDao;

    @MockBean
    private NumerosDocumentsDao numerosDocumentsDao;

    @MockBean
    private FactureDao factureDao;

    @MockBean
    private DevisDao devisDao;

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {

    }
}
