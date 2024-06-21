package es.unirioja.paw.service;

import es.unirioja.paw.jpa.ClienteEntity;

/**
 * Servicio de autenticacion de usuarios
 */
public interface AuthService {

    /**
     * Comprueba que el usuario y password indicados son correctos
     * @param username login de usuario
     * @param password contraseña
     * @return Cliente si la autenticacion es correcta, null en cualquier otro caso
     */
    public ClienteEntity authenticate(String username, String password);

}
