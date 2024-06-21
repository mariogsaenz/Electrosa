/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unirioja.paw.db;

import es.unirioja.paw.model.Articulo;
import es.unirioja.paw.model.Cliente;
import es.unirioja.paw.model.Direccion;
import es.unirioja.paw.model.ExcepcionDeAplicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ASUS
 */
public class ClienteDAOMySQL implements ClienteDAO {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Cliente buildCliente(ResultSet res) {
        Cliente c;
        try {
            String calle = res.getString("calle");
            String ciudad = res.getString("ciudad");
            String provincia = res.getString("provincia");
            String cp = res.getString("cp");
            Direccion dir = new Direccion(calle,ciudad,provincia,cp);
            
            c = new Cliente(
                res.getString("codigo"), 
                res.getString("username"),  
                res.getString("nombre"), 
                res.getString("cif"),    
                dir,   
                res.getString("email"), 
                res.getString("tfno")
            );
        } 
        catch (SQLException ex) {
            logger.error("Cannot build entity", ex);
            return null;
        }
        return c;
    }

    @Override
    public Cliente findByUsername(String username) throws ExcepcionDeAplicacion {
        Cliente c = null;
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from CLIENTE where username like ?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = buildCliente(rs);
                if (c != null) {
                    return c;
                }
            }
            rs.close();
        } 
        catch (SQLException ex) {
            logger.error("Error al recuperar el cliente con el username dado", ex);
            throw new ExcepcionDeAplicacion(ex);
        } 
        finally {
            if (con != null) {
                try {
                    ConnectionManager.returnConnection(con);
                } catch (SQLException ex1) {
                    logger.error("Error al cerrar conexion", ex1);
                }
            }
        }
        return c;
    }
    
    @Override
    public Cliente findByCIF(String cif) throws ExcepcionDeAplicacion {
        Cliente c = null;
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from CLIENTE where cif like ?");
            ps.setString(1,cif);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = buildCliente(rs);
                if (c != null) {
                    return c;
                }
            }
            rs.close();
        } 
        catch (SQLException ex) {
            logger.error("Error al recuperar el cliente con el cif dado", ex);
            throw new ExcepcionDeAplicacion(ex);
        } 
        finally {
            if (con != null) {
                try {
                    ConnectionManager.returnConnection(con);
                } catch (SQLException ex1) {
                    logger.error("Error al cerrar conexion", ex1);
                }
            }
        }
        return c;
    }

    @Override
    public Cliente register(Cliente c, String contraseña) throws ExcepcionDeAplicacion {
        //TENDREMOS QUE IMPLEMENTAR EL REGISTRO DE UN NUEVO CLIENTE
        //El cliente c debe ser null
        //Este método generará un código adecuado para el nuevo cliente y lo devolverá
        
        Cliente nuevo = null;
        Connection con = null;
        GeneradorCodigo generador = new GeneradorCodigo();
        try {
            con = ConnectionManager.getConnection();
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO cliente VALUES(?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO usuario VALUES(?,?)");
            //Esto no se si se hace así porque lo de que Cliente deba ser null me parece bastante lioso y no entiendo bien la explicación que se da en el enunciado
            //Generamos un código adecuado para el nuevo cliente
            String cod = generador.generarCodigo(); //Esto hay que hacerlo bien e implementar un sistema bueno
            ps.setString(1,cod);
            ps.setString(2,c.getCif());
            ps.setString(3,c.getNombre());
            ps.setString(4,c.getDireccion().getCalle());
            ps.setString(5,c.getDireccion().getCp());
            ps.setString(6,c.getDireccion().getCiudad());
            ps.setString(7,c.getEmail());
            ps.setString(8,c.getDireccion().getProvincia());
            ps.setString(9,c.getTelefono());
            ps.setString(10,c.getUsername());
            
            ps2.setString(1,c.getUsername());
            ps2.setString(2,contraseña);
            
            ps2.executeUpdate();
            ps.executeUpdate();
            
  
            Direccion d = new Direccion(c.getDireccion().getCalle(),c.getDireccion().getCiudad(),c.getDireccion().getProvincia(),c.getDireccion().getCp());
            nuevo = new Cliente(cod,c.getUsername(),c.getNombre(),c.getCif(),d,c.getEmail(),c.getTelefono());
            
        } 
        catch (SQLException ex) {
            logger.error("Error al insertar cliente", ex);
            throw new ExcepcionDeAplicacion(ex);
        } 
        finally {
            if (con != null) {
                try {
                    ConnectionManager.returnConnection(con);
                } 
                catch (SQLException ex1) {
                    logger.error("Error al cerrar conexion", ex1);
                }
            }
        }
        return nuevo;
    }
    
}
