package dev.fatum.www.business.manager;

import dev.fatum.www.model.entities.Profil;

public interface ProfilManager {

    Profil getProfilByEmail(String email);

}
