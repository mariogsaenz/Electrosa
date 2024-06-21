package es.unirioja.paw.db;

//import es.unirioja.paw.controller.ArticuloSearchCriteria;
import es.unirioja.paw.model.Articulo;
import es.unirioja.paw.model.ExcepcionDeAplicacion;
import es.unirioja.paw.model.Fabricante;
import es.unirioja.paw.model.TipoArticulo;
//import es.unirioja.paw.pagination.PageNumberPagination;
import java.util.List;

public interface ArticuloDAO {

    /**
     * @return Todos los articulos
     * @throws ExcepcionDeAplicacion 
     */
    public List<Articulo> findAll() throws ExcepcionDeAplicacion;

    /**
     * @param codigo Código de artículo
     * @return Articulo
     * @throws ExcepcionDeAplicacion 
     */
    public Articulo findOneByCodigo(String codigo) throws ExcepcionDeAplicacion;

    /**
     * @param pageNumber Número de página a mostrar
     * @param pageSize Número de artículos a mostrar en cada página
     * @return Lista de articulos a mostrar en la pagina
     * @throws ExcepcionDeAplicacion
     */
    public List<Articulo> findByPage(int pageNumber, int pageSize) throws ExcepcionDeAplicacion;

    /**
     * @return Todos los tipos de artículos
     * @throws ExcepcionDeAplicacion 
     */
    public List<TipoArticulo> findTiposArticulo() throws ExcepcionDeAplicacion;
    
    /**
     * @return Todos los fabricantes de artículos
     * @throws ExcepcionDeAplicacion 
     */
    public List<Fabricante> findFabricantesArticulos() throws ExcepcionDeAplicacion;

    /**
     *
     * @param pageSize Número de artículos a mostrar en cada página
     * @param currentPage Número de página actual
     * @return Información para el paginador
     * @throws ExcepcionDeAplicacion
     */
    //public PageNumberPagination buildPageNumberPagination(int pageSize, int currentPage) throws ExcepcionDeAplicacion;

    /**
     * 
     * @param criteria Criterios de búsqueda
     * @param pageSize Número de artículos a mostrar en cada página
     * @param currentPage Número de página actual
     * @return
     * @throws ExcepcionDeAplicacion 
     */
    //public List<Articulo> findByCriteria(ArticuloSearchCriteria criteria, int pageSize, int currentPage) throws ExcepcionDeAplicacion;

    /**
     * @param criteria Criterios de búsqueda
     * @param pageSize Número de artículos a mostrar en cada página
     * @param currentPage Número de página actual
     * @return Información para el paginador
     * @throws ExcepcionDeAplicacion 
     */
    //public PageNumberPagination buildPageNumberPagination(ArticuloSearchCriteria criteria, int pageSize, int currentPage) throws ExcepcionDeAplicacion;
}