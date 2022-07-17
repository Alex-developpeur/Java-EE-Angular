package dev.fatum.www.webapp.security;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.Groupes;
import dev.fatum.www.model.entities.Profil;
import dev.fatum.www.model.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Service d'identification pour les connexions.
 *
 * @author LEONARD
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ManagerFactory managerFactory;

    /**
     * Renvoie un {@link UserDetails} authentifié par {@code email}
     *
     * @param email login en {@link String}
     * @return {@link UserDetails}
     * @throws ResourceNotFoundException Si le {@link Profil} n'a pas été trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Profil appUser = managerFactory.getProfilManager().getProfilByEmail(email);

        if (appUser == null)
            throw new ResourceNotFoundException("Email ou mot de passe incorrect.");

        Set<Groupes> roleNames = appUser.getGroupesSet();

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (Groupes role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getNom());
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new User(appUser.getEmail(), appUser.getMdp(), grantList);

        return userDetails;
    }

}
