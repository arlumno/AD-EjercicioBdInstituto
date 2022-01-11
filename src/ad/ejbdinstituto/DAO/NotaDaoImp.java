/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.controller.Controller;
import ad.ejbdinstituto.model.Alumno;
import ad.ejbdinstituto.model.Asignatura;
import ad.ejbdinstituto.model.Nota;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ar
 */
public class NotaDaoImp implements ICrudNota, IValidateSql {

    @Override
    public boolean create(Nota nota) {
        String sql = "INSERT INTO " + EstructuraBD.DB_TABLE_NOTAS + "(id_alumno, id_asignatura, fecha, nota) VALUES ('" + nota.getAlumno().getId() + "','" + nota.getAsignatura().getId() + "','" + nota.getFecha() + "','" + nota.getNota() + "')";
        return ConexionBD.executeSql(sql, "Creada Nota " + nota.getAsignatura().getCodigo() + " - " + nota.getAlumno().getNombre());
    }

    @Override
    public Nota read(String idAsignatura, String idAlumno, String fecha) {
        Nota nota = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_asignatura, id_alumno, fecha, nota FROM " + EstructuraBD.DB_TABLE_NOTAS + " WHERE id_asignatura = '" + validate(idAsignatura) + "' and id_alumno = '" + validate(idAlumno) + "' and fecha = '" + validate(fecha) + "'";
            rs = ConexionBD.executeQuerySql(sql, "Consultando Nota de : " + idAlumno + " - " + idAsignatura + " - " + fecha);
            if (rs != null && rs.next()) {
                Asignatura asignatura = Controller.obtenerAsignatura(rs.getString(1));
                Alumno alumno = Controller.obtenerAlumno(rs.getString(2));
                nota = new Nota(asignatura, alumno, LocalDate.parse(rs.getString(3)), rs.getFloat(4));
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Nota");
        }

        return nota;
    }

    @Override
    public List<Nota> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Nota t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Nota t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
