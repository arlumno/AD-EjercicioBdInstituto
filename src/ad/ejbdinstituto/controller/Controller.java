/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.controller;

import ad.ejbdinstituto.DAO.AlumnoDaoImp;
import ad.ejbdinstituto.DAO.ProfesorDaoImp;
import ad.ejbdinstituto.model.Alumno;
import ad.ejbdinstituto.model.Profesor;
import java.util.List;

/**
 *
 * @author Arsito
 */
public class Controller {
    //ALUMNO
    public static boolean crearAlumno(Alumno alumno){
        return (new AlumnoDaoImp()).create(alumno);
    }
    public static boolean borrarAlumno(Alumno alumno){
        boolean resultado = false;
        if(alumno.getId() != null){
            resultado = (new AlumnoDaoImp()).delete(alumno);
        }
        return resultado;
    }
    
    public static boolean modificarAlumno(Alumno alumno){
        boolean resultado = false;
        if(alumno.getId() != null){
            resultado = (new AlumnoDaoImp()).update(alumno);
        }
        return resultado;
    }
    
    public static Alumno obtenerAlumno(String codigo){
        return (new AlumnoDaoImp()).read(codigo);
    }
    public static List<Alumno> obtenerAlumnos(){
        return (new AlumnoDaoImp()).readAll();
    }
    
    //PROFESOR
     public static boolean crearProfesor(Profesor profesor){
        return (new ProfesorDaoImp()).create(profesor);
    }
     
     public static Profesor obtenerProfesor(String dni){
        return (new ProfesorDaoImp()).read(dni);
    }
     public static boolean borrarProfesor(Profesor profesor){        
        return (new ProfesorDaoImp()).delete(profesor);
    }
      public static List<Profesor> obtenerProfesores(){
        return (new ProfesorDaoImp()).readAll();
    }
}
