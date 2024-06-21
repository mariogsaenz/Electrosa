package es.unirioja.paw.repository;

import es.unirioja.paw.jpa.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, String> {

}
