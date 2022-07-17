package dev.fatum.www.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Sécurité pour les connexions.
 *
 * @author LEONARD
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    /**
     * Authntification de l'utilisateur
     *
     * @param auth fonction d'authentification {@link AuthenticationManagerBuilder}
     * @return {@link Void}
     * @throws Exception Si l'authentification echoue.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Control par {@code http} des droit d'accés
     *
     * @param http pour {@link HttpSecurity}
     * @return {@link Void}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
*/
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/nouvel-utilisateur").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        // H2 database consol :
        http.headers().frameOptions().disable();
    }

    /**
     * Renvoie un {@link PasswordEncoder} pour crypter le mot de passe
     *
     * @return {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}