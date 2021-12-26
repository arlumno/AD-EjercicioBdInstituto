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
public class Profesor {

    private String dni;
    private String nombre;
    private String titulacion;
    public static int MAX_SIZE_DNI = 9;
    public static int MIN_SIZE_DNI = 9;
    public static int MAX_SIZE_NOMBRE = 50;
    public static int MIN_SIZE_NOMBRE = 3;
    public static int MAX_SIZE_TITULACION = 1;
    public static int MIN_SIZE_TITULACION = 50;

    public Profesor(String dni, String nombre, String titulacion) {
        setDni(dni);
        this.nombre = nombre;
        this.titulacion = titulacion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni.toUpperCase();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

}
