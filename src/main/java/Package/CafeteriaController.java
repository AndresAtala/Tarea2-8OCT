package Package;

import model.Cafeteria;
import model.data.dao.CafeteriaDao;
import model.data.dao.CafeDao;
import org.jooq.DSLContext;
import java.util.List;

public class CafeteriaController {
    private CafeteriaDao cafeteriaDao;
    private DSLContext query;

    public CafeteriaController(DSLContext query) {
        this.query = query;
        this.cafeteriaDao = new CafeteriaDao(query);
    }

    public void modificarCafeteria(String nombreCafeteria, String nuevaDireccion, String nuevasRedesSociales) {
        Cafeteria cafeteria = new Cafeteria(nombreCafeteria, nuevaDireccion, nuevasRedesSociales);
        cafeteriaDao.actualizarCafeteria(query, cafeteria);
    }

    public model.Cafe buscarCafe(String nombreCafe) {
        return CafeDao.buscarCafe(query, nombreCafe);
    }

    public void descontinuarCafe(String nombreCafe) {
        cafeteriaDao.descontinuarCafe(query, nombreCafe);
    }
    public String[] obtenerNombresCafeterias() {
        List<String> nombresCafeterias = cafeteriaDao.obtenerNombresCafeterias();
        return nombresCafeterias.toArray(new String[0]);
    }
}
