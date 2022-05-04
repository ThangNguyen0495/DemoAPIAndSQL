package utilities.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utilities.links.LinksAndAccount.*;

public class DBConfiguration {
    public Connection createConnection() throws SQLException {
        String connectionUrl =
                "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT + ";"
                        + "database=" + DB_DATABASE + ";"
                        + "user=" + DB_USER + ";"
                        + "password=" + DB_PASSWORD + ";"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        return DriverManager.getConnection(connectionUrl);

    }
}
