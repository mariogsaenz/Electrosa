package es.unirioja.paw.service;

import es.unirioja.paw.jpa.PedidoanuladoEntity;
import java.util.List;

public interface PedidoAnuladoUseCase {

    public List<PedidoanuladoEntity> findAnuladosByCliente(String codigoCliente);

    public PedidoanuladoEntity findOne(String pedidoId);
    
}
