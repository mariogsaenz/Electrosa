package es.unirioja.paw.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String DATASOURCE_NAME = "java:comp/env/jdbc/electrosa";
    private static DataSource ds;

    static {
        try {
            //Intento obtener el DataSource desde el entorno
            ds = (DataSource) new InitialContext().lookup(DATASOURCE_NAME);
        } catch (NamingException ex) {
            logger.error("Error looking up datasource", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (ds == null) {
            logger.info("Getting normal connection {}", ConnectionProperties.URL);
            return DriverManager.getConnection(ConnectionProperties.URL, ConnectionProperties.USER, ConnectionProperties.PWD);
        } else {
            logger.info("Getting connection from pool: {}", DATASOURCE_NAME);
            return ds.getConnection();
        }
    }

    public static void returnConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
