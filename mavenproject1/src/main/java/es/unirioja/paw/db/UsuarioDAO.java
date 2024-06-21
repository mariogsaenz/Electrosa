/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unirioja.paw.db;

import es.unirioja.paw.model.ExcepcionDeAplicacion;
import es.unirioja.paw.model.Usuario;

/**
 *
 * @author ASUS
 */
public interface UsuarioDAO {
    /**
     * @param example Usuario
     * @return Usuario
     * @throws ExcepcionDeAplicacion 
     */
    public Usuario findUsuarioByExample(Usuario example) throws ExcepcionDeAplicacion;
}
