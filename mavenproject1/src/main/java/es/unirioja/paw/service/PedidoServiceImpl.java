package es.unirioja.paw.service;

import es.unirioja.paw.jpa.CestaCompraEntity;
import es.unirioja.paw.jpa.PedidoEntity;
import es.unirioja.paw.repository.CestaCompraRepository;
import es.unirioja.paw.repository.PedidoRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private PedidoRepository pedidoRepository;
    private CestaCompraRepository cestaRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, CestaCompraRepository cestaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.cestaRepository = cestaRepository;
    }

    @Override
    public List<PedidoEntity> findByCliente(String codigoCliente) {
        PedidoEntity example = new PedidoEntity();
        example.setCodigoCliente(codigoCliente);
        List<PedidoEntity> itemCollection = pedidoRepository.findAll(Example.of(example));
        if (itemCollection != null) {
            for (PedidoEntity e : itemCollection) {
                e.buildImporte();
            }
        }
        logger.info("Cliente {}: {} pedidos", codigoCliente, itemCollection.size());
        return itemCollection;
    }

    @Override
    public PedidoEntity findOne(String codigoPedido) {
        PedidoEntity pedido = null;
        Optional<PedidoEntity> entity = pedidoRepository.findById(codigoPedido);
        if (entity.isPresent()) {
            pedido = entity.get();
            pedido.buildImporte();
        }
        return pedido;
    }

    @Override
    public CestaCompraEntity findCestaCliente(String codigoCliente) {
        CestaCompraEntity example = new CestaCompraEntity();
        example.setCodigoCliente(codigoCliente);
        Optional<CestaCompraEntity> cesta = cestaRepository.findOne(Example.of(example));
        if (cesta.isPresent()) {
            logger.info("Cliente {}: cesta {}", codigoCliente, cesta.get().getCodigo());
            return cesta.get();
        }
        logger.info("Cliente {}: todavia no tiene cesta", codigoCliente);
        return null;
    }

    @Override
    public void deleteCestaCliente(String codigoCliente) {
        CestaCompraEntity cesta = cestaRepository.findOneByCodigoCliente(codigoCliente);
        if (cesta == null) {
            logger.info("Cliente {}: no hay cesta", codigoCliente);
            return;
        }
        logger.info("Cliente {}: cesta {}", codigoCliente, cesta.getCodigo());
        cestaRepository.deleteById(cesta.getCodigo());
    }

}
