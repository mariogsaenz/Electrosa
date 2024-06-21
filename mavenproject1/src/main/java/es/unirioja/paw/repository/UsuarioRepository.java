package es.unirioja.paw.repository;

import es.unirioja.paw.jpa.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

}
