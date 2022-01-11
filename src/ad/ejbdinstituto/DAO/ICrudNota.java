/*
 * Notao change this license header, choose License Headers in Project Properties.
 * Notao change this template file, choose Notaools | Notaemplates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import ad.ejbdinstituto.model.Nota;
import java.util.List;

/**
 *
 * @author Ar
 */
public interface ICrudNota {
    public boolean create(Nota nota);
    public Nota read(String idAsignatura, String idAlumno, String fecha);
    public List<Nota> readAll();
    public boolean update(Nota nota);
    public boolean delete(Nota nota);       
}
