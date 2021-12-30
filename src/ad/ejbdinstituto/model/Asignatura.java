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
public class Asignatura {
    public static int MAX_SIZE_NOMBRE = 50; 
    public static int MIN_SIZE_NOMBRE = 3; 
    public static int MAX_SIZE_CODIGO = 4; 
    public static int MIN_SIZE_CODIGO = 4; 
    
    private Integer id;
    private String codigo;
    private String ciclo;
    
    
    public Asignatura(int id, String codigo, String ciclo) throws InvalidDataException{
        setCodigo(codigo);
        setCiclo(ciclo);
        setId(id);
    }
    
    public Asignatura(String codigo, String ciclo) throws InvalidDataException{
        setCodigo(codigo);
        setCiclo(ciclo);
    }
     
    private void  validateCodigo(String codigo) throws InvalidDataException{
        if(!utilidades.Utils.validarString(codigo, "...[A-Z]")){
            throw new InvalidDataException("Código no válido");
        }
    }
    public Integer getId() {
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

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return "Id: "+ getId() + " || Código: " + getCodigo() + " || Ciclo: " + getCiclo(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
