package es.unirioja.paw.repository;

import es.unirioja.paw.jpa.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, String> {
}
