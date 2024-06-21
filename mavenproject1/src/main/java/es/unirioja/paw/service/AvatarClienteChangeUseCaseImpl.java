package es.unirioja.paw.service;

import es.unirioja.paw.jpa.ClienteEntity;
import es.unirioja.paw.repository.ClienteRepository;
import es.unirioja.paw.service.data.AvatarClienteChangeRequest;
import es.unirioja.paw.service.data.AvatarClienteChangeResponse;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvatarClienteChangeUseCaseImpl implements AvatarClienteChangeUseCase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileStorageService storage;

    private final ClienteRepository clienteRepository;

    @Autowired
    public AvatarClienteChangeUseCaseImpl(FileStorageService storage, ClienteRepository clienteRepository) {
        this.storage = storage;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<AvatarClienteChangeResponse> execute(AvatarClienteChangeRequest changeRequest) {
        // Guardar el fichero en la ruta de destino
        try {
            storage.store(changeRequest.file, changeRequest.realContextPath);
        } catch (Exception ex) {
            logger.error("Cliente {}: error al guardar avatar", changeRequest.codigoCliente);
            logger.error("Error guardando fichero", ex);
            return Optional.empty();
        }

        // Actualizar el avatar del cliente
        Optional<ClienteEntity> entity = clienteRepository.findById(changeRequest.codigoCliente);
        if (!entity.isPresent()) {
            logger.error("Cliente {}: no encontrado", changeRequest.codigoCliente);
            return Optional.empty();
        }
        ClienteEntity cliente = entity.get();
        logger.info("Cliente {}: encontrado", changeRequest.codigoCliente);

        cliente.setImagenAvatar(changeRequest.file.getOriginalFilename());
        cliente = clienteRepository.save(cliente);
        logger.info("Cliente {}: avatar actualizado", cliente.getCodigo());

        return Optional.of(new AvatarClienteChangeResponse(cliente));
    }

}
