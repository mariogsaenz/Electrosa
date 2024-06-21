/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unirioja.paw.db;

import ch.qos.logback.core.CoreConstants;
//import es.unirioja.paw.controller.ArticuloSearchCriteria;
import es.unirioja.paw.model.Articulo;
import es.unirioja.paw.model.ExcepcionDeAplicacion;
import es.unirioja.paw.model.Fabricante;
import es.unirioja.paw.model.TipoArticulo;
//import es.unirioja.paw.pagination.PageNumberPagination;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 *
 * @author ASUS
 */
public class ArticuloDAOMySQL implements ArticuloDAO{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Articulo buildArticulo(ResultSet res) {
        Articulo a;
        try {
            a = new Articulo(
                res.getString("codigo"), 
                res.getString("nombre"), 
                res.getDouble("pvp"), 
                res.getString("tipo"), 
                res.getString("fabricante"), 
                res.getString("foto"), 
                res.getString("descripcion")
            );
        } 
        catch (SQLException ex) {
            logger.error("Cannot build entity", ex);
            return null;
        }
        return a;
    }
    
    @Override
    public List<Articulo> findAll() throws ExcepcionDeAplicacion {
        List<Articulo> result = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from articulo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Articulo a = buildArticulo(rs);
                if (a != null) {
                    result.add(a);
                }
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Error al recuperar los artículos", ex);
            throw new ExcepcionDeAplicacion(ex);
        } finally {
            if (con != null) {
                try {
                    ConnectionManager.returnConnection(con);
                } catch (SQLException ex1) {
                    logger.error("Error al cerrar conexion", ex1);
                }
            }
        }
        return result;
    }

    @Override
    public Articulo findOneByCodigo(String codigo) throws ExcepcionDeAplicacion {
        Articulo a = null;
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from articulo where codigo like ?");
            ps.setString(1, "%" + codigo + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = buildArticulo(rs);
                if (a != null) {
                    return a;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            logger.error("Error al recuperar el articulo con el codigo dado", ex);
            throw new ExcepcionDeAplicacion(ex);
        } finally {
            if (con != null) {
                try {
                    ConnectionManager.returnConnection(con);
                } catch (SQLException ex1) {
                    logger.error("Error al cerrar conexion", ex1);
                }
            }
        }
        return a;
    }
    
    //MÉTODOS ENCARGADOS DE LA PAGINACIÓN
    
    
    public List<Articulo> findByPageVersionMala(int pageNumber, int pageSize) throws ExcepcionDeAplicacion {
        List<Articulo> articulos = this.findAll();
        
        List<List<Articulo>> subListas = new ArrayList<>();
              
        int n = articulos.size() / pageSize;
        int paginas = (int) Math.ceil(n);
        
        for(int j = 0; j<paginas; j++){
            List<Articulo> sub = new ArrayList<>();
            for(int i = 0; i<pageSize; i++){
                sub.add(articulos.get(i));
            }
            subListas.add(sub);
        }
        
        if(pageNumber>0){
            List<Articulo> solucion = (List<Articulo>) subListas.get(pageNumber);
            return solucion;
        }
        else{
            System.out.println("Page Number incorrecto");
        }
        return articulos;
    }
    
    @Override
    public List<Articulo> findByPage(int pageNumber, int pageSize) throws ExcepcionDeAplicacion {
        List<Articulo> listArticulos = new ArrayList<Articulo>();
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement pre = con.prepareStatement("SELECT  * FROM articulo LIMIT ?, ?");
            pre.setInt(1, pageNumber);
            pre.setInt(2, pageSize );
            ResultSet res = pre.executeQuery();
            while (res.next()) {
                String n = res.getString(1);
                Articulo a = findOneByCodigo(n);
                listArticulos.add(a);
            }
            res.close();
            pre.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return listArticulos;
    }

    
    /*@Override
    public PageNumberPagination buildPageNumberPagination(int pageSize, int currentPage) throws ExcepcionDeAplicacion {
        
        Connection con = null;
        String countQuery = "SELECT COUNT(*) AS total FROM articulo";
        int totalRecords = 0;
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(countQuery);
            ResultSet countResult = ps.executeQuery();
            if (countResult.next()) {
                totalRecords = countResult.getInt("total");
            }
            countResult.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return new PageNumberPagination(totalRecords, pageSize, currentPage);

    }*/
    
    @Override
    public List<TipoArticulo> findTiposArticulo() throws ExcepcionDeAplicacion {
        List<TipoArticulo> sol = new ArrayList<>();
        Connection con = null;
        String query = "SELECT DISTINCT(tipo) FROM articulo";
        try{
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String tip = res.getString(1);
                TipoArticulo ta = new TipoArticulo(tip);
                sol.add(ta);
            }
            res.close();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return sol;
    }
    
    @Override
    public List<Fabricante> findFabricantesArticulos() throws ExcepcionDeAplicacion {
        List<Fabricante> sol = new ArrayList<>();
        Connection con = null;
        String query = "SELECT DISTINCT(fabricante) FROM articulo";
        try{
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String fab = res.getString(1);
                Fabricante fa = new Fabricante(fab);
                sol.add(fa);
            }
            res.close();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return sol;
    }

    /*
    @Override
    public List<Articulo> findByCriteria(ArticuloSearchCriteria criteria, int pageSize, int currentPage) throws ExcepcionDeAplicacion {
        Connection con = null;
        
        List<Articulo> sol = new ArrayList<>();
        
        try {
            con = ConnectionManager.getConnection();
            String consulta = "SELECT * FROM articulo" + criteria.getFiltroBusquedaGeneral() + " limit ?,?";
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setInt(1, (currentPage-1)*pageSize);
            ps.setInt(2, pageSize);
            ResultSet res = ps.executeQuery();
            if (!"".equals(criteria.getFiltroBusquedaGeneral())) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Articulo a = buildArticulo(rs);
                        if (a != null) {
                            sol.add(a);
                        }
                    }
                }
            }
            res.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        } 
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return sol;
    }

    @Override
    public PageNumberPagination buildPageNumberPagination(ArticuloSearchCriteria criteria, int pageSize, int currentPage) throws ExcepcionDeAplicacion {
        
        int count = 1;
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            String query = "select count(*) from articulo" + criteria.getFiltroBusquedaGeneral();
            PreparedStatement ps = con.prepareStatement(query);
            if (!"".equals(criteria.getFiltroBusquedaGeneral())) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            logger.error("Error al recuperar el articulo", ex);
            throw new ExcepcionDeAplicacion(ex);
        } finally {
            if (con != null) {
                try {
                    ConnectionManager.returnConnection(con);
                } catch (SQLException ex1) {
                    logger.error("Error al cerrar conexion", ex1);
                }
            }
        }
        return new PageNumberPagination(count, pageSize, currentPage);
    }
    */
}
