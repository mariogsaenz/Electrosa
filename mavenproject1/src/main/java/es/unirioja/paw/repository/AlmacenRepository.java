package es.unirioja.paw.repository;

import es.unirioja.paw.jpa.AlmacenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<AlmacenEntity, String> {
}
