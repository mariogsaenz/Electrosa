package es.unirioja.paw.service;

import es.unirioja.paw.service.data.MailRequest;
import es.unirioja.paw.service.data.MailResponse;

/**
 * Envío de correos electrónicos
 */
public interface Mailer {
    
    /**
     * @param request Datos para enviar el correo
     * @return Resultado del envio 
     */
    public MailResponse send(MailRequest request);

}
