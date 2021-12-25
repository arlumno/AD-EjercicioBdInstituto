/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import ad.ejbdinstituto.DAO.AlumnoDaoImp;
import ad.ejbdinstituto.Exceptions.InvalidDataException;
import ad.ejbdinstituto.model.Alumno;

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
        do {
            error = false;
            String nombre = peticiones.EntradasGui.pedirString("Indica el nombre del Alumno", Alumno.MAX_SIZE_NOMBRE);
            String codigo = peticiones.EntradasGui.pedirString("Indica el codigo del Alumno.\n Deben 3 numeros segudos de una Letra Mayúscula (Ej. 123Z)");
            try {
                Alumno alumno = new Alumno(codigo, nombre);
                if((new AlumnoDaoImp()).create(alumno)){
                    peticiones.SalidasGui.mensaje("Operación realizada con Éxito");
                }
            } catch (InvalidDataException ex) {
                error = true;
                peticiones.SalidasGui.mensaje("Error al crear el alumno. Datos inválidos");
            }
        } while (error);
        
    }
}
