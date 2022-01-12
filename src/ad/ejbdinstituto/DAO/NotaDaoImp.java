/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.Exceptions.NoIdException;
import ad.ejbdinstituto.controller.Controller;
import ad.ejbdinstituto.model.Alumno;
import ad.ejbdinstituto.model.Asignatura;
import ad.ejbdinstituto.model.Nota;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ar
 */
public class NotaDaoImp implements ICrudNota, IValidateSql {

    @Override
    public boolean create(Nota nota) {
        String sql;
        try {
            sql = "INSERT INTO " + EstructuraBD.DB_TABLE_NOTAS + "(id_alumno, id_asignatura, fecha, nota) VALUES ('" + nota.getAlumno().getId() + "','" + nota.getAsignatura().getId() + "','" + nota.getFecha() + "','" + nota.getNota() + "')";
            return ConexionBD.executeSql(sql, "Creada Nota " + nota.getAsignatura().getCodigo() + " - " + nota.getAlumno().getNombre());
        } catch (NoIdException ex) {            
            return false;
        }
    }

    @Override
    public Nota read(int idAsignatura, int idAlumno, String fecha) {
        Nota nota = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT asi.cod_asignatura, al.cod_alumno, n.fecha, n.nota "
                    + " FROM " + EstructuraBD.DB_TABLE_NOTAS + " AS n"
                    + " LEFT JOIN " +  EstructuraBD.DB_TABLE_ASIGNATURAS + " AS asi ON " + " asi.id_asignatura = n.id_asignatura"
                    + " LEFT JOIN " +  EstructuraBD.DB_TABLE_ALUMNOS + " AS al ON " + " al.id_alumno = n.id_alumno"
                    + " WHERE n.id_asignatura = '" + idAsignatura + "' and n.id_alumno = '" + idAlumno + "' and n.fecha = '" + fecha + "'";
            rs = ConexionBD.executeQuerySql(sql, "Consultando Nota de : " + idAlumno + " - " + idAsignatura + " - " + fecha);
            if (rs != null && rs.next()) {
                String codAsignatura = rs.getString(1);
                String codAlumno = rs.getString(2);
                LocalDate fechaLocalDate = LocalDate.parse(rs.getString(3));
                float puntuacion = rs.getFloat(4);
                Asignatura asignatura = Controller.obtenerAsignatura(codAsignatura);
                Alumno alumno = Controller.obtenerAlumno(codAlumno);
                nota = new Nota(asignatura, alumno,fechaLocalDate,puntuacion );
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Nota" + ex.toString());
        }

        return nota;
    }
    public List<Nota> readByAlumno(int idAlumno) {
        Nota nota = null;
        ResultSet rs = null;
        ArrayList<Nota> listaNotas = new ArrayList<Nota>();
        try {
            String sql = "SELECT asi.cod_asignatura, al.cod_alumno, n.fecha, n.nota "
                    + " FROM " + EstructuraBD.DB_TABLE_NOTAS + " AS n"
                    + " LEFT JOIN " +  EstructuraBD.DB_TABLE_ASIGNATURAS + " AS asi ON " + " asi.id_asignatura = n.id_asignatura"
                    + " LEFT JOIN " +  EstructuraBD.DB_TABLE_ALUMNOS + " AS al ON " + " al.id_alumno = n.id_alumno"
                    + " WHERE n.id_alumno = '" + idAlumno + "'";
            rs = ConexionBD.executeQuerySql(sql, "Consultando Notas de : " + idAlumno);
            if (rs != null && rs.next()) {
                String codAsignatura = rs.getString(1);
                String codAlumno = rs.getString(2);
                LocalDate fechaLocalDate = LocalDate.parse(rs.getString(3));
                float puntuacion = rs.getFloat(4);
                Asignatura asignatura = Controller.obtenerAsignatura(codAsignatura);
                Alumno alumno = Controller.obtenerAlumno(codAlumno);
                listaNotas.add(new Nota(asignatura, alumno,fechaLocalDate,puntuacion));
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Nota" + ex.toString());
        }

        return listaNotas;
    }

    @Override
    public List<Nota> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Nota nota) {
        boolean resultado = false;
        String sql;
        try {
            sql = "UPDATE " + EstructuraBD.DB_TABLE_NOTAS + " SET nota ='"+ nota.getNota() +"' WHERE id_alumno = '" + nota.getAlumno().getId() + "' and id_asignatura = '" + nota.getAsignatura().getId() + "'";
            resultado = ConexionBD.executeSql(sql, "Modificado nota de alunmno" + nota.getAlumno().getNombre());
        } catch (NoIdException ex) {     
            resultado = false;
        }
        return resultado;
    }

    @Override
    public boolean delete(Nota t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
