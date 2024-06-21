package es.unirioja.paw.service;

import es.unirioja.paw.service.data.RegistroClienteRequest;
import es.unirioja.paw.service.data.RegistroClienteResponse;

/**
 * Caso de uso de registro de nuevo cliente
 */
public interface RegistroClienteUseCase {

    public RegistroClienteResponse registrar(RegistroClienteRequest request);

}
