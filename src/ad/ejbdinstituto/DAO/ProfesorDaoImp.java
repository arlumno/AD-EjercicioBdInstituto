/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.ConexionBD;
import ad.ejbdinstituto.EstructuraBD;
import ad.ejbdinstituto.model.Profesor;
import java.util.List;

/**
 *
 * @author Ar
 */
public class ProfesorDaoImp implements IDaoCrud<Profesor> {

    
    @Override
    public boolean create(Profesor profesor) {
        String sql = "INSERT INTO " + EstructuraBD.DB_TABLE_PROFESORES  + "(dni, nombre, titulacion) VALUES ('"+ profesor.getDni()+ "','" + profesor.getNombre()+ "','" + profesor.getTitulacion()+ "')";        
        return ConexionBD.executeSql(sql,"Creado Profesor " +  profesor.getNombre());
    }
    @Override
    public Profesor read(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profesor> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Profesor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Profesor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
