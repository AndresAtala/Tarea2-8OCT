package model.data.dao;
import model.Cafe;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.Table;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class CafeDao {

    public static boolean validarExistenciaCafe(DSLContext query, String columnaTabla, Object dato) {
        Result<Record> resultados = query.select().from(table("Cafe")).where(field(columnaTabla).eq(dato)).fetch();
        return resultados.size() >= 1;
    }

    public static void agregarCafe(DSLContext query, Cafe cafe) {
        Table tablaCafe = table(name("Cafe"));
        query.insertInto(tablaCafe,
                        field("nombre"),
                        field("cantidad_gramos_cafe"),
                        field("mililitros_agua"),
                        field("tamaño"),
                        field("crema"),
                        field("leche"),
                        field("chocolate"))
                .values(cafe.getNombre(),
                        cafe.getCantidadGramosCafe(),
                        cafe.getMililitrosAgua(),
                        cafe.getTamaño(),
                        cafe.isCrema(),
                        cafe.isLeche(),
                        cafe.isChocolate())
                .execute();
    }

    public static Cafe buscarCafe(DSLContext query, Object nombreCafe) {
        Result<Record> resultados = query.select().from(table("Cafe")).where(field("nombre").eq(nombreCafe)).fetch();

        if (resultados.isEmpty()) {
            return null;
        } else {
            Record record = resultados.get(0);
            String nombre = record.get("nombre", String.class);
            int cantidadGramosCafe = record.get("cantidad_gramos_cafe", Integer.class);
            int mililitrosAgua = record.get("mililitros_agua", Integer.class);
            String tamaño = record.get("tamaño", String.class);
            boolean crema = record.get("crema", Boolean.class);
            boolean leche = record.get("leche", Boolean.class);
            boolean chocolate = record.get("chocolate", Boolean.class);
            boolean discontinuado = record.get("discontinuado", boolean.class);

            Cafe cafe = new Cafe(nombre, cantidadGramosCafe, mililitrosAgua, tamaño, crema, leche, chocolate, discontinuado);
            return cafe;
        }
    }

    public String[] obtenerNombresCafes(DSLContext query) {
        Result<Record1<Object>> resultados = query.select(field("nombre")).from(table("Cafe")).fetch();
        List<String> nombres = new ArrayList<>();
        for (Record1<Object> record : resultados) {
            nombres.add(record.component1().toString());
        }
        return nombres.toArray(new String[0]);
    }
    public Cafe[] obtenerCafes(DSLContext query) {
        Result<Record> resultados = query.select().from(table("Cafe")).fetch();
        List<Cafe> cafes = new ArrayList<>();
        for (Record record : resultados) {
            String nombre = record.get("nombre", String.class);
            int cantidadGramosCafe = record.get("cantidad_gramos_cafe", Integer.class);
            int mililitrosAgua = record.get("mililitros_agua", Integer.class);
            String tamaño = record.get("tamaño", String.class);
            boolean crema = record.get("crema", Boolean.class);
            boolean leche = record.get("leche", Boolean.class);
            boolean chocolate = record.get("chocolate", Boolean.class);
            boolean discontinuado = record.get("discontinuado", boolean.class);
            Cafe cafe = new Cafe(nombre, cantidadGramosCafe, mililitrosAgua, tamaño, crema, leche, chocolate, discontinuado);
            cafes.add(cafe);
        }
        return cafes.toArray(new Cafe[0]);
    }

    public void actualizarCafe(DSLContext query, Cafe cafe) {
        query.update(table("Cafe"))
                .set(field("cantidad_gramos_cafe"), cafe.getCantidadGramosCafe())
                .set(field("mililitros_agua"), cafe.getMililitrosAgua())
                .set(field("tamaño"), cafe.getTamaño())
                .set(field("crema"), cafe.isCrema())
                .set(field("leche"), cafe.isLeche())
                .set(field("chocolate"), cafe.isChocolate())
                .where(field("nombre").eq(cafe.getNombre()))
                .execute();
    }
}
