/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unirioja.paw.db;

import es.unirioja.paw.model.Cliente;
import es.unirioja.paw.model.ExcepcionDeAplicacion;

/**
 *
 * @author ASUS
 */
public interface ClienteDAO {
    /**
     * @param username Nombre de usuario
     * @return Cliente
     * @throws ExcepcionDeAplicacion 
     */
    public Cliente findByUsername(String username) throws ExcepcionDeAplicacion;
    
    public Cliente findByCIF(String cif) throws ExcepcionDeAplicacion;
    
    public Cliente register(Cliente c, String contraseña) throws ExcepcionDeAplicacion;
}
