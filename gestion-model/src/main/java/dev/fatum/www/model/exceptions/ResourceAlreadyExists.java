package dev.fatum.www.model.exceptions;

import java.sql.SQLException;

/**
 * Classe d'exception levée quand l'objet métier demandé existe déjà.
 *
 * @author LEONARD
 */
public class ResourceAlreadyExists extends SQLException {

    /**
     * Constructeur par défaut.
     */
    public ResourceAlreadyExists() {}

    /**
     * Constructeur.
     *
     * @param message -
     */
    public ResourceAlreadyExists(String message) {
        super(message);
    }

    /**
     * Constructeur.
     *
     * @param message -
     * @param cause -
     */
    public ResourceAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

}
