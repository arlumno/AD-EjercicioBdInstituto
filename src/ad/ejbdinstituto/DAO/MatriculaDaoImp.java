/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.Exceptions.NoIdException;
import ad.ejbdinstituto.model.Matricula;
import java.util.List;

/**
 *
 * @author Ar
 */
public class MatriculaDaoImp implements ICrudExtended<Matricula>{

    @Override
    public boolean create(Matricula matricula){
        String sql;
        try {
            sql = "INSERT INTO " + EstructuraBD.DB_TABLE_MATRICULAS + "(id_alumno, id_asignatura, dni_profesor) VALUES ('" + matricula.getAlumno().getId() + "','" + matricula.getAsignatura().getId()+ "','" + matricula.getProfesor().getDni()+ "')";
            return ConexionBD.executeSql(sql, "Creada Matricula "+ matricula.getAsignatura().getCodigo() + " - "+ matricula.getAlumno().getNombre());
        } catch (NoIdException ex) {
            return false;
        }
    }

    @Override
    public Matricula read(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Matricula> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public boolean update(Matricula t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Matricula t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
