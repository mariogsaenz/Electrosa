package es.unirioja.paw.service;

import es.unirioja.paw.service.data.MailRequest;
import es.unirioja.paw.service.data.MailResponse;
import es.unirioja.paw.service.data.MailConfig;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaxMailer implements Mailer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MailConfig mailConfig;

    public JavaxMailer(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    @Override
    public MailResponse send(MailRequest request) {

        if (!request.isComplete()) {
            return new MailResponse(false);
        }

        Properties properties = mailConfig.getProperties();
        String username = mailConfig.getUsername();
        String password = mailConfig.getPassword();
        boolean success = true;
        Session session = Session.getInstance(properties);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(request.recipient)
            );
            message.setSubject(request.subject);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(request.content, request.contentType);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message, username, password);
        } catch (Exception ex) {
            logger.error("Error building message", ex);
            success = false;
        }
        return new MailResponse(success);
    }

}
