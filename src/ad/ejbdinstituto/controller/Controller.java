/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.controller;

import ad.ejbdinstituto.DAO.AlumnoDaoImp;
import ad.ejbdinstituto.model.Alumno;

/**
 *
 * @author Arsito
 */
public class Controller {
    public static boolean crearAlumno(Alumno alumno){
        return (new AlumnoDaoImp()).create(alumno);
    }
    public static boolean borrarAlumno(Alumno alumno){
        return (new AlumnoDaoImp()).delete(alumno);
    }
    public static Alumno obtenerAlumno(String id){
        return (new AlumnoDaoImp()).read(id);
    }
}
