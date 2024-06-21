package es.unirioja.paw.exception;

public class RepositoryException extends RuntimeException {

    public RepositoryException() {
    }

    public RepositoryException(String errorMessage) {
        super(errorMessage);
    }
}
