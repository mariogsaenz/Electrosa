package es.unirioja.paw.service.data;

import org.springframework.web.multipart.MultipartFile;

public class AvatarClienteChangeRequest {

    public final String codigoCliente;
    public final MultipartFile file;
    public final String realContextPath;

    /**
     *
     * @param codigoCliente Código del cliente al que asociar la imagen
     * @param file Fichero subido
     * @param realContextPath Ruta real (física) del contexto de la aplicación
     */
    public AvatarClienteChangeRequest(String codigoCliente, MultipartFile file, String realContextPath) {
        this.codigoCliente = codigoCliente;
        this.file = file;
        this.realContextPath = realContextPath;
    }

}
