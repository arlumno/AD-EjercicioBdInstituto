/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.model.Alumno;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Ar
 */
public class AlumnoDaoImp implements IDaoCrud<Alumno>{
    private final String TABLA = "alumnos";

    @Override
    public boolean create(Alumno alumno) {
        String sql = "INSERT INTO " + TABLA  + "(cod_alumno, nombre) VALUES ('"+ alumno.getCodigo() + "','" + alumno.getNombre()+ "')";        
        return ConexionBD.executeSql(sql,"Creado Alumno " +  alumno.getNombre());
    }

    @Override
    public List<Alumno> read(Alumno alumno) {
        List<Alumno> listado = null;
        
        return listado;
    }

    @Override
    public boolean update(Alumno alumno) {
                boolean resultado = false;
        
        return resultado;
    }

    @Override
    public boolean delete(Alumno alumno) {
        boolean resultado = false;
        
        return resultado;
    }
    
    
}
