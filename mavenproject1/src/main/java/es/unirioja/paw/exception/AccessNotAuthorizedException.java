package es.unirioja.paw.exception;


public class AccessNotAuthorizedException extends RuntimeException {

    public AccessNotAuthorizedException() {
    }

    public AccessNotAuthorizedException(String errorMessage) {
        super(errorMessage);
    }

}
