package ittepic.edu.mx.sqlite;

/**
 * Created by Hector on 28/02/17.
 */

public class Datos {
    protected String definicion,concepto;
    protected Integer ID;

    public Datos(Integer idalumno, String nombre, String direccion){
        this.ID = idalumno;
        this.concepto = nombre;
        this.definicion = direccion;

    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }



}
