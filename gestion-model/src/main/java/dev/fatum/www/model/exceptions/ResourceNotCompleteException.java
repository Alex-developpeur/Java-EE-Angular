package dev.fatum.www.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe d'exception levée quand l'objet métier demandé n'est pas complet.
 *
 * @author LEONARD
 */
public class ResourceNotCompleteException extends RuntimeException {

    /**
     * Constructeur par défaut.
     */
    public ResourceNotCompleteException(){
    }

    /**
     * Constructeur.
     *
     * @param message -
     */
    public ResourceNotCompleteException(String message){
        super(message);
    }

    /**
     * Constructeur.
     *
     * @param message -
     * @param cause -
     */
    public ResourceNotCompleteException(String message, Throwable cause) {
        super(message, cause);
    }

}
