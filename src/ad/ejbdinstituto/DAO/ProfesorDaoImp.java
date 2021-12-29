/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.Exceptions.InvalidDataException;
import ad.ejbdinstituto.model.Profesor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ar
 */
public class ProfesorDaoImp implements ICrudExtended<Profesor> {

    @Override
    public boolean create(Profesor profesor) {
        String sql = "INSERT INTO " + EstructuraBD.DB_TABLE_PROFESORES + "(dni, nombre, titulacion) VALUES ('" + profesor.getDni() + "','" + profesor.getNombre() + "','" + profesor.getTitulacion() + "')";
        return ConexionBD.executeSql(sql, "Creado Profesor " + profesor.getNombre());
    }

    @Override
    public Profesor read(String id) {
        Profesor profesor = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT dni, nombre, titulacion FROM " + EstructuraBD.DB_TABLE_PROFESORES + " WHERE dni= '" + validate(id) + "'";
            rs = ConexionBD.executeQuerySql(sql, "Consultando Profesor dni: " + id);
            if (rs.next()) {
                profesor = new Profesor(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Profesor");
        }
//        catch (InvalidDataException ex) {
//            peticiones.SalidasGui.mensaje("Corrupción en los datos del profesor");
//        }
        return profesor;
    }

    @Override
    public List<Profesor> readAll() {
         List<Profesor> profesores = new ArrayList<Profesor>();
        ResultSet rs = null;
        try {
            String sql = "SELECT dni, nombre, titulacion FROM " + EstructuraBD.DB_TABLE_PROFESORES;
            rs = ConexionBD.executeQuerySql(sql, "Consultando Listado de Profesores");
            while (rs.next()) {
                profesores.add(new Profesor(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            peticiones.SalidasGui.mensaje("Error al consultar Profesores");
        }
//        catch (InvalidDataException ex) {
//            peticiones.SalidasGui.mensaje("Corrupción en los datos del profesor");
//        }
        return profesores;
    }

    @Override
    public boolean update(Profesor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Profesor profesor) {
        boolean resultado = false;
        String sql = "DELETE FROM " + EstructuraBD.DB_TABLE_PROFESORES + " WHERE dni = '" + profesor.getDni()+ "'";
        return ConexionBD.executeSql(sql, "Eliminado Profesor " + profesor.getNombre());
    }

}
