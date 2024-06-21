package es.unirioja.paw.service.data;

import es.unirioja.paw.jpa.ClienteEntity;

public class AvatarClienteChangeResponse {

    public final ClienteEntity cliente;

    public AvatarClienteChangeResponse(ClienteEntity cliente) {
        this.cliente = cliente;
    }

}
