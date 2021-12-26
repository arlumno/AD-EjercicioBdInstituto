/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.Exceptions.InvalidDataException;
import ad.ejbdinstituto.model.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ar
 */
public class AlumnoDaoImp implements IDaoCrud<Alumno> {

    @Override
    public boolean create(Alumno alumno) {
        String sql = "INSERT INTO " + EstructuraBD.DB_TABLE_ALUMNOS + "(cod_alumno, nombre) VALUES ('" + alumno.getCodigo() + "','" + alumno.getNombre() + "')";
        return ConexionBD.executeSql(sql, "Creado Alumno " + alumno.getNombre());
    }

    @Override
    public Alumno read(String codigo) {
        Alumno alumno = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_alumno, cod_alumno, nombre FROM " + EstructuraBD.DB_TABLE_ALUMNOS + " WHERE cod_alumno = '" + validate(codigo) + "'";
            //https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            //https://www.ibm.com/docs/es/i/7.1?topic=resultsets-example-resultset-interface
            rs = ConexionBD.executeQuerySql(sql, "Consultando Alumno id: " + codigo);            
            if(rs.next()){
                alumno = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3));                            
            }
        } catch (SQLException ex) {
             peticiones.SalidasGui.mensaje("Error al consultar Alumno");             
        } catch (InvalidDataException ex) {
             peticiones.SalidasGui.mensaje("Corrupción en los datos del alumno");             
        }
        return alumno;
    }

    @Override
    public List<Alumno> readAll() {
        List<Alumno> alumnos = new ArrayList<Alumno>();        
        ResultSet rs = null;
        try {
            String sql = "SELECT id_alumno, cod_alumno, nombre FROM " + EstructuraBD.DB_TABLE_ALUMNOS;            
            rs = ConexionBD.executeQuerySql(sql, "Consultando Listado de Alumnos");            
            while(rs.next()){
                alumnos.add(new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3)));                            
            }
        } catch (SQLException ex) {
             peticiones.SalidasGui.mensaje("Error al consultar Alumnos");             
        } catch (InvalidDataException ex) {
             peticiones.SalidasGui.mensaje("Corrupción en los datos del alumno");             
        }
        return alumnos;
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
