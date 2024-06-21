package es.unirioja.paw.service;

import es.unirioja.paw.jpa.PedidoanuladoEntity;
import es.unirioja.paw.repository.PedidoAnuladoRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PedidoAnuladoUseCaseImpl implements PedidoAnuladoUseCase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private PedidoAnuladoRepository pedidoAnuladoRepository;

    public PedidoAnuladoUseCaseImpl(PedidoAnuladoRepository pedidoAnuladoRepository) {
        this.pedidoAnuladoRepository = pedidoAnuladoRepository;
    }

    @Override
    public List<PedidoanuladoEntity> findAnuladosByCliente(String codigoCliente) {
        List<PedidoanuladoEntity> itemCollection = pedidoAnuladoRepository.findByCodigoCliente(codigoCliente);
        logger.info("Cliente {}: {} pedidos", codigoCliente, itemCollection.size());
        return itemCollection;
    }

    @Override
    public PedidoanuladoEntity findOne(String pedidoId) {
        Optional<PedidoanuladoEntity> entity = pedidoAnuladoRepository.findById(pedidoId);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

}
