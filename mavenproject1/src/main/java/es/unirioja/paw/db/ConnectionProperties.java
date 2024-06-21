package es.unirioja.paw.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionProperties {

    private static final String propertiesPath = "/conexion.properties";

    private static final Logger logger = LoggerFactory.getLogger(ConnectionProperties.class);

    private static final Properties properties = getConfig();

    // Driver
    public static final String DRIVERCLASS = properties.getProperty("driverClass");

    // Url a la BD
    public static String URL = properties.getProperty("url");

    // Usuario
    public static String USER = properties.getProperty("user");

    // Password
    public static String PWD = properties.getProperty("pwd");

    private static Properties getConfig() {
        Properties config = new Properties();

        logger.error("Reading properties from file: {}", propertiesPath);
        try {
            InputStream prIS = ConnectionProperties.class.getResourceAsStream(propertiesPath);
//            Debug.check(prIS, "No pudo cargarse el fichero de propiedades conexion.properties (situalo en la raiz)");
            config.load(prIS);
        } catch (IOException ioe) {
//            Debug.warning("Error al inicializar las constantes de conexion a la BD: " + ioe.getMessage(), ioe);
            logger.error("Error reading properties", ioe);
        }
        return config;
    }
}
