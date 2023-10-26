package model.data.dao;

import model.Cafeteria;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class CafeteriaDao {
    private final DSLContext query;

    public CafeteriaDao(DSLContext query) {
        this.query = query;
    }

    public static void actualizarCafeteria(DSLContext query, Cafeteria cafeteria) {
        query.update(table("Cafeteria"))
                .set(field("direccion"), cafeteria.getDireccion())
                .set(field("redes_sociales"), cafeteria.getRedesSociales())
                .where(field("nombre").eq(cafeteria.getNombre()))
                .execute();
    }

    public static Cafeteria buscarCafe(DSLContext query, String nombreCafe) {
        Result<Record> result = query.select()
                .from(table("Cafeteria"))
                .where(field("nombre").eq(nombreCafe))
                .fetch();

        if (!result.isEmpty()) {
            Record record = result.get(0);
            String nombre = record.get("nombre", String.class);
            String direccion = record.get("direccion", String.class);
            String redesSociales = record.get("redes_sociales", String.class);
            return new Cafeteria(nombre, direccion, redesSociales);
        } else {
            return null;
        }
    }

    public static void descontinuarCafe(DSLContext query, String nombreCafeteria) {
        query.update(table("Cafeteria"))
                .set(field("discontinuado"), true)
                .where(field("nombre").eq(nombreCafeteria))
                .execute();
    }
    public List<String> obtenerNombresCafeterias() {
        try {
            Result<Record1<Object>> result = query.select(field("nombre")).from(table("Cafeteria")).fetch();
            return result.stream().map(record -> record.get(0).toString()).collect(Collectors.toList());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
