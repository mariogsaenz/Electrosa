package es.unirioja.paw.service.data;

import es.unirioja.paw.jpa.ClienteEntity;
import es.unirioja.paw.jpa.UsuarioEntity;

public class RegistroClienteResponse {

    public final ClienteEntity cliente;
    public final UsuarioEntity usuario;

    public RegistroClienteResponse(ClienteEntity cliente, UsuarioEntity usuario) {
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public boolean isSuccess() {
        if (cliente == null) {
            return false;
        }
        if (usuario == null) {
            return false;
        }
        return true;
    }

}
