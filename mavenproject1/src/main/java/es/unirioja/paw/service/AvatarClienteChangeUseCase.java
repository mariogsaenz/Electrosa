package es.unirioja.paw.service;

import es.unirioja.paw.service.data.AvatarClienteChangeRequest;
import es.unirioja.paw.service.data.AvatarClienteChangeResponse;
import java.util.Optional;

public interface AvatarClienteChangeUseCase {

    public Optional<AvatarClienteChangeResponse>  execute(AvatarClienteChangeRequest changeRequest);
}
