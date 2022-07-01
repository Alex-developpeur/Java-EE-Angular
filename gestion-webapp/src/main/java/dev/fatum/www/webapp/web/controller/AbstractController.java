package dev.fatum.www.webapp.web.controller;

import dev.fatum.www.business.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract ressource REST pour les controller.
 *
 * @author LEONARD
 */

public abstract class AbstractController {

    @Autowired
    private ManagerFactory managerFactory;

    /**
     * Renvoie l'interface {@link ManagerFactory}
     *
     * @return ManagerFactory
     */
    protected ManagerFactory getManagerFactory() {
        return managerFactory;
    }

    /**
     * Implemente l'interface {@link ManagerFactory}
     *
     * @return Void
     */
    public void setManagerFactory(ManagerFactory managerFactory) {
        managerFactory = managerFactory;
    }
}
