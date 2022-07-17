package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.ProfilManager;
import dev.fatum.www.model.entities.Profil;
import org.springframework.stereotype.Service;

@Service
public class ProfilManagerImpl extends AbstractManagerImpl implements ProfilManager {

    @Override
    public Profil getProfilByEmail(String email) {
        return getDaoFactory().getProfilDao().findByEmail(email);
    }

}
