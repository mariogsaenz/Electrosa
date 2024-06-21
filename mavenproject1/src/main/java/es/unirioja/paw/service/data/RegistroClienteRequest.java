package es.unirioja.paw.service.data;

import es.unirioja.paw.web.data.RegistroPostPayload;

public class RegistroClienteRequest {

    public final RegistroPostPayload payload;

    public RegistroClienteRequest(RegistroPostPayload payload) {
        this.payload = payload;
    }

}
