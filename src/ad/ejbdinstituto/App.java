/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Menu;


/**
 *
 * @author a20armandocb
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            utilidades.Log.getInstance().setDir("src\\");
        } catch (Exception ex) {            
            System.out.println("Error: " + ex);
        }
        utilidades.Log.getInstance().addToLog("Aplicación iniciada");
        
         boolean continuar = true;
        
        try {
            Menu menu = construirMenuPrincipal();
            do {
                continuar = menuAcciones(menu);
            } while (continuar);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        utilidades.Log.getInstance().addToLog("Aplicación finalizada");     
        System.exit(0);
    }
    
    private static Menu construirMenuPrincipal() throws Exception{
        AccionesApp accionesApp = new AccionesApp();
        Menu menu = new Menu();        
        menu.setTituloMenu("Menú Base Datos");
        menu.setTextoSalir("Salir");
        
        menu.addLabel("Creación BD");
        menu.addOpcion("Crear Base de Datos", ()-> { accionesApp.crearBD();});
        menu.addOpcion("ELIMINAR Base de Datos", ()-> { accionesApp.borrarBD();});
        
        menu.addLabel("Altas");
        menu.addOpcion("Nuevo Profesor", ()-> { accionesApp.altaProfesor();});
        menu.addOpcion("Nuevo Alumno", ()-> { accionesApp.altaAlumno();});
        menu.addOpcion("Nueva Asignatura", ()-> { accionesApp.altaAsignatura();});
        menu.addOpcion("Poner Nota", ()-> { accionesApp.altaNota();});
        menu.addOpcion("Matricular", ()-> { accionesApp.altaMatricula();});
        
        
        menu.addLabel("Bajas");
        menu.addOpcion("Eliminar Profesor", ()-> { accionesApp.borrarProfesor();});
        menu.addOpcion("Eliminar Alumno", ()-> { accionesApp.borrarAlumno();});
        
        menu.addLabel("Modificaciones");
        menu.addOpcion("Alumno", ()-> { accionesApp.modificarAlumno();});
        
        
        menu.addLabel("Consultar y Listar");        
        menu.addOpcion("Consultar Alumno", ()-> { accionesApp.obtenerAlumno();});
        menu.addOpcion("Listar Alumnos", ()-> { accionesApp.listarAlumnos();});
        menu.addOpcion("Consultar Profesor", ()-> { accionesApp.obtenerProfesor();});
        menu.addOpcion("Listar Profesores", ()-> { accionesApp.listarProfesores();});
        menu.addOpcion("Consultar Asignatura", ()-> { accionesApp.obtenerAsignatura();});
        menu.addOpcion("Listar Asignatura", ()-> { accionesApp.listarAsignaturas();});
        
        
        menu.addLabel("Log");
        menu.addOpcion("Ver Log", ()-> { accionesApp.verLog();});
        menu.addOpcion("Borrar Log", ()-> { accionesApp.borrarLog();});
        
        return menu;
    }
 
    
    private static boolean menuAcciones(Menu menu) {
        boolean continuar = true;
        try {
            menu.mostrarGUI();
            switch (menu.getSeleccion()) {
                case 0:
                    //salir
                    continuar = false;               
                    break;
            }
        } catch (Exception e) {
            continuar = false;  
            System.out.println("Error: " + e);
        }
        return continuar;
    }

  
}
