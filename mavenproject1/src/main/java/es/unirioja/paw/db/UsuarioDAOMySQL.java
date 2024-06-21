/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unirioja.paw.db;

import es.unirioja.paw.model.ExcepcionDeAplicacion;
import es.unirioja.paw.model.Usuario;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ASUS
 */
public class UsuarioDAOMySQL implements UsuarioDAO {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Usuario buildUsuario(ResultSet res) {
        Usuario u;
        try {
            u = new Usuario(
                res.getString("username"), 
                res.getString("password")
            );
        } 
        catch (SQLException ex) {
            logger.error("Cannot build entity", ex);
            return null;
        }
        return u;
    }

    @Override
    public Usuario findUsuarioByExample(Usuario example) throws ExcepcionDeAplicacion {
        Usuario u = null;
        Connection con = null;
        String password_Encoded = encodeValue(example.getPassword(), "SHA");
        String username = example.getUsername();
        try {
            con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE username=? AND password=?");
            ps.setString(1,username);
            ps.setString(2,password_Encoded);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = buildUsuario(rs);
                if(u!=null){
                    return u;
                }
            }
            rs.close();
        } 
        catch (SQLException ex) {
            logger.error("Error al recuperar el usuario", ex);
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
        return u;
    }
    
    private String encodeValue(String value, String algorithm) {
        byte[] bytes = value.getBytes();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (Exception exception) {
            logger.error("Error getting MessageDigest instance", exception);
            return value;
        }
        messageDigest.reset();
        messageDigest.update(bytes);
        byte[] digestBytes = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b = 0; b < digestBytes.length; b++) {
            if ((digestBytes[b] & 0xFF) < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Long.toString((digestBytes[b] & 0xFF), 16));
        }
        return stringBuffer.toString();
    }
    
}
