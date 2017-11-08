package hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades;

import java.io.Serializable;

/**
 * Created by Hector on 01/10/17.
 */

public class Usuario implements Serializable {

    private Integer id;
    private String nombre;
    private String direccion;
    private String celular;
    private String email;
    private String fecha;

    public Usuario(Integer id, String nombre, String direccion, String celular, String email, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.celular = celular;
        this.email = email;
        this.fecha = fecha;
    }

    public Usuario(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
