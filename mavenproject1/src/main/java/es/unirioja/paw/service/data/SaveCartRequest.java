package es.unirioja.paw.service.data;

import es.unirioja.paw.jpa.CestaCompraEntity;

public class SaveCartRequest {

    public String codigoCliente;

    public CestaCompraEntity cesta;

    public SaveCartRequest(String codigoCliente, CestaCompraEntity cesta) {
        this.codigoCliente = codigoCliente;
        this.cesta = cesta;
    }

}
