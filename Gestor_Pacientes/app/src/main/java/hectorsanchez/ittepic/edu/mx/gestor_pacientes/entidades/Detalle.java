package hectorsanchez.ittepic.edu.mx.gestor_pacientes.entidades;

import java.io.Serializable;

/**
 * Created by Hector on 02/10/17.
 */

public class Detalle implements Serializable {
    private Integer id_cliente;

    public Detalle(Integer id_cliente, Integer id_medicamento){
        this.id_cliente = id_cliente;
        this.id_medicamento = id_medicamento;
    }

    public Detalle(){

    }
    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(Integer id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    private Integer id_medicamento;
}


