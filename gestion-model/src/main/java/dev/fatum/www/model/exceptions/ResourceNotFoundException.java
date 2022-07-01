package dev.fatum.www.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe d'exception levée quand l'objet métier demandé n'a pas été trouvé.
 *
 * @author LEONARD
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructeur par défaut.
     */
    public ResourceNotFoundException(){
    }

    /**
     * Constructeur.
     *
     * @param message -
     */
    public ResourceNotFoundException(String message){
        super(message);
    }

    /**
     * Constructeur.
     *
     * @param message -
     * @param cause -
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
