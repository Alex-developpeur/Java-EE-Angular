package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractManagerImpl {

    @Autowired
    private DaoFactory daoFactory;

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactoryaoFactory) {
        daoFactory = daoFactory;
    }
}
