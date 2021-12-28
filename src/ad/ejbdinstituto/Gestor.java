/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import ad.ejbdinstituto.Exceptions.InvalidDataException;
import ad.ejbdinstituto.controller.Controller;
import ad.ejbdinstituto.model.Alumno;
import ad.ejbdinstituto.model.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a20armandocb
 */
public class Gestor {

    public Gestor() {

    }

    public void crearBD() {
        EstructuraBD estructura = new EstructuraBD();
        if (peticiones.EntradasGui.pedirBoolean("Vas a proceder a crear la base da datos.Todos los datos anteriores se perderán.\n¿Confirmar operación?")) {
            estructura.build();
            peticiones.SalidasGui.mensaje("Operación realizada con éxito");
        } else {
            peticiones.SalidasGui.mensaje("Operación Cancelada");
        }
    }

    public void borrarBD() {
        EstructuraBD estructura = new EstructuraBD();
        if (peticiones.EntradasGui.pedirBoolean("Vas a proceder a ELIMINAR la base da datos.\n¿Confirmar operación?")) {
            estructura.borrarBd();
            peticiones.SalidasGui.mensaje("Operación realizada con éxito");
        } else {
            peticiones.SalidasGui.mensaje("Operación Cancelada");
        }
    }

    public void verLog() {
        peticiones.SalidasGui.bloqueTexto(utilidades.Log.getInstance().getLog());
    }

    public void borrarLog() {
        utilidades.Log.getInstance().borrarLog();
        peticiones.SalidasGui.mensaje("Log Borrado");
    }

    public void altaAlumno() {
        boolean error;
        String nombre = null;
        String codigo = null;
        do {
            error = false;
            try {
                nombre = peticiones.EntradasGui.pedirString("Indica el nombre del Alumno", Alumno.MAX_SIZE_NOMBRE, Alumno.MIN_SIZE_NOMBRE, false);
                if (nombre != null) {
                    codigo = peticiones.EntradasGui.pedirString("Indica el codigo del Alumno.\n Deben ser 4 caracteres y el ultimo una letra Mayúscula (Ej. ar1Z)", Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
                }
                if (nombre != null && codigo != null) {
                    Alumno alumno = new Alumno(codigo, nombre);
                    if (Controller.crearAlumno(alumno)) {
                        peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                    } else {
                        peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                        error = true;
                    }
                }
            } catch (InvalidDataException ex) {
                error = true;
                peticiones.SalidasGui.mensaje("Error al crear el alumno. Datos inválidos");
            }
        } while (error);

    }

    public void obtenerAlumno() {
        String codigo = peticiones.EntradasGui.pedirString("Indica el codigo del Alumno.", Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
        Alumno alumno = Controller.obtenerAlumno(codigo);
        peticiones.SalidasGui.mensaje(alumno.toString());
    }

    public void listarAlumnos() {
        List<Alumno> alumnos = Controller.obtenerAlumnos();
        StringBuilder listadoAlumnos = new StringBuilder();
        for (Alumno alumno : alumnos) {
            listadoAlumnos.append(alumno.toString() + "\n");
        }
        peticiones.SalidasGui.bloqueTexto(listadoAlumnos.toString());
    }

    void modificarAlumno() {
        String codigo = peticiones.EntradasGui.pedirString("Indica el codigo del Alumno que quieres Modificar.", Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
        Alumno alumno = null;
        if ((alumno = Controller.obtenerAlumno(codigo)) != null) {
            String nombre = peticiones.EntradasGui.pedirString("Indica el nuevo nombre del Alumno \n -- Antes: " + alumno.getNombre() + " --", Alumno.MAX_SIZE_NOMBRE, Alumno.MIN_SIZE_NOMBRE, false);
            alumno.setNombre(nombre);
            if (Controller.modificarAlumno(alumno)) {
                peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
            } else {
                peticiones.SalidasGui.mensaje("Error al realizar la Operación");
            }
        } else {
            peticiones.SalidasGui.mensaje("Código introducido NO VÁLIDO");
        }
    }

    void borrarAlumno() {
        String codigo = peticiones.EntradasGui.pedirString("Indica el codigo del Alumno que quieres ELIMINAR.", Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
        Alumno alumno = null;
        if ((alumno = Controller.obtenerAlumno(codigo)) != null) {
            if (peticiones.EntradasGui.pedirBoolean("Seguro que quieres eliminar al alumno " + alumno.getNombre() + " (" + alumno.getCodigo() + ")")) {
                if (Controller.borrarAlumno(alumno)) {
                    peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                } else {
                    peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                }
            }
        } else {
            peticiones.SalidasGui.mensaje("Código introducido NO VÁLIDO");
        }
    }

    public void altaProfesor() {
        boolean error;
        String nombre = null;
        String dni = null;
        String titulacion = null;
        do {
            error = false;
            try {
                nombre = peticiones.EntradasGui.pedirString("Indica el nombre del Profesor", Profesor.MAX_SIZE_NOMBRE, Profesor.MIN_SIZE_NOMBRE, false);
                if (nombre != null) {
                    dni = peticiones.EntradasGui.pedirString("Indica el Dni del Profesor. Sin espacio ni guiones, y con letra.", Profesor.MAX_SIZE_DNI, Profesor.MIN_SIZE_DNI, false);
                    if (dni != null) {
                        titulacion = peticiones.EntradasGui.pedirString("Indica la titulación del Profesor. Max. " + Profesor.MAX_SIZE_TITULACION + " caracteres.", Profesor.MAX_SIZE_TITULACION, Profesor.MIN_SIZE_TITULACION, false);
                    }
                }
                if (nombre != null && dni != null && titulacion != null) {
                    Profesor profesor = new Profesor(dni, nombre, titulacion);
                    if (Controller.crearProfesor(profesor)) {
                        peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                    } else {
                        peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                        error = true;
                    }
                }
            } catch (Exception e) {
                error = true;
                peticiones.SalidasGui.mensaje("Error al crear el profesor. Datos inválidos");
            }
        } while (error);

    }

}
