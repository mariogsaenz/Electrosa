package es.unirioja.paw.service.data;

import es.unirioja.paw.jpa.CestaCompraEntity;
import es.unirioja.paw.jpa.LineaCestaCompraEntity;

public class AddToCartResponse {

    public final CestaCompraEntity cesta;
    public final LineaCestaCompraEntity linea;

    public AddToCartResponse(CestaCompraEntity cesta, LineaCestaCompraEntity linea) {
        this.cesta = cesta;
        this.linea = linea;
    }

}
