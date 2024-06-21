package es.unirioja.paw.service;

import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.jpa.Fabricante;
import es.unirioja.paw.jpa.TipoArticulo;
import java.util.List;

public interface ArticuloFinder {

    /**
     * @return Todos los articulos
     */
    public List<ArticuloEntity> findAll();

    /**
     * @return Coleccion de los diferentes fabricantes
     */
    public List<Fabricante> findFabricantes();

    /**
     * @return Coleccion de los diferentes tipos de articulos
     */
    public List<TipoArticulo> findTiposArticulo();

}
