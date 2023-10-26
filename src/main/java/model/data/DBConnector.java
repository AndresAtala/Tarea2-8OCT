package model.data;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/";

    private static Connection connection = null;

    public static DSLContext createDSLContext(String dbName, String username, String password) {
        try {
            if (connection == null || connection.isClosed()) {
                connection = doConnection(dbName, username, password);
            }
            return DSL.using(connection, SQLDialect.MYSQL);
        } catch (SQLException e) {
            System.err.println("Error al comprobar si está cerrada la conexión: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Connection connection(String db, String username, String password) {
        try {
            if (connection == null || connection.isClosed()) {
                connection = doConnection(db, username, password);
            }
        } catch (SQLException e) {
            System.err.println("Error al comprobar si está cerrada la conexión: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection doConnection(String db, String username, String password) {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL + db, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al crear la conexión: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("Conexion creada : " + conn);
        return conn;
    }
}
