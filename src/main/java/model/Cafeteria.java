package model;

public class Cafeteria {
    private String nombre;
    private String direccion;
    private String redesSociales;

    public Cafeteria() {
    }
    public Cafeteria(String nombre, String direccion, String redesSociales) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.redesSociales = redesSociales;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(String redesSociales) {
        this.redesSociales = redesSociales;
    }
}

