/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.Exceptions.InvalidDataException;
import java.sql.ResultSet;
import java.sql.SQLException;
import ad.ejbdinstituto.model.Asignatura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ar
 */
public class AsignaturaDaoImp implements ICrudExtended<Asignatura>, IValidateSql {
    
    @Override
    public boolean create(Asignatura asignatura) {
        String sql = "INSERT INTO " + EstructuraBD.DB_TABLE_ASIGNATURAS + "(cod_asignatura, ciclo) VALUES ('" + asignatura.getCodigo() + "','" + asignatura.getCiclo() + "')";
        return ConexionBD.executeSql(sql, "Creado Asignatura código:" + asignatura.getCodigo());
    }
    
    @Override
    public Asignatura read(String codigo) {
        Asignatura asignatura = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_asignatura, cod_asignatura, ciclo FROM " + EstructuraBD.DB_TABLE_ASIGNATURAS + " WHERE cod_asignatura = '" + validate(codigo) + "'";
            rs = ConexionBD.executeQuerySql(sql, "Consultando Asignatura id: " + codigo);
            if (rs.next()) {
                asignatura = new Asignatura(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Asignatura");
        } catch (InvalidDataException ex) {
            peticiones.SalidasGui.mensaje("Corrupción en los datos del asignatura");
        }
        return asignatura;
    }
    
    @Override
    public List<Asignatura> readAll() {
        List<Asignatura> asignaturas = new ArrayList<Asignatura>();
        ResultSet rs = null;
        try {
            String sql = "SELECT id_asignatura, cod_asignatura, ciclo FROM " + EstructuraBD.DB_TABLE_ASIGNATURAS;
            rs = ConexionBD.executeQuerySql(sql, "Consultando Listado de Asignaturas");
            while (rs.next()) {
                asignaturas.add(new Asignatura(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Asignaturas");
        } catch (InvalidDataException ex) {
            peticiones.SalidasGui.mensaje("Corrupción en los datos del asignatura");
        }
        return asignaturas;
    }
    
    @Override
    public boolean update(Asignatura asignatura) {
        boolean resultado = false;
        String sql = "UPDATE " + EstructuraBD.DB_TABLE_ASIGNATURAS + " SET ciclo ='" + validate(asignatura.getCiclo()) + "' WHERE cod_asignatura = '" + asignatura.getCodigo() + "'";
        return ConexionBD.executeSql(sql, "Modificado Asignatura " + asignatura.getCodigo());
    }
    
    @Override
    public boolean delete(Asignatura asignatura) {
        boolean resultado = false;
        String sql = "DELETE FROM " + EstructuraBD.DB_TABLE_ASIGNATURAS + " WHERE cod_asignatura = '" + asignatura.getCodigo() + "'";
        return ConexionBD.executeSql(sql, "Eliminado Asignatura " + asignatura.getCodigo());
    }
    
    public List<Asignatura> readByProfesor(String dni) {
        List<Asignatura> asignaturas = new ArrayList<Asignatura>();
        ResultSet rs = null;
        try {
            String sql = "SELECT asi.id_asignatura, asi.cod_asignatura, asi.ciclo "
                    + "FROM " + EstructuraBD.DB_TABLE_ASIGNATURAS + " AS asi "
                    + "LEFT JOIN " + EstructuraBD.DB_TABLE_MATRICULAS
                    + " AS ma ON ma.id_asignatura = asi.id_asignatura "
                    + "WHERE ma.dni_profesor =  '" + validate(dni) + "'";
            rs = ConexionBD.executeQuerySql(sql, "Consultando Listado de Asignaturas");
            while (rs.next()) {
                asignaturas.add(new Asignatura(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Asignaturas");
        } catch (InvalidDataException ex) {
            peticiones.SalidasGui.mensaje("Corrupción en los datos del asignatura");
        }
        return asignaturas;
    }
    
}
