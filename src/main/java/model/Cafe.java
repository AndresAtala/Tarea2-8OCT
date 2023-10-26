package model;

public class Cafe {
    private String nombre;
    private int cantidadGramosCafe;
    private int mililitrosAgua;
    private String tamaño;
    private boolean crema;
    private boolean leche;
    private boolean chocolate;
    private boolean discontinuado;

    public Cafe(String nombre, int cantidadGramosCafe, int mililitrosAgua, String tamaño, boolean crema, boolean leche, boolean chocolate, boolean discontinuado) {
        this.nombre = nombre;
        this.cantidadGramosCafe = cantidadGramosCafe;
        this.mililitrosAgua = mililitrosAgua;
        this.tamaño = tamaño;
        this.crema = crema;
        this.leche = leche;
        this.chocolate = chocolate;
        this.discontinuado = discontinuado;
    }
    public Cafe() {
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadGramosCafe() {
        return cantidadGramosCafe;
    }

    public void setCantidadGramosCafe(int cantidadGramosCafe) {
        this.cantidadGramosCafe = cantidadGramosCafe;
    }

    public int getMililitrosAgua() {
        return mililitrosAgua;
    }

    public void setMililitrosAgua(int mililitrosAgua) {
        this.mililitrosAgua = mililitrosAgua;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public boolean isCrema() {
        return crema;
    }

    public void setCrema(boolean crema) {
        this.crema = crema;
    }

    public boolean isLeche() {
        return leche;
    }

    public void setLeche(boolean leche) {
        this.leche = leche;
    }

    public boolean isChocolate() {
        return chocolate;
    }

    public void setChocolate(boolean chocolate) {
        this.chocolate = chocolate;
    }
    public void setDiscontinuado(boolean discontinuado) {
        this.discontinuado = discontinuado;
    }
    public boolean isDiscontinuado() {
        return discontinuado;
    }
}
