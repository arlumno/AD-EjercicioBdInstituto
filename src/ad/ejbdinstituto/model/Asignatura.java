/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.model;

/**
 *
 * @author a20armandocb
 */
public class Asignatura {
     private int id;
    private String codigo;
    private String ciclo;

    public Asignatura(int id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.ciclo = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return ciclo;
    }

    public void setNombre(String nombre) {
        this.ciclo = nombre;
    }
    
}
