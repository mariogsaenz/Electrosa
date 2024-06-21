package es.unirioja.paw.exception;

public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException() {
    }

    public PedidoNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
