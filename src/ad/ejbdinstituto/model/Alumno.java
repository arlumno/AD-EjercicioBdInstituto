/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.model;

import ad.ejbdinstituto.Exceptions.InvalidDataException;
import ad.ejbdinstituto.Exceptions.NoIdException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a20armandocb
 */
public class Alumno {

    public static int MAX_SIZE_NOMBRE = 50;
    public static int MIN_SIZE_NOMBRE = 3;
    public static int MAX_SIZE_CODIGO = 4;
    public static int MIN_SIZE_CODIGO = 4;

    private Integer id;
    private String codigo;
    private String nombre;

    public Alumno(int id, String codigo, String nombre) throws InvalidDataException {
        setCodigo(codigo);
        setNombre(nombre);
        setId(id);
    }

    public Alumno(String codigo, String nombre) throws InvalidDataException {
        setCodigo(codigo);
        setNombre(nombre);
    }

    private void validateCodigo(String codigo) throws InvalidDataException {
        if (!utilidades.Utils.validarString(codigo, "...[A-Z]")) {
            throw new InvalidDataException("Código no válido");
        }
    }

    public int getId() throws NoIdException {
        if (id == null) {
            throw new NoIdException("El alumno no tiene una id vinculada a la base de datos");
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) throws InvalidDataException {
        validateCodigo(codigo);
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        String result;
        try {
            result = "Id: " + getId() + " || Código: " + getCodigo() + " || Nombre: " + getNombre(); 
        } catch (NoIdException ex) {
            result = "Id: --- || Código: " + getCodigo() + " || Nombre: " + getNombre(); 
        }
        return result;
    }

}
