/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.model;

import ad.ejbdinstituto.Exceptions.InvalidDataException;

/**
 *
 * @author a20armandocb
 */
public class Alumno {
    public static int MAX_SIZE_NOMBRE = 50; 
    public static int MIN_SIZE_NOMBRE = 3; 
    public static int MAX_SIZE_CODIGO = 4; 
    public static int MIN_SIZE_CODIGO = 4; 
    
    private int id;
    private String codigo;
    private String nombre;
    
    
    public Alumno(int id, String codigo, String nombre) throws InvalidDataException{
        setCodigo(codigo);
        setNombre(nombre);
        setId(id);
    }
    
    public Alumno(String codigo, String nombre) throws InvalidDataException{
        setCodigo(codigo);
        setNombre(nombre);
    }
     
    private void  validateCodigo(String codigo) throws InvalidDataException{
        if(!utilidades.Utils.validarString(codigo, "...[A-Z]")){
            throw new InvalidDataException("Código no válido");
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) throws InvalidDataException{
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
        return "Id: "+ getId() + " || Código: " + getCodigo() + " || Nombre: " + getNombre(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
