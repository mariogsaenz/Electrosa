package es.unirioja.paw.service.data;

import java.util.Properties;

/**
 * Configuración del servicio de envío de mails
 */
public class MailConfig {

    private final Properties configProperties;

    public MailConfig(Properties configProperties) {
        this.configProperties = configProperties;
    }

    public Properties getProperties() {
        return configProperties;
    }

    public String getUsername() {
        return configProperties.getProperty("mail.smtp.user");
    }

    public String getPassword() {
        return configProperties.getProperty("mail.smtp.password");
    }

}
