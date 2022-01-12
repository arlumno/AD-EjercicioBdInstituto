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
public class Matricula {

    private Profesor profesor;
    private Asignatura asignatura;
    private Alumno alumno;

    public Matricula(Profesor profesor, Asignatura asignatura, Alumno alumno) {
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.alumno = alumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

}
