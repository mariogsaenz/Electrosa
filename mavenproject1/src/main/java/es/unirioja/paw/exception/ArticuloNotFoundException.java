package es.unirioja.paw.exception;

public class ArticuloNotFoundException extends RuntimeException {

    public ArticuloNotFoundException() {
    }

    public ArticuloNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
