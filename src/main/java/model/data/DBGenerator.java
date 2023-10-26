package model.data;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.impl.DSL.foreignKey;
import static org.jooq.impl.DSL.primaryKey;
import static org.jooq.impl.SQLDataType.INTEGER;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class DBGenerator {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void iniciarBD(String nombreBD) {
        DSLContext create = DBConnector.createDSLContext("", USERNAME, PASSWORD);
        crearBaseDato(create, nombreBD);
        create = DBConnector.createDSLContext(nombreBD, USERNAME, PASSWORD);
        crearTablaCafe(create);
        crearTablaCafeteria(create);
    }

    private static Connection conectarBaseDatos() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) throws SQLException {
        DBConnector.closeConnection(connection);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombreBD, USERNAME, PASSWORD);
        return DSL.using(connection);
    }

    private static void crearTablaCafe(DSLContext create) { //para mañana cambiar esto//
        create.createTableIfNotExists("Cafe")
                .column("nombre", VARCHAR(100))
                .column("cantidad_gramos_cafe", INTEGER)
                .column("mililitros_agua", INTEGER)
                .column("tamaño", VARCHAR(50))
                .column("crema", INTEGER)
                .column("leche", INTEGER)
                .column("chocolate", INTEGER)
                .constraint(primaryKey("nombre"))
                .execute();
    }

    private static void crearTablaCafeteria(DSLContext create) { // esto tambien//
        create.createTableIfNotExists("Cafeteria")
                .column("nombre", VARCHAR(100))
                .column("direccion", VARCHAR(100))
                .column("redes_sociales", VARCHAR(100))
                .constraint(primaryKey("nombre"))
                .execute();
    }

    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTableIfExists(nombreTabla)
                .alterConstraint(foreignKey(claveForanea)
                        .references(nombreTablaRelacion))
                .enforced();
    }
}
