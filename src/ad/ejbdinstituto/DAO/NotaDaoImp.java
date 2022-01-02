/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.model.Nota;
import java.util.List;

/**
 *
 * @author Ar
 */
public class NotaDaoImp implements ICrudExtended<Nota>{

    @Override
    public boolean create(Nota nota) {
        String sql = "INSERT INTO " + EstructuraBD.DB_TABLE_NOTAS + "(id_alumno, id_asignatura, fecha, nota) VALUES ('" + nota.getAlumno().getId() + "','" + nota.getAsignatura().getId()+ "','" + nota.getFecha()+ "','" + nota.getNota()+ "')";
        return ConexionBD.executeSql(sql, "Creada Nota "+ nota.getAsignatura().getCodigo() + " - "+ nota.getAlumno().getNombre());
    }

    @Override
    public Nota read(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
