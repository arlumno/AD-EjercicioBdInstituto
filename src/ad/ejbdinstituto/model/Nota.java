/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.model;

import java.time.LocalDate;

/**
 *
 * @author a20armandocb
 */
public class Nota {
    private String id;
    private Asignatura asignatura;
    private Alumno alumno;
    private LocalDate fecha;
    private float nota;

    public Nota(Asignatura asignatura, Alumno alumno, LocalDate fecha, float nota) {
        this.asignatura = asignatura;
        this.alumno = alumno;
        this.fecha = fecha;
        this.nota = nota;
    }
    
    public String getId() {
        return Integer.toString(asignatura.getId()) + Integer.toString(alumno.getId()) + fecha.toString();
    }
    
    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
    
    
}
