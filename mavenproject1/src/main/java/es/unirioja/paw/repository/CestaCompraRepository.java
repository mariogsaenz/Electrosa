package es.unirioja.paw.repository;

import es.unirioja.paw.jpa.CestaCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CestaCompraRepository extends JpaRepository<CestaCompraEntity, String> {

    public CestaCompraEntity findOneByCodigoCliente(String s);
}
