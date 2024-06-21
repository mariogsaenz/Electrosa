package es.unirioja.paw.service.data;

import es.unirioja.paw.jpa.CestaCompraEntity;

public class SaveCartResponse {

    public final CestaCompraEntity cesta;

    public SaveCartResponse(CestaCompraEntity cesta) {
        this.cesta = cesta;
    }

}
