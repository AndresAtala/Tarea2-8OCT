package Package;

import model.Cafe;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import model.data.dao.CafeDao;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class CafeController {
    private CafeDao cafeDao;
    private DSLContext query;

    public CafeController(DSLContext query) {
        this.cafeDao = new CafeDao();
        this.query = query;
    }

    public String[] obtenerNombresCafes(DSLContext query) {
        return cafeDao.obtenerNombresCafes(this.query);
    }

    public void agregarCafe(String nombre, int cantidadGramosCafe, int mililitrosAgua, String tamaño, boolean crema, boolean leche, boolean chocolate, boolean discontinuado) {
        Cafe nuevoCafe = new Cafe(nombre, cantidadGramosCafe, mililitrosAgua, tamaño, crema, leche, chocolate, discontinuado);
        cafeDao.agregarCafe(query, nuevoCafe);
    }

    public Cafe buscarCafe(String nombreCafe) {
        return cafeDao.buscarCafe(query, nombreCafe);
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
            boolean discontinuado = record.get("discontinuado", Boolean.class); // Agregar el campo discontinuado
            Cafe cafe = new Cafe(nombre, cantidadGramosCafe, mililitrosAgua, tamaño, crema, leche, chocolate, discontinuado);
            cafe.setDiscontinuado(discontinuado); // Establecer el valor de discontinuado
            cafes.add(cafe);
        }
        return cafes.toArray(new Cafe[0]);
    }

    public void descontinuarCafe(String nombreCafe) {
        Cafe cafe = buscarCafe(nombreCafe);
        if (cafe != null) {
            cafe.setDiscontinuado(true);
            cafeDao.actualizarCafe(query, cafe);
        } else {
            System.out.println("El café no existe en la cafetería.");
        }
    }

    public void modificarCafe(String nombreCafe, int nuevaCantidadGramosCafe, int nuevosMililitrosAgua, String nuevoTamaño, boolean nuevaCrema, boolean nuevaLeche, boolean nuevoChocolate) {
        Cafe cafe = buscarCafe(nombreCafe);
        if (cafe != null) {
            cafe.setCantidadGramosCafe(nuevaCantidadGramosCafe);
            cafe.setMililitrosAgua(nuevosMililitrosAgua);
            cafe.setTamaño(nuevoTamaño);
            cafe.setCrema(nuevaCrema);
            cafe.setLeche(nuevaLeche);
            cafe.setChocolate(nuevoChocolate);
            cafeDao.actualizarCafe(query, cafe);
        } else {
            System.out.println("El café no existe en la cafetería.");
        }
    }
    public void actualizarCafe(DSLContext query, Cafe cafe) {
        query.update(table("Cafe"))
                .set(field("cantidad_gramos_cafe"), cafe.getCantidadGramosCafe())
                .set(field("mililitros_agua"), cafe.getMililitrosAgua())
                .set(field("tamaño"), cafe.getTamaño())
                .set(field("crema"), cafe.isCrema())
                .set(field("leche"), cafe.isLeche())
                .set(field("chocolate"), cafe.isChocolate())
                .set(field("discontinuado"), cafe.isDiscontinuado()) // Agregar el campo discontinuado
                .where(field("nombre").eq(cafe.getNombre()))
                .execute();
    }

}
