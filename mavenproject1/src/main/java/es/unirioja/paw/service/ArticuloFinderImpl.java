package es.unirioja.paw.service;

import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.jpa.Fabricante;
import es.unirioja.paw.jpa.TipoArticulo;
import es.unirioja.paw.repository.ArticuloRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloFinderImpl implements ArticuloFinder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticuloRepository articuloRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ArticuloEntity> findAll() {
        return articuloRepository.findAll();
    }

    @Override
    public List<Fabricante> findFabricantes() {
        logger.info("entityManager: {}", entityManager);

        List<String> stringCollection = entityManager
                .createQuery("""
                    select distinct a.fabricante
                    from ArticuloEntity a 
                    order by a.fabricante
                    """, String.class).getResultList();
        return buildFabricantes(stringCollection);
    }

    @Override
    public List<TipoArticulo> findTiposArticulo() {
        logger.info("entityManager: {}", entityManager);

        List<String> stringCollection = entityManager
                .createQuery("""
                    select distinct a.tipo
                    from ArticuloEntity a
                    order by a.tipo
                    """, String.class).getResultList();
        return buildTiposArticulo(stringCollection);
    }

    private List<Fabricante> buildFabricantes(List<String> stringCollection) {

        List<Fabricante> result = new ArrayList<>();
        if (stringCollection == null) {
            return result;
        }
        for (String s : stringCollection) {
            Fabricante e = new Fabricante(s);
            result.add(e);
        }
        logger.info("Fabricantes {}", result.size());
        return result;
    }

    private List<TipoArticulo> buildTiposArticulo(List<String> stringCollection) {

        List<TipoArticulo> result = new ArrayList<>();
        if (stringCollection == null) {
            return result;
        }
        for (String s : stringCollection) {
            TipoArticulo e = new TipoArticulo(s);
            result.add(e);
        }
        logger.info("TiposArticulo {}", result.size());
        return result;
    }

}
