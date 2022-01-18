/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.controller;

import ad.ejbdinstituto.DAO.AlumnoDaoImp;
import ad.ejbdinstituto.DAO.AsignaturaDaoImp;
import ad.ejbdinstituto.DAO.MatriculaDaoImp;
import ad.ejbdinstituto.DAO.NotaDaoImp;
import ad.ejbdinstituto.DAO.ProfesorDaoImp;
import ad.ejbdinstituto.Exceptions.NoIdException;
import ad.ejbdinstituto.model.Alumno;
import ad.ejbdinstituto.model.Asignatura;
import ad.ejbdinstituto.model.Matricula;
import ad.ejbdinstituto.model.Nota;
import ad.ejbdinstituto.model.Profesor;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Arsito
 */
public class Controller {

    //ALUMNO
    public static boolean crearAlumno(Alumno alumno) {
        return (new AlumnoDaoImp()).create(alumno);
    }

    public static boolean borrarAlumno(Alumno alumno) {
        boolean resultado = false;
        resultado = (new AlumnoDaoImp()).delete(alumno);
        return resultado;
    }

    public static boolean modificarAlumno(Alumno alumno) {
        boolean resultado = false;
        resultado = (new AlumnoDaoImp()).update(alumno);
        return resultado;
    }

    public static Alumno obtenerAlumno(String codigo) {
        return (new AlumnoDaoImp()).read(codigo);
    }

    public static List<Alumno> obtenerAlumnos() {
        return (new AlumnoDaoImp()).readAll();
    }

    //PROFESOR
    public static boolean crearProfesor(Profesor profesor) {
        return (new ProfesorDaoImp()).create(profesor);
    }

    public static Profesor obtenerProfesor(String dni) {
        return (new ProfesorDaoImp()).read(dni);
    }

    public static boolean borrarProfesor(Profesor profesor) {
        return (new ProfesorDaoImp()).delete(profesor);
    }

    public static List<Profesor> obtenerProfesores() {
        return (new ProfesorDaoImp()).readAll();
    }

    public static List<Asignatura> obtenerAsignaturasProfesor(Profesor profesor) {
        return (new AsignaturaDaoImp()).readByProfesor(profesor.getDni());
    }

    //ASIGNATURA
    public static boolean crearAsignatura(Asignatura asignatura) {
        return (new AsignaturaDaoImp()).create(asignatura);
    }

    public static boolean borrarAsignatura(Asignatura asignatura) {
        boolean resultado = false;
        resultado = (new AsignaturaDaoImp()).delete(asignatura);
        return resultado;
    }

    public static boolean modificarAsignatura(Asignatura asignatura) {
        boolean resultado = false;
        resultado = (new AsignaturaDaoImp()).update(asignatura);
        return resultado;
    }

    public static Asignatura obtenerAsignatura(String codigo) {
        return (new AsignaturaDaoImp()).read(codigo);
    }

    public static List<Asignatura> obtenerAsignaturas() {
        return (new AsignaturaDaoImp()).readAll();
    }

    public static List<Profesor> obtenerProfesoresAsignatura(Asignatura asignatura) {
        try {
            return (new ProfesorDaoImp()).readByAsignatura(asignatura.getId());
        } catch (NoIdException ex) {
            return null;
        }
    }

    //NOTAS    
    public static boolean crearNota(Nota nota) {
        
        return (new NotaDaoImp()).create(nota);
    }

    public static Nota obtenerNota(Asignatura asignatura, Alumno alumno, LocalDate fecha) {
        try {
            return (new NotaDaoImp()).read(asignatura.getId(), alumno.getId(), fecha.toString());
        } catch (NoIdException ex) {
            return null;
        }

    }

    public static List<Nota> obtenerNotasAlumno(Alumno alumno) {
        try {
            return (new NotaDaoImp()).readByAlumno(alumno.getId());
        } catch (NoIdException ex) {
            return null;
        }

    }

    public static boolean modificarNota(Nota nota) {
        return (new NotaDaoImp()).update(nota);
    }

    //MATRICULA
    public static boolean altaMatricula(Matricula matricula) {
        return (new MatriculaDaoImp()).create(matricula);

    }

}
