package es.unirioja.paw.service.data;

import es.unirioja.paw.jpa.CestaCompraEntity;

public class AddToCartRequest {

    public String codigoArticulo;

    public CestaCompraEntity cesta;

    public AddToCartRequest(String codigoArticulo, CestaCompraEntity cesta) {
        this.codigoArticulo = codigoArticulo;
        this.cesta = cesta;
    }

}
