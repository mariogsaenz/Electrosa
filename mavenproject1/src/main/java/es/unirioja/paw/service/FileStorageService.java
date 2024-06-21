package es.unirioja.paw.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    /**
     * Guarda un fichero en una ruta física
     *
     * @param file El fichero origen
     * @param targetPath Ruta destino donde guardar el fichero
     */
    public void store(MultipartFile file, String targetPath);

}
