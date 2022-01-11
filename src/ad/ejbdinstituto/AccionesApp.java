/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import ad.ejbdinstituto.Exceptions.InvalidDataException;
import ad.ejbdinstituto.controller.Controller;
import ad.ejbdinstituto.model.Alumno;
import ad.ejbdinstituto.model.Asignatura;
import ad.ejbdinstituto.model.Matricula;
import ad.ejbdinstituto.model.Nota;
import ad.ejbdinstituto.model.Profesor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import peticiones.EntradasGui;

/**
 *
 * @author a20armandocb
 */
public class AccionesApp {

    private final String PEDIR_COD_ALUMNO = "Indica el código del Alumno.\n Deben ser 4 caracteres y el ultimo una letra Mayúscula (Ej. ar1Z)";
    private final String PEDIR_DNI_PROFESOR = "Indica el Dni del Profesor. Sin espacio ni guiones, y con letra.";
    private final String PEDIR_COD_ASIGNATURA = "Indica el codigo de la Asignatura.\n Deben ser 4 caracteres y el ultimo una letra Mayúscula (Ej. ar1Z)";

    public AccionesApp() {

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

    // ALUMNOS
    public void altaAlumno() {
        boolean error;
        String nombre = null;
        String codigo = null;
        do {
            error = false;
            try {
                codigo = peticiones.EntradasGui.pedirString(PEDIR_COD_ALUMNO, Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
                if (Controller.obtenerAlumno(codigo) == null) {

                    if (codigo != null) {
                        nombre = peticiones.EntradasGui.pedirString("Indica el nombre del Alumno", Alumno.MAX_SIZE_NOMBRE, Alumno.MIN_SIZE_NOMBRE, false);
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
                } else {
                    peticiones.SalidasGui.mensaje("Error al crear el Alumno. El Código ya está registrado");
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
        peticiones.SalidasGui.bloqueTexto("Alumnos", listadoAlumnos.toString());
    }

    void modificarNombreAlumno() {
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

    //PROFESORES
    public void obtenerProfesor() {
        String dni = peticiones.EntradasGui.pedirString(PEDIR_DNI_PROFESOR, Profesor.MAX_SIZE_DNI, Profesor.MIN_SIZE_DNI, false);
        if (dni != null) {
            Profesor profesor = Controller.obtenerProfesor(dni);
            peticiones.SalidasGui.mensaje(profesor.toString());
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
                dni = peticiones.EntradasGui.pedirString(PEDIR_DNI_PROFESOR, Profesor.MAX_SIZE_DNI, Profesor.MIN_SIZE_DNI, false);
                if (Controller.obtenerProfesor(dni) == null) {

                    if (dni != null) {
                        nombre = peticiones.EntradasGui.pedirString("Indica el nombre del Profesor", Profesor.MAX_SIZE_NOMBRE, Profesor.MIN_SIZE_NOMBRE, false);
                        if (nombre != null) {
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
                } else {
                    peticiones.SalidasGui.mensaje("Error al crear el profesor. El DNI ya está registrado");
                }
            } catch (Exception e) {
                error = true;
                peticiones.SalidasGui.mensaje("Error al crear el profesor. Datos inválidos");
            }
        } while (error);

    }

    public void borrarProfesor() {
        String codigo = peticiones.EntradasGui.pedirString("Indica el el DNI del Profesor que quieres ELIMINAR.", Profesor.MAX_SIZE_DNI, Profesor.MIN_SIZE_DNI, false);
        Profesor profesor = null;
        if ((profesor = Controller.obtenerProfesor(codigo)) != null) {
            if (peticiones.EntradasGui.pedirBoolean("Seguro que quieres eliminar al profesor " + profesor.getNombre() + " (" + profesor.getDni() + ")")) {
                if (Controller.borrarProfesor(profesor)) {
                    peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                } else {
                    peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                }
            }
        } else {
            peticiones.SalidasGui.mensaje("DNI introducido NO VÁLIDO");
        }
    }

    public void listarProfesores() {
        List<Profesor> profesores = Controller.obtenerProfesores();
        StringBuilder listadoProfesors = new StringBuilder();
        for (Profesor profesor : profesores) {
            listadoProfesors.append(profesor.toString() + "\n");
        }
        peticiones.SalidasGui.bloqueTexto("Profesores",listadoProfesors.toString());
    }

    public void altaAsignatura() {
        boolean error;
        String ciclo = null;
        String codigo = null;
        do {
            error = false;
            try {
                codigo = peticiones.EntradasGui.pedirString(PEDIR_COD_ASIGNATURA, Asignatura.MAX_SIZE_CODIGO, Asignatura.MIN_SIZE_CODIGO, false);
                if (Controller.obtenerAsignatura(codigo) == null) {

                    if (codigo != null) {
                        ciclo = peticiones.EntradasGui.pedirString("Indica el ciclo de la Asignatura", Asignatura.MAX_SIZE_NOMBRE, Asignatura.MIN_SIZE_NOMBRE, false);
                    }
                    if (ciclo != null && codigo != null) {
                        Asignatura asignatura = new Asignatura(codigo, ciclo);
                        if (Controller.crearAsignatura(asignatura)) {
                            peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                        } else {
                            peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                            error = true;
                        }
                    }
                } else {
                    peticiones.SalidasGui.mensaje("Error al crear el Asignatura. El Código ya está registrado");
                }
            } catch (InvalidDataException ex) {
                error = true;
                peticiones.SalidasGui.mensaje("Error al crear el asignatura. Datos inválidos");
            }
        } while (error);

    }

    public void obtenerAsignatura() {
        String codigo = peticiones.EntradasGui.pedirString("Indica el codigo de la Asignatura.", Asignatura.MAX_SIZE_CODIGO, Asignatura.MIN_SIZE_CODIGO, false);
        Asignatura asignatura = Controller.obtenerAsignatura(codigo);
        peticiones.SalidasGui.mensaje(asignatura.toString());
    }

    public void listarAsignaturas() {
        List<Asignatura> asignaturas = Controller.obtenerAsignaturas();
        StringBuilder listadoAsignaturas = new StringBuilder();
        for (Asignatura asignatura : asignaturas) {
            listadoAsignaturas.append(asignatura.toString() + "\n");
        }
        peticiones.SalidasGui.bloqueTexto("Asignaturas", listadoAsignaturas.toString());
    }

    //NOTAS
    public void altaNota() {
        boolean error;
        Alumno alumno = null;
        Asignatura asignatura = null;
        String codAlumno = null;
        String codAsignatura = null;
        LocalDate fecha = null;
        float puntuacion;

        do {
            error = false;
            codAlumno = peticiones.EntradasGui.pedirString(PEDIR_COD_ALUMNO, Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
            if ((alumno = Controller.obtenerAlumno(codAlumno)) != null) {
                codAsignatura = peticiones.EntradasGui.pedirString(PEDIR_COD_ASIGNATURA, Asignatura.MAX_SIZE_CODIGO, Asignatura.MIN_SIZE_CODIGO, false);
                if ((asignatura = Controller.obtenerAsignatura(codAsignatura)) != null) {
                    fecha = EntradasGui.pedirLocalDate("Indica la fecha de la Nota");
                    if (fecha != null) {
                        puntuacion = EntradasGui.pedirFloat("Indica la NOTA");
                        //TODO arreglar date to localdate y cancelar operaciones float
                        Nota nota = new Nota(asignatura, alumno, fecha, puntuacion);
                        if (Controller.crearNota(nota)) {
                            peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                        } else {
                            peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                            error = true;
                        }
                    }

                } else {
                    peticiones.SalidasGui.mensaje("Error el código de la AsignaturaNo Existe");

                }
            } else {
                peticiones.SalidasGui.mensaje("Error el código del Alumno No Existe");
            }

        } while (error);
    }

    public void modificarNota() {
        boolean error;
        Alumno alumno = null;
        Asignatura asignatura = null;
        Profesor profesor = null;
        String codAlumno = null;
        String codAsignatura = null;
        LocalDate fecha;
        float puntuacion;
        do {
            error = false;
            codAlumno = peticiones.EntradasGui.pedirString(PEDIR_COD_ALUMNO, Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
            if ((alumno = Controller.obtenerAlumno(codAlumno)) != null) {
                codAsignatura = peticiones.EntradasGui.pedirString(PEDIR_COD_ASIGNATURA, Asignatura.MAX_SIZE_CODIGO, Asignatura.MIN_SIZE_CODIGO, false);
                if ((asignatura = Controller.obtenerAsignatura(codAsignatura)) != null) {
                    fecha = EntradasGui.pedirLocalDate("Indica la fecha de la nota");
                    if (Controller.obtenerNota(codAsignatura, codAlumno, fecha) != null) {
                           puntuacion = EntradasGui.pedirFloat("Indica la nueva nota");
                        if (Controller.modificarNota(new Nota(asignatura, alumno, fecha ,puntuacion))) {
                            peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                        } else {
                            peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                            error = true;
                        }
                    } else {
                        peticiones.SalidasGui.mensaje("Error La nota no está registrada para ese alumno y asignatura en esa fecha.");
                    }

                } else {
                    peticiones.SalidasGui.mensaje("Error el código de la Asignatura No Existe");

                }
            } else {
                peticiones.SalidasGui.mensaje("Error el código del Alumno No Existe");
            }

        } while (error);
    }

    //MATRICULA
    public void altaMatricula() {
        boolean error;
        Alumno alumno = null;
        Asignatura asignatura = null;
        Profesor profesor = null;
        String codAlumno = null;
        String codAsignatura = null;
        String dniProfesor = null;

        do {
            error = false;
            codAlumno = peticiones.EntradasGui.pedirString(PEDIR_COD_ALUMNO, Alumno.MAX_SIZE_CODIGO, Alumno.MIN_SIZE_CODIGO, false);
            if ((alumno = Controller.obtenerAlumno(codAlumno)) != null) {
                codAsignatura = peticiones.EntradasGui.pedirString(PEDIR_COD_ASIGNATURA, Asignatura.MAX_SIZE_CODIGO, Asignatura.MIN_SIZE_CODIGO, false);
                if ((asignatura = Controller.obtenerAsignatura(codAsignatura)) != null) {
                    dniProfesor = peticiones.EntradasGui.pedirString(PEDIR_DNI_PROFESOR, Profesor.MAX_SIZE_DNI, Profesor.MIN_SIZE_DNI, false);
                    if ((profesor = Controller.obtenerProfesor(dniProfesor)) == null) {
                        Matricula matricula = new Matricula(profesor, asignatura, alumno);
                        if (Controller.altaMatricula(matricula)) {
                            peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                        } else {
                            peticiones.SalidasGui.mensaje("Error al realizar la Operación");
                            error = true;
                        }
                    } else {
                        peticiones.SalidasGui.mensaje("Error el dni del Profesor No Existe");
                    }

                } else {
                    peticiones.SalidasGui.mensaje("Error el código de la Asignatura No Existe");

                }
            } else {
                peticiones.SalidasGui.mensaje("Error el código del Alumno No Existe");
            }

        } while (error);
    }
}
