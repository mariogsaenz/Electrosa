package es.unirioja.paw.service.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Datos para enviar el mail
 */
public class MailRequest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Direccion email del destinatario
     */
    public final String recipient;

    /**
     * Asunto
     */
    public final String subject;

    /**
     * Cuerpo del mail
     */
    public final String content;

    public final String contentType = "text/html; charset=utf-8";

    public MailRequest(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    public boolean isComplete() {
        if (recipient == null || recipient.isEmpty()) {
            logger.warn("Campo 'recipient' null o vacio");
            return false;
        }
        if (subject == null || subject.isEmpty()) {
            logger.warn("Campo 'subject' null o vacio");
            return false;
        }
        if (content == null || content.isEmpty()) {
            logger.warn("Campo 'content' null o vacio");
            return false;
        }
        return true;
    }

}
