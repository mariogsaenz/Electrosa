package es.unirioja.paw.repository;

import es.unirioja.paw.jpa.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    
}
