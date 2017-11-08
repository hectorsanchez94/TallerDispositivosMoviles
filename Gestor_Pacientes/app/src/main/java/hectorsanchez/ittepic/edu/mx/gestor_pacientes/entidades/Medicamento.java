package hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades;

import java.io.Serializable;

/**
 * Created by Hector on 01/10/17.
 */

public class Medicamento implements Serializable {

    private Integer id;
    private String nombre_m;
    private String padecimiento;
    private String instrucciones;
    private String fecha_consulta;
    private String fecha_inicio;
    private String fecha_fin;
    private boolean vigente;
    private int id_paciente;



    public Medicamento(Integer id, String nombre_m, String padecimiento, String instrucciones, String fecha_consulta, String fecha_inicio, String fecha_fin, boolean vigente) {
        this.id = id;
        this.nombre_m = nombre_m;
        this.padecimiento = padecimiento;
        this.instrucciones = instrucciones;
        this.fecha_consulta = fecha_consulta;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.vigente = vigente;
    }

    public Medicamento(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_m() {
        return nombre_m;
    }

    public void setNombre_m(String nombre_m) {
        this.nombre_m = nombre_m;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(String fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}


